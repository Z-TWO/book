package com.ztwo.book.service;

import com.ztwo.book.bean.Router;

import java.util.List;

/**
 * @Author ZTwo
 * @Date 2021/12/23 16:16
 */
public interface RouterService {

    /**
     * 获取用户路由
     */
    List<Router> getVisitorRouters();

    /**
     * 获取用户路由
     */
    List<Router> getUserRouters();

    /**
     * 获取商家路由
     */
    List<Router> getMerchantRouters();

}
