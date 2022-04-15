package com.ztwo.book.common.vo;

/**
 * @Author ZTwo
 * @Date 2021/12/19 22:55
 */
public enum ResultCode {
    SUCCESS(200, "OK"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOTFOUND(404, "Not Found"),
    SERVERERROR(500, "Internal Server Error");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
