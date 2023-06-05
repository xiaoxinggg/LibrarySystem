package com.booksys.util;

import java.util.Date;

/**
 * 计算两个日期之间相差的天数
 */
public class CalculateDays {
    public static long sumOfDays(long time) {
//        System.out.println("sumOfDays" + time);
        long num = new Date().getTime() - time;
//        System.out.println("now" + new Date().getTime());
//        System.out.println("sum" + num);
//        System.out.println(num/24/60/60/1000);
        return num/24/60/60/1000;
    }
}
