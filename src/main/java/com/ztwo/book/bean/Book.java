package com.ztwo.book.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Book implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    @Column(name = "press_name")
    private String pressName;

    /**
     * 出版时间
     */
    @Column(name = "press_date")
    private Integer pressDate;

    /**
     * 介绍
     */
    private String info;

    /**
     * 当前剩余数量
     */
    @Column(name = "current_num")
    private Integer currentNum;

    /**
     * 总数量
     */
    @Column(name = "total_num")
    private Integer totalNum;

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
     * 获取书名
     *
     * @return name - 书名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置书名
     *
     * @param name 书名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取出版社
     *
     * @return press_name - 出版社
     */
    public String getPressName() {
        return pressName;
    }

    /**
     * 设置出版社
     *
     * @param pressName 出版社
     */
    public void setPressName(String pressName) {
        this.pressName = pressName;
    }

    /**
     * 获取出版时间
     *
     * @return press_date - 出版时间
     */
    public Integer getPressDate() {
        return pressDate;
    }

    /**
     * 设置出版时间
     *
     * @param pressDate 出版时间
     */
    public void setPressDate(Integer pressDate) {
        this.pressDate = pressDate;
    }

    /**
     * 获取介绍
     *
     * @return info - 介绍
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置介绍
     *
     * @param info 介绍
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取当前剩余数量
     *
     * @return current_num - 当前剩余数量
     */
    public Integer getCurrentNum() {
        return currentNum;
    }

    /**
     * 设置当前剩余数量
     *
     * @param currentNum 当前剩余数量
     */
    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    /**
     * 获取总数量
     *
     * @return total_num - 总数量
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * 设置总数量
     *
     * @param totalNum 总数量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", author=").append(author);
        sb.append(", pressName=").append(pressName);
        sb.append(", pressDate=").append(pressDate);
        sb.append(", info=").append(info);
        sb.append(", currentNum=").append(currentNum);
        sb.append(", totalNum=").append(totalNum);
        sb.append("]");
        return sb.toString();
    }
}