package com.ztwo.book.bean;

import java.io.Serializable;
import javax.persistence.*;

public class Merchant implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 商家用户名
     */
    private String username;

    /**
     * 商家密码
     */
    private String password;

    /**
     * 是否可用
     */
    private Integer enable;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商家用户名
     *
     * @return username - 商家用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置商家用户名
     *
     * @param username 商家用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取商家密码
     *
     * @return password - 商家密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置商家密码
     *
     * @param password 商家密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取是否可用
     *
     * @return enable - 是否可用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置是否可用
     *
     * @param enable 是否可用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", enable=").append(enable);
        sb.append("]");
        return sb.toString();
    }
}