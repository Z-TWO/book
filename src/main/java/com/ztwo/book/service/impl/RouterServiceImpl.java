package com.ztwo.book.service.impl;

import com.ztwo.book.bean.Router;
import com.ztwo.book.dao.RouterMapper;
import com.ztwo.book.service.RouterService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ZTwo
 * @Date 2021/12/23 16:25
 */
@Service
public class RouterServiceImpl implements RouterService {

    @Resource
    private RouterMapper routerMapper;

    @Override
    public List<Router> getVisitorRouters() {
        Example example = new Example(Router.class);
        example.and().orEqualTo("type", 0);
        //执行查询
        return routerMapper.selectByExample(example);
    }

    @Override
    public List<Router> getUserRouters() {
        Example example = new Example(Router.class);
        example.and().orEqualTo("type", 0)
                .orEqualTo("type", 1);
        //执行查询
        return routerMapper.selectByExample(example);
    }

    @Override
    public List<Router> getMerchantRouters() {
        Example example = new Example(Router.class);
        example.and().orEqualTo("type", 0)
                .orEqualTo("type", 2);
        //执行查询
        return routerMapper.selectByExample(example);
    }
}
