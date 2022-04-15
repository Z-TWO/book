package com.ztwo.book.service.impl;


import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztwo.book.bean.BorrowedInfo;
import com.ztwo.book.bean.User;
import com.ztwo.book.common.util.JwtUtils;
import com.ztwo.book.common.vo.ResultCode;
import com.ztwo.book.common.vo.ResultVo;
import com.ztwo.book.dao.BorrowedInfoMapper;
import com.ztwo.book.dao.UserMapper;
import com.ztwo.book.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ZTwo
 * @Date 2021/12/20 12:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${jwt.account_user}")
    private String ACCOUNT_U;

    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private BorrowedInfoMapper borrowedInfoMapper;

    @Override
    public ResultVo login(String username, String password) {
        //查询用户
        Example example = new Example(User.class);
        example.and()
                .andEqualTo("username", username);
        List<User> users = userMapper.selectByExample(example);

        //用户是否存在和密码是否正确
        if (!users.isEmpty() && users.get(0).getPassword().equals(SecureUtil.md5(password))) {
            if (users.get(0).getEnable() == 0) {
                return ResultVo.error(201, "该用户被限制登录");
            }
            //生成token
            HashMap<String, Object> claim = new HashMap<>();
            claim.put("account", ACCOUNT_U);
            claim.put("userId", users.get(0).getId());
            String token = jwtUtils.generateToken(claim);
            return ResultVo.success(token);
        }

        return ResultVo.error(201, "用户名或密码有误");

    }

    @Override
    public ResultVo register(String username, String password) {
        //判断用户是否存在
        Example example = new Example(User.class);
        example.and()
                .andEqualTo("username", username);
        List<User> users = userMapper.selectByExample(example);

        //判断用户是否已经存在
        if (!users.isEmpty()) {
            return ResultVo.error(201, "注册失败，用户已存在");
        }

        //添加用户
        String securePass = SecureUtil.md5(password);
        int result = userMapper.insert(new User(username, securePass, 1));
        if (result > 0) {
            return ResultVo.success();
        }

        return ResultVo.error(ResultCode.SERVERERROR);
    }

    @Override
    public ResultVo getUser(Integer userId) {
        Example example = new Example(User.class);
        example.and().andEqualTo("id", userId);
        List<User> users = userMapper.selectByExample(example);

        return ResultVo.success(users);
    }

    @Override
    public ResultVo getUser(Integer currentPage, Integer size) {
        //开启分页
        PageHelper.startPage(currentPage, size);

        //查询
        List<User> list = userMapper.selectAll();
        PageInfo<User> users = new PageInfo<>(list);

        return ResultVo.success(users);
    }

    @Override
    public ResultVo deleteUser(Integer userId) {
        int result = userMapper.deleteByPrimaryKey(userId);
        if (result > 0) {
            return ResultVo.success();
        }

        return ResultVo.error(201, "删除失败");
    }

    @Override
    public ResultVo updateUser(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0) {
            return ResultVo.success();
        }

        return ResultVo.error(201, "更新失败");
    }

    @Override
    public ResultVo addUser(User user) {
        //判断用户是否存在
        Example example = new Example(User.class);
        example.and().andEqualTo("name", user.getUsername());
        List<User> users = userMapper.selectByExample(example);

        if (users.size() == 0) {
            int result = userMapper.insert(user);
            if (result > 0) {
                return ResultVo.success();
            }
        }

        return ResultVo.error(201, "添加用户失败");
    }

    @Override
    public ResultVo getBorrowInfoByPage(Integer curr, Integer size, HttpServletRequest request) {
        //获取当前用户ID
        Integer userId = jwtUtils.getUserIdFromRequest(request);

        //开启分页
        PageHelper.startPage(curr, size);
        List<BorrowedInfo> list = borrowedInfoMapper.selectBorrowInfoByUserId(userId);
        PageInfo<BorrowedInfo> borrowedInfoPageInfos = new PageInfo<>(list);

        return ResultVo.success(borrowedInfoPageInfos);
    }

    @Override
    public ResultVo getBorrowInfoByPage(Integer curr, Integer size) {
        //开启分页
        PageHelper.startPage(curr, size);
        List<BorrowedInfo> list = borrowedInfoMapper.selectBorrowInfo();
        PageInfo<BorrowedInfo> borrowedInfoPageInfos = new PageInfo<>(list);

        return ResultVo.success(borrowedInfoPageInfos);
    }

    @Override
    public ResultVo updateUserPass(Integer uid, String password) {
        User user = new User();
        user.setPassword(SecureUtil.md5(password));
        user.setId(uid);
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0) {
            return ResultVo.success();
        }

        return ResultVo.error(201, "操作失败");
    }

    @Override
    public ResultVo updateUserStatus(Integer uid, Integer status) {
        status = status == 1 ? 1 : 0;
        Integer result = userMapper.updateUserStatus(uid, status);
        if (result > 0) {
            return ResultVo.success();
        }

        return ResultVo.error(201, "更新失败");
    }
}
