package com.ztwo.book.service.impl;

import com.ztwo.book.bean.Book;
import com.ztwo.book.bean.BorrowedInfo;
import com.ztwo.book.common.util.DateUtils;
import com.ztwo.book.common.util.JwtUtils;
import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.dao.BookMapper;
import com.ztwo.book.dao.BorrowedInfoMapper;
import com.ztwo.book.service.BorrowedInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author ZTwo
 * @Date 2021/12/23 19:42
 */
@Service
public class BorrowedInfoServiceImpl implements BorrowedInfoService {

    @Value("${book.day}")
    private Integer day;
    @Value("${book.cost}")
    private Double cost;
    @Value("${book.dayTimeMillis}")
    private Long dayTimeMillis;
    @Value("${book.maxBook}")
    private Integer maxBook;
    @Resource
    private BorrowedInfoMapper borrowedInfoMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private DateUtils dateUtils;
    @Resource
    private JwtUtils jwtUtils;

    @Transactional
    @Override
    public ResultVo borrowBook(Integer bookId, HttpServletRequest request) {
        //获取用户Id
        Integer userId = jwtUtils.getUserIdFromRequest(request);
        /*
         * 判断用户是否可以借书
         * 不可以借书情况：1.已借该书 2.当前借书数量达到maxBook
         */
        ResultVo isBorrowBook = isBorrowBook(userId, bookId);
        if (isBorrowBook != null) return isBorrowBook;

        /*
         * 判断书籍是否可借
         * 该书不能借的情况：1.没有库存
         */
        Book book = bookMapper.selectByPrimaryKey(bookId);
        if (book != null && book.getCurrentNum() > 0) {
            //更新书本库存数量
            book.setCurrentNum(book.getCurrentNum() - 1);
            bookMapper.updateByPrimaryKeySelective(book);

            //创建借书记录
            long currentTimeMillis = System.currentTimeMillis();
            long returnTimeMillis = currentTimeMillis + day * dayTimeMillis;
            BorrowedInfo order = new BorrowedInfo(userId, bookId, new Date(currentTimeMillis), null, new Date(returnTimeMillis), new BigDecimal("0.00"), 0);
            int result = borrowedInfoMapper.insert(order);
            if (result > 0) {
                return ResultVo.success();
            }
        }

        return ResultVo.error(201, "操作失败");
    }

    @Transactional
    @Override
    public ResultVo backBook(Integer orderId, HttpServletRequest request) {
        //判断当前用户是否有该记录
        Integer userId = jwtUtils.getUserIdFromRequest(request);
        Example example = new Example(BorrowedInfo.class);
        example.and().andEqualTo("userId", userId)
                .andEqualTo("id", orderId);
        List<BorrowedInfo> borrowedInfos = borrowedInfoMapper.selectByExample(example);

        if (borrowedInfos != null && borrowedInfos.size() > 0) {
            BorrowedInfo borrowedInfo = borrowedInfos.get(0);
            //计算费用
            Date curr = new Date();
            BigDecimal bigDecimal = new BigDecimal("0.00");
            //当前时间大于归还时间则需要计费
            if (borrowedInfo.getReturnTime().before(curr)) {
                int subDay = dateUtils.countDay(borrowedInfo.getReturnTime(), curr);
                bigDecimal = bigDecimal.add(new BigDecimal(subDay * cost));
            }

            //更新借书记录 [金额 记录状态 结束时间]
            borrowedInfo.setCharge(bigDecimal);
            borrowedInfo.setStatus(1);
            borrowedInfo.setEndTime(curr);
            int borrowResult = borrowedInfoMapper.updateByPrimaryKey(borrowedInfo);

            //修改书籍库存数量
            Book book = bookMapper.selectByPrimaryKey(borrowedInfo.getBookId());
            book.setCurrentNum(book.getCurrentNum() + 1);
            int bookResult = bookMapper.updateByPrimaryKeySelective(book);

            if (borrowResult > 0 && bookResult > 0) {
                return ResultVo.success();
            }
        }

        return ResultVo.error(201, "操作失败");
    }

    /**
     * 判断当前是否可以借书
     *
     * @param userId 用户id
     * @param bookId 书籍id
     */
    private ResultVo isBorrowBook(Integer userId, Integer bookId) {
        //查询当前用户的借书记录
        Example example = new Example(BorrowedInfo.class);
        example.and().andEqualTo("userId", userId);
        example.and().andEqualTo("status", 0);
        List<BorrowedInfo> list = borrowedInfoMapper.selectByExample(example);

        //是否已经借用此书
        for (BorrowedInfo borrowedInfo : list) {
            if (Objects.equals(borrowedInfo.getBookId(), bookId)) {
                return ResultVo.error(201, "当前已借此书，还未归还");
            }
        }

        //判断是否借用书本大于3
        if (list.size() >= 3) {
            return ResultVo.error(201, "每个用户最多借用3本书，如需借用其它书籍，请先归还已借书籍");
        }

        return null;
    }
}
