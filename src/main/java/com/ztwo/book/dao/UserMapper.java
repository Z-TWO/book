package com.ztwo.book.dao;


import com.ztwo.book.bean.User;
import com.ztwo.book.general.GeneraDAO;

public interface UserMapper extends GeneraDAO<User> {

    Integer updateUserStatus(Integer uid, Integer status);
}