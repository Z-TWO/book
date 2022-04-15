package com.ztwo.book.config;

import com.ztwo.book.common.vo.ResultCode;
import com.ztwo.book.common.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常拦截
 *
 * @Author ZTwo
 * @Date 2021/12/21 14:54
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultVo.error(ResultCode.SERVERERROR);
    }
}
