package com.booksys.service.impl;

import com.booksys.pojo.Admin;
import com.booksys.pojo.BookRecord;
import com.booksys.pojo.NormalUser;
import com.booksys.pojo.User;
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
    ArrayList<BookRecord> isOverDue(NormalUser normalUser);

    //交罚款
    boolean PayFine(NormalUser normalUser, BookRecord bookRecord);

    //充值
    boolean Charge(NormalUser normalUser, double money);

    //获取余额
    double getBalanceSql(NormalUser normalUser);

}
