package com.ztwo.book.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author ZTwo
 * @Date 2021/12/23 20:07
 */
@Component
public class DateUtils {

    @Value("${book.dayTimeMillis}")
    private Long dayTimeMillis;

    /**
     * 获取天数差
     *
     * @param end  到期还书时间
     * @param curr 当前时间
     */
    public int countDay(Date end, Date curr) {
        long preTime = end.getTime();
        long lastTime = curr.getTime();
        int subDay = (int) (lastTime - preTime) / 86400000;
        return subDay == 0 ? 1 : subDay;
    }
}
