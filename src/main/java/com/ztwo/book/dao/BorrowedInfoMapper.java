package com.ztwo.book.dao;

import com.ztwo.book.bean.BorrowedInfo;
import com.ztwo.book.general.GeneraDAO;

import java.util.List;

public interface BorrowedInfoMapper extends GeneraDAO<BorrowedInfo> {

    List<BorrowedInfo> selectBorrowInfoByUserId(Integer uid);

    List<BorrowedInfo> selectBorrowInfo();

}