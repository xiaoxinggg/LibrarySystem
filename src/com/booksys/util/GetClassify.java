package com.booksys.util;

public class GetClassify {
    public static int getClassify(int num) {
        int t = 0;
        while(num!=0) {
            t = num;
            num /= 10;
        }
        return t;
    }
}
