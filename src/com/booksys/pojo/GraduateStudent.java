package com.booksys.pojo;

import java.util.ArrayList;

public class GraduateStudent extends NormalUser {
    //可借阅的书本数
    protected final int theorySum = 20;
    //书单
    ArrayList<Book> arr = new ArrayList<>(theorySum);


}