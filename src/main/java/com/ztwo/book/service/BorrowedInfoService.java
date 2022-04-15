package com.ztwo.book.service;


import com.ztwo.book.common.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ZTwo
 * @Date 2021/12/20 12:34
 */
public interface BorrowedInfoService {

    /**
     * 借书
     *
     * @param bookId
     */
    ResultVo borrowBook(Integer bookId, HttpServletRequest request);

    /**
     * 还书
     *
     * @param orderId
     */
    ResultVo backBook(Integer orderId, HttpServletRequest request);
}
