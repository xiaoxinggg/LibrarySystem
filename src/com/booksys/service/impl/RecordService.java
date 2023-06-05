package com.booksys.service.impl;

import com.booksys.pojo.BookRecord;
import com.booksys.pojo.NormalUser;

interface RecordService {

    boolean addRecord(BookRecord bookRecord);

    BookRecord selectByBookRecord(String bookName, String borrower);

    //根据借书人找借书记录
    BookRecord selectByBorrower(BookRecord bookRecord);

    //删除用户的借书记录（用户查看自己目前借的书是就看不到了）
    boolean deleteRecord(NormalUser normalUser, BookRecord bookRecord);


}
