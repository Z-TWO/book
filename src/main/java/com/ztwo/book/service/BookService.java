package com.ztwo.book.service;


import com.ztwo.book.bean.Book;
import com.ztwo.book.common.vo.ResultVo;

/**
 * @Author ZTwo
 * @Date 2021/12/19 22:53
 */
public interface BookService {

    /***
     * 获取书籍信息（分页）
     *
     * @param currentPage
     * @param size
     */
    ResultVo getBookByPage(Integer currentPage, Integer size);

    /***
     * 获取指定书籍
     *
     * @param id
     */
    ResultVo getBook(Integer id);

    /**
     * 通过名字查询书籍
     *
     * @param keyWorld
     * @return [java.lang.String]
     */
    ResultVo searchBookByName(String keyWorld, Integer currentPage, Integer size);

    /***
     * 删除书籍
     *
     * @param bookId
     */
    ResultVo deleteBook(Integer bookId);

    /***
     * 添加书籍
     *
     * @param book
     */
    ResultVo addBook(Book book);

    /***
     * 更新书籍
     *
     * @param book
     */
    ResultVo updateBook(Book book);

}
