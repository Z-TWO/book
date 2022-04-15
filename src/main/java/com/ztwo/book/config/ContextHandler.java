package com.ztwo.book.config;

/**
 * 上下文对象类
 *
 * @Author ZTwo
 * @Date 2021/12/21 15:40
 */
public class ContextHandler {

    public static ThreadLocal<Integer> context = new ThreadLocal<>();

    public static void setUserId(Integer userId) {
        context.set(userId);
    }

    public static Integer getUserId() {
        return context.get();
    }

    public static void shutdown() {
        context.remove();
    }
}
