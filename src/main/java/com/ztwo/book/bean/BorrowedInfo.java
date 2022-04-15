package com.ztwo.book.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "borrowed_info")
public class BorrowedInfo implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 书本id
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date endTime;

    /**
     * 还书时间
     */
    @Column(name = "return_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date returnTime;

    /**
     * 费用
     */
    private BigDecimal charge;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 书籍对象
     */
    private Book book;

    @Override
    public String toString() {
        return "BorrowedInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", returnTime=" + returnTime +
                ", charge=" + charge +
                ", status=" + status +
                ", book=" + book +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 用户对象
     */
    private User user;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private static final long serialVersionUID = 1L;

    public BorrowedInfo() {

    }

    public BorrowedInfo(Integer userId, Integer bookId, Date createTime, Date endTime, Date returnTime, BigDecimal charge, Integer status) {
        this.userId = userId;
        this.bookId = bookId;
        this.createTime = createTime;
        this.endTime = endTime;
        this.returnTime = returnTime;
        this.charge = charge;
        this.status = status;
    }

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取书本id
     *
     * @return book_id - 书本id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置书本id
     *
     * @param bookId 书本id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取还书时间
     *
     * @return return_time - 还书时间
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * 设置还书时间
     *
     * @param returnTime 还书时间
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * 获取费用
     *
     * @return charge - 费用
     */
    public BigDecimal getCharge() {
        return charge;
    }

    /**
     * 设置费用
     *
     * @param charge 费用
     */
    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    /**
     * 获取订单状态
     *
     * @return status - 订单状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态
     *
     * @param status 订单状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}