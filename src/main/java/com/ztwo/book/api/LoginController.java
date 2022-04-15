package com.ztwo.book.api;

import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.service.MerchantService;
import com.ztwo.book.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author ZTwo
 * @Date 2022/1/5 16:02
 */
@Api(tags = "登陆注册管理")
@RestController
@CrossOrigin
public class LoginController {

    @Resource
    private MerchantService merchantService;

    @ApiOperation(value = "商家登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataTypeClass = String.class, required = true)
    })
    @RequestMapping(value = "/merchant/login", method = RequestMethod.POST)
    private ResultVo merchantLogin(String username, String password) {
        return merchantService.login(username, password);
    }

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataTypeClass = String.class, required = true)
    })
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    private ResultVo userLogin(String username, String password) {
        return userService.login(username, password);
    }

    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataTypeClass = String.class, required = true)
    })
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    private ResultVo register(String username, String password) {
        return userService.register(username, password);
    }
}
