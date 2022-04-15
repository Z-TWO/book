package com.ztwo.book.dao;

import com.ztwo.book.bean.BorrowedInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BorrowedInfoMapperTest {

    @Resource
    private BorrowedInfoMapper borrowedInfoMapper;

    @Test
    void selectBorrowInfoByUserId() {
        List<BorrowedInfo> list = borrowedInfoMapper.selectBorrowInfoByUserId(23);
        for (BorrowedInfo borrowedInfo : list) {
            System.out.println(borrowedInfo);
        }
    }
}