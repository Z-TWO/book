package com.ztwo.book.api;

import com.ztwo.book.bean.Book;
import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.service.BookService;
import com.ztwo.book.service.BorrowedInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author ZTwo
 * @Date 2021/12/20 18:38
 */
@Api(tags = "图书管理")
@RequestMapping("/book")
@CrossOrigin
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @ApiOperation(value = "获取所有书籍")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ResultVo getBooksByPage(@RequestParam(defaultValue = "1") Integer curr,
                                    @RequestParam(defaultValue = "10") Integer size) {
        return bookService.getBookByPage(curr, size);
    }

    @ApiOperation(value = "通过关键词查询书籍")
    @ApiImplicitParam(name = "keyWorld", value = "关键词", dataTypeClass = String.class, required = true)
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    private ResultVo getBookByName(String keyWorld,
                                   @RequestParam(defaultValue = "1") Integer curr,
                                   @RequestParam(defaultValue = "10") Integer size) {
        return bookService.searchBookByName(keyWorld, curr, size);
    }

    @ApiOperation(value = "添加书籍")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    private ResultVo addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @ApiOperation(value = "删除书籍")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private ResultVo deleteBook(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }

    @ApiOperation(value = "更新书籍")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    private ResultVo updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }



}
