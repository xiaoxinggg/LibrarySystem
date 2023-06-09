package com.booksys.pojo;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

public class Book {
    private int id;
    private String bookName; //书名
    private int isbn; //编号
    private String author; // 作者
    private double price; // 图书价格
    private int num; //书的数量
    private int toWareHorse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getToWareHorse() {
        return toWareHorse;
    }

    public void setToWareHorse(int toWareHorse) {
        this.toWareHorse = toWareHorse;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", isbn=" + isbn +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", site=" + toWareHorse +
                '}';
    }
}
