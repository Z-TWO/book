package com.ztwo.book.service;


import com.ztwo.book.common.vo.ResultVo;

/**
 * @Author ZTwo
 * @Date 2021/12/20 12:31
 */
public interface MerchantService {

    /**
     * 商家登陆
     *
     * @param username
     * @param password
     * @return [java.lang.String, java.lang.String]
     */
    ResultVo login(String username, String password);


}
