package com.booksys.util;

public class Remind {
    static final int DAY_BORROW = 30;

    /**
     * 没有逾期返回一个大于7的数以示区分， 还有7天逾期返回还有几天逾期，逾期返回逾期的天数
     * @param time
     * @return
     */
    public static long remind(long time) {
        long sum = CalculateDays.sumOfDays(time);
        if(sum<23)
            return 2147483647;
        else if(sum<30)
            return DAY_BORROW-sum;
        else
            return overDays(time);
    }

    public static long overDays(long time) {
        return CalculateDays.sumOfDays(time)-DAY_BORROW;
    }
}
