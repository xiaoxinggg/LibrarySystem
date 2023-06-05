package com.booksys.pojo;

public class NormalUser extends User {
    //余额
    protected double balance = 10;
    //实际借阅的书本数
    protected int realSum;
    //可借阅的书本数
    protected int theorySum;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getRealSum() {
        return realSum;
    }

    public void setRealSum(int realSum) {
        this.realSum = realSum;
    }

    public int getTheorySum() {
        return theorySum;
    }

    public void setTheorySum(int theorySum) {
        this.theorySum = theorySum;
    }
}