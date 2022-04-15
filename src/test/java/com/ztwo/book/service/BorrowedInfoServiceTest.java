package com.ztwo.book.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
class BorrowedInfoServiceTest {

    @Resource
    private BorrowedInfoService borrowedInfoService;

    @Test
    void borrowBook() {
//        borrowedInfoService.borrowBook(1, 1);
    }

    @Test
    void backBook() {
//        ResultVo resultVo = borrowedInfoService.backBook(8);
//        System.out.println(resultVo);
    }
}