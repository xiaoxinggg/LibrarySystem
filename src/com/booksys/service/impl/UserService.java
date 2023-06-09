package com.booksys.service.impl;

import com.booksys.pojo.*;

import java.util.ArrayList;

public interface UserService {

    NormalUser login(NormalUser normalUser);

    //管理员登录
    Admin login(Admin admin);

    //username是unique约束的
    NormalUser selectByName(String userName);

    //注册
    boolean addUser(User user);

    //是否有图书快要逾期
    ArrayList<BorrowRecord> isOverDue(NormalUser normalUser);

    //交罚款
    boolean PayFine(NormalUser normalUser, BorrowRecord bookRecord);

    //充值
    boolean Charge(NormalUser normalUser, double money);

    //获取余额
    double getBalanceSql(NormalUser normalUser);

}
