package com.booksys.pojo;

import java.sql.Timestamp;

public class BorrowRecord {
    private int bookId;
    private int borrowerId;
    private Timestamp borrowerTime;
    private Timestamp returnTime;
    private int isReturn;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
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

    public int getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(int isReturn) {
        this.isReturn = isReturn;
    }
}
