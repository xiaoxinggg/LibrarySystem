package com.booksys.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * 判断是否超期
 */
public class JudgeOfDelay {
    static final int DAY_BORROW = 30;
    public static boolean isExceedTime(long time) {
        if(CalculateDays.sumOfDays(time)>30)
            return true;
        else
            return false;
    }

    public static long overDays(long time) {
        return CalculateDays.sumOfDays(time)-DAY_BORROW;
    }
}
