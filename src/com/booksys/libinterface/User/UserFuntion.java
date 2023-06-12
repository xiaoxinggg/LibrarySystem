package com.booksys.libinterface.User;

import com.booksys.pojo.NormalUser;
import com.booksys.util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFuntion extends JFrame {
    //借 还 查书单 查书库
    static JPanel jPanel;
    static JLabel jLabel1; //主题
    static JButton jButton1; //返回
    static JButton jButton2; //还书
    static JButton jButton3; //借书
    static JButton jButton4; //查自己借书单
    static JButton jButton5; //查自己借书单

//    static JButton jButton6; //查书库

    public UserFuntion(NormalUser normalUser) {
        super("图书管理系统");
        setBounds(700, 220, 500, 600);

        setLayout(null);
        panelSet();
        button1Set();
        button2Set();
        button3Set();
        button4Set();
        button5Set();

//        button6Set();
        label1Set();
        setActionListen(normalUser);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("请选择功能");
        jLabel1.setBounds(160, 70, 300, 100);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 30));
        jPanel.add(jLabel1);
    }

    public void panelSet() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1000, 1000);
        jPanel.setBackground(new Color(255, 255, 255));
        add(jPanel);
    }

    public void button1Set() {
        jButton1 = new JButton("返回");
        jButton1.setBounds(200, 410, 90, 40);
        jPanel.add(jButton1);
    }

    public void button2Set() {
        jButton2 = new JButton("归还书籍");
        jButton2.setBounds(200, 200, 90, 40);
        jPanel.add(jButton2);
    }

    public void button3Set() {
        jButton3 = new JButton("借阅书籍");
        jButton3.setBounds(200, 270, 90, 40);
        jPanel.add(jButton3);
    }

    public void button4Set() {
        jButton4 = new JButton("充值");
        jButton4.setBounds(200, 340, 90, 40);
        jPanel.add(jButton4);
    }

    public void button5Set() {
        jButton5 = new JButton("查询个人信息");
        jButton5.setBounds(185, 480, 120, 40);
        jPanel.add(jButton5);
    }

    public void setActionListen(NormalUser normalUser) {
        jButton1.addActionListener(e -> { //返回
            dispose();
            new UserLogin();
        });
        jButton2.addActionListener(e -> { //还书
//            dispose();
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                con = DbUtil.getConnection();
                String sql = "select bookName,borrowTime from book,normaluser,borrowrecord " +
                        "where book.id=bookId and normaluser.id=borrowerId and borrowerId=? and isReturn=0";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, normalUser.getId());
                resultSet = pstmt.executeQuery();
                new ReturnBook(resultSet, normalUser);
            } catch (SQLException esp) {
                esp.printStackTrace();
            } finally {
                DbUtil.close(resultSet, pstmt, con);
            }
        });
        jButton3.addActionListener(e -> { //借书
          //  dispose();
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                con = DbUtil.getConnection();
                String sql = "select * from book";
                pstmt = con.prepareStatement(sql);
                resultSet = pstmt.executeQuery();
                new BorrowBook(resultSet, normalUser);
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            } finally {
                DbUtil.close(resultSet, pstmt, con);
            }
        });
        jButton4.addActionListener(e -> {  //充值界面
         //   dispose();
            new UserRecharge(normalUser);
        });
        jButton5.addActionListener(e -> {
            //  dispose();
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                con = DbUtil.getConnection();
                String sql = "select * from normaluser where userName=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, normalUser.getUserName());
                resultSet = pstmt.executeQuery();
                System.out.println("34234");
                new UserMessage(resultSet, normalUser);
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            } finally {
                DbUtil.close(resultSet, pstmt, con);
            }
        });

//        jButton6.addActionListener(e -> { //显示所有书籍
//        //    dispose();
//            Connection con = null;
//            PreparedStatement pstmt = null;
//            ResultSet resultSet = null;
//            UserDao userDao = new UserDao();
////            NormalUser user = userDao.selectByName("dll");
//            try {
//                con = DbUtil.getConnection();
//                String sql = "select * from book";
//                pstmt = con.prepareStatement(sql);
//                resultSet = pstmt.executeQuery();
//                new BorrowBook(resultSet, user);
////            new ShowList(user);
//            } catch (SQLException sqe) {
//                sqe.printStackTrace();
//            } finally {
//                DbUtil.close(resultSet, pstmt, con);
//            }
//        });
    }

//    public static void main(String[] args) {
//        new UserFuntion();
//    }
}
