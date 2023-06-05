package com.booksys.pojo;

import java.sql.Timestamp;

public class BorrowRecord {
    private int id;
    private String bookName;
    private String borrower;
    private Timestamp borrowerTime;
    private Timestamp returnTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public Timestamp getBorrowerTime() {
        return borrowerTime;
    }

    public void setBorrowerTime(Timestamp borrowerTime) {
        this.borrowerTime = borrowerTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }
}
