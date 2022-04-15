package com.ztwo.book.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.ztwo.book.bean.Merchant;
import com.ztwo.book.common.util.JwtUtils;
import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.dao.MerchantMapper;
import com.ztwo.book.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ZTwo
 * @Date 2021/12/20 18:30
 */
@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService {

    @Value("${jwt.account_merchant}")
    private String ACCOUNT_M;

    @Resource
    private MerchantMapper merchantMapper;
    @Resource
    private JwtUtils jwtUtils;

    @Override
    public ResultVo login(String username, String password) {
        Example example = new Example(Merchant.class);
        example.and().andEqualTo("username", username);

        List<Merchant> merchants = merchantMapper.selectByExample(example);
        if (!merchants.isEmpty() && merchants.get(0).getPassword().equals(SecureUtil.md5(password))) {
            //生成token
            HashMap<String, Object> claim = new HashMap<>();
            claim.put("account", ACCOUNT_M);
            claim.put("userId", merchants.get(0).getId());
            String token = jwtUtils.generateToken(claim);

            //返回token
            return ResultVo.success(token);
        }

        return ResultVo.error(201, "用户名或密码有误");
    }
}
