package com.ztwo.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztwo.book.bean.Book;
import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.dao.BookMapper;
import com.ztwo.book.service.BookService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ZTwo
 * @Date 2021/12/20 19:28
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public ResultVo getBookByPage(Integer currentPage, Integer size) {
        PageHelper.startPage(currentPage, size);
        List<Book> list = bookMapper.selectAll();
        PageInfo<Book> books = new PageInfo<>(list);
        return ResultVo.success(books);
    }

    @Override
    public ResultVo getBook(Integer id) {
        return null;
    }

    @Override
    public ResultVo searchBookByName(String keyWorld, Integer currentPage, Integer size) {
        //开启分页
        PageHelper.startPage(currentPage, size);

        //模糊查询
        Example example = new Example(Book.class);
        example.and().andLike("name", "%" + keyWorld + "%");
        example.orderBy("currentNum");
        List<Book> list = bookMapper.selectByExample(example);
        PageInfo<Book> books = new PageInfo<>(list);

        return ResultVo.success(books);
    }


    @Override
    public ResultVo deleteBook(Integer bookId) {
        int result = bookMapper.deleteByPrimaryKey(bookId);
        if (result > 0) {
            return ResultVo.success();
        }

        return ResultVo.error(200, "删除失败");
    }

    @Override
    public ResultVo addBook(Book book) {
        //判断书籍是否已经存在
        Example example = new Example(Book.class);
        example.and().andEqualTo("name", book.getName())
                .andEqualTo("author", book.getAuthor())
                .andEqualTo("pressName", book.getPressName())
                .andEqualTo("pressDate", book.getPressDate());
        List<Book> books = bookMapper.selectByExample(example);

        if (books.isEmpty()) {
            //添加书籍
            int result = bookMapper.insert(book);
            if (result > 0) {
                return ResultVo.success();
            }
        }

        return ResultVo.error(200, "添加失败");
    }

    @Override
    public ResultVo updateBook(Book book) {
        int result = bookMapper.updateByPrimaryKeySelective(book);
        if (result > 0) {
            return ResultVo.success();
        }
        return ResultVo.error(200, "更新失败");
    }

}
