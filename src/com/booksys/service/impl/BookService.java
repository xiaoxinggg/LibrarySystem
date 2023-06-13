package com.booksys.service.impl;

import com.booksys.pojo.Book;
import com.booksys.pojo.BorrowRecord;
import com.booksys.pojo.NormalUser;

import java.sql.Timestamp;
import java.util.List;

public interface BookService {
    boolean addBook(Book book);

    //查看所有图书
    List<Book> selectAll();

    //删除书籍
    boolean deleteBook(String bookName);

    //还书
    boolean returnBook(BorrowRecord borrowRecord);

    //借书

    boolean modifyBookName(int id, String bookName);

    boolean modifyAuthor(int id, String Author);

    boolean modifyISBN(int id, int isbn);

    boolean modifyPrice(int id, double price);

    boolean modifyNum(int id, int num);
    
    

}
