package com.booksys.pojo;

import java.util.ArrayList;

public class Staff extends NormalUser {
    //可借阅的书本数
    protected final int theorySum = 30;
    //书单
    protected ArrayList<Book> arr = new ArrayList<>(theorySum);


}
