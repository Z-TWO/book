package com.ztwo.book.api;

import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @Author ZTwo
 * @Date 2021/12/25 15:03
 */
@Api(tags = "系统管理")
@RequestMapping("/admin")
@CrossOrigin
@RestController
public class AdminController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "查询所有用户")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    private ResultVo selectAllUser(
            @RequestParam(defaultValue = "1") Integer curr,
            @RequestParam(defaultValue = "10") Integer size) {
        return userService.getUser(curr, size);
    }

    @ApiOperation(value = "修改用户密码")
    @RequestMapping(value = "/user/pass", method = RequestMethod.POST)
    private ResultVo updateUserPass(Integer uid, String password) {
        return userService.updateUserPass(uid, password);
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    private ResultVo deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @ApiOperation(value = "更改用户权限")
    @RequestMapping(value = "/user/status", method = RequestMethod.POST)
    private ResultVo updateUserStatus(Integer uid, Integer status) {
        return userService.updateUserStatus(uid, status);
    }

    @ApiOperation(value = "查看所有借书记录")
    @RequestMapping(value = "/user/borrowinfo", method = RequestMethod.GET)
    private ResultVo getUserBorrowInfo(@RequestParam(defaultValue = "1") Integer curr,
                                       @RequestParam(defaultValue = "10") Integer size) {
        return userService.getBorrowInfoByPage(curr, size);
    }

}
