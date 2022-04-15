package com.ztwo.book.api;

import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.service.BorrowedInfoService;
import com.ztwo.book.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author ZTwo
 * @Date 2022/1/5 16:00
 */
@Api(tags = "借阅管理")
@RequestMapping("/user")
@CrossOrigin
@RestController
public class BorrowController {

    @Resource
    private UserService userService;
    @Resource
    private BorrowedInfoService borrowedInfoService;

    @ApiOperation(value = "获取当前用户的所有借书记录")
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    private ResultVo getBorrowInfo(@RequestParam(defaultValue = "1") Integer curr,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   HttpServletRequest request) {
        return userService.getBorrowInfoByPage(curr, size, request);
    }

    @ApiOperation(value = "借书")
    @RequestMapping(value = "/book/borrow", method = RequestMethod.GET)
    private ResultVo borrowBook(Integer bId, HttpServletRequest request) {
        return borrowedInfoService.borrowBook(bId, request);
    }

    @ApiOperation(value = "还书")
    @RequestMapping(value = "/book/back", method = RequestMethod.GET)
    private ResultVo getBackBook(Integer oId, HttpServletRequest request) {
        return borrowedInfoService.backBook(oId, request);
    }
}
