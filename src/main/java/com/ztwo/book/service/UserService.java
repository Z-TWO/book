package com.ztwo.book.service;


import com.ztwo.book.bean.User;
import com.ztwo.book.common.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author ZTwo
 * @Date 2021/12/20 12:26
 */
public interface UserService {

    /***
     * 用户登陆
     *
     * @param username
     * @param password
     */
    ResultVo login(String username, String password);

    /***
     * 用户注册
     *
     * @param username
     * @param password
     */
    ResultVo register(String username, String password);

    /***
     * 查询指定用户
     *
     * @param userId
     */
    ResultVo getUser(Integer userId);

    /**
     * 查询所有用户（分页查询）
     *
     * @param currentPage
     * @param size
     * @return [java.lang.Integer, java.lang.Integer]
     */
    ResultVo getUser(Integer currentPage, Integer size);

    /**
     * 删除用户
     *
     * @param userId
     */
    ResultVo deleteUser(Integer userId);

    /***
     * 更新用户
     *
     * @param user
     */
    ResultVo updateUser(User user);

    /***
     * 添加用户
     *
     * @param user
     */
    ResultVo addUser(User user);

    /**
     * 获取当前用户的结束记录（分页查询）
     *
     * @param curr
     * @param size
     * @param request
     */
    ResultVo getBorrowInfoByPage(Integer curr, Integer size, HttpServletRequest request);

    /**
     * 获取所有用户的结束记录（分页查询）
     *
     * @param curr
     * @param size
     */
    ResultVo getBorrowInfoByPage(Integer curr, Integer size);

    /**
     * 修改用户密码
     *
     * @param uid
     * @param password
     * @return [java.lang.Integer, java.lang.String]
     */
    ResultVo updateUserPass(Integer uid, String password);

    /**
     * 更新账号状态
     *
     * @param uid
     * @param status
     * @return [java.lang.Integer, java.lang.Integer]
     */
    ResultVo updateUserStatus(Integer uid, Integer status);
}
