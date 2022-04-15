package com.ztwo.book.common.vo;

import lombok.ToString;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 公共响应类
 *
 * @Author ZTwo
 * @Date 2021/12/19 22:54
 */
public class ResultVo {
    private int code;
    private String msg;
    private Object data;

    private ResultVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    private ResultVo(ResultCode resultCode) {
        setResultCode(resultCode);
    }

    private ResultVo(ResultCode resultCode, Object data) {
        setResultCode(resultCode);
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    /**
     * 成功
     */
    public static ResultVo success(Object data) {
        return new ResultVo(ResultCode.SUCCESS, data);
    }

    public static ResultVo success() {
        return success(null);
    }

    /**
     * 失败
     */
    public static ResultVo error(ResultCode resultCode) {
        return new ResultVo(resultCode, null);
    }

    public static ResultVo error(ResultCode resultCode, Object data) {
        return new ResultVo(resultCode, data);
    }

    public static ResultVo error(int code, String msg) {
        return new ResultVo(code, msg, null);
    }

    public static ResultVo error(int code, String msg, Object data) {
        return new ResultVo(code, msg, data);
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
