package com.booksys.libinterface.Admin;



import com.booksys.libinterface.User.BorrowBook;
import com.booksys.libinterface.User.ShowList;
import com.booksys.dao.UserDao;
import com.booksys.pojo.Admin;
import com.booksys.pojo.NormalUser;
import com.booksys.pojo.UnderGraduate;
import com.booksys.util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminFuntion extends JFrame {
    static JPanel jPanel;
    static JLabel jLabel1; //主题
    static JButton jButton1; //返回
    static JButton jButton2; //增加
    static JButton jButton3; //删除
    static JButton jButton4; //修改
    static JButton jButton5; //查询
    static JButton jButton6; //查询所有书记信息
    static JButton jButton7; //查询所有书记信息
    static JButton jButton8; //数据分析

    public AdminFuntion(Admin admin) {
        super("图书管理系统");
        setBounds(700, 100, 500, 800);
        setLayout(null);
        panelSet();
        button1Set();
        button2Set();
        button3Set();
        button4Set();
        button5Set();
        button6Set();
        button7Set();
        button8Set();
        label1Set();
        setActionListen(admin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("请选择管理员的功能");
        jLabel1.setBounds(110, 70, 300, 100);
        jLabel1.setFont(new Font("宋体",Font.BOLD,30));
        jPanel.add(jLabel1);
    }

    public void panelSet() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1000, 1500);
        jPanel.setBackground(new Color(255, 255, 255));
        add(jPanel);
    }

    public void button1Set() {
        jButton1 = new JButton("返回");
        jButton1.setBounds(200, 620, 90, 40);
        jPanel.add(jButton1);
    }
    public void button8Set() {
        jButton8 = new JButton("数据分析");
        jButton8.setBounds(200, 560, 90, 40);
        jPanel.add(jButton8);
    }

    public void button7Set() {
        jButton7 = new JButton("管理用户");
        jButton7.setBounds(200, 500, 90, 40);
        jPanel.add(jButton7);
    }

    public void button2Set() {
        jButton2 = new JButton("增加书籍");
        jButton2.setBounds(200, 440, 90, 40);
        jPanel.add(jButton2);
    }

    public void button3Set() {
        jButton3 = new JButton("删除书籍");
        jButton3.setBounds(200, 380, 90, 40);
        jPanel.add(jButton3);
    }

    public void button4Set() {
        jButton4 = new JButton("修改书籍");
        jButton4.setBounds(200, 320, 90, 40);
        jPanel.add(jButton4);
    }

    public void button5Set() {
        jButton5 = new JButton("显示所有借阅信息");
        jButton5.setBounds(170, 200, 150, 40);
        jPanel.add(jButton5);
    }

    public void button6Set() {
        jButton6 = new JButton("显示所有书籍信息");
        jButton6.setBounds(170, 260, 150, 40);
        jPanel.add(jButton6);
    }

    public void setActionListen(Admin admin) {
        jButton1.addActionListener(e -> {
            dispose();
            new AdminLogin();
        });
        jButton2.addActionListener(e -> {
            dispose();
            new AddBook(admin);
        });
        jButton3.addActionListener(e -> {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                con=DbUtil.getConnection();
                String sql = "select distinct book.id,bookName,isbn,author,price,num from book,warehouse,admin " +
                        "where warehouse.adminId=? and book.toWareHouse=warehouse.wareHouseNum";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, admin.getId());
                resultSet=pstmt.executeQuery();
                new DeleteBook(resultSet, new UnderGraduate());
            } catch (SQLException esp) {
                esp.printStackTrace();
            }finally {
                DbUtil.close(resultSet,pstmt,con);
            }
        });
        jButton4.addActionListener(e -> { //修改书籍信息
            dispose();
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                con=DbUtil.getConnection();
                String sql = "select distinct book.id,bookName,isbn,author,price,num from book,warehouse,admin " +
                        "where warehouse.adminId=? and book.toWareHouse=warehouse.wareHouseNum";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, admin.getId());
                resultSet=pstmt.executeQuery();
                new Modify(resultSet, admin);
            } catch (SQLException esp) {
                esp.printStackTrace();
            }finally {
                DbUtil.close(resultSet,pstmt,con);
            }
        });
        jButton5.addActionListener(e -> {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                con = DbUtil.getConnection();
                String sql = "select * from book,normaluser,borrowrecord " +
                        "where book.id=bookId and normaluser.id=borrowerId";
                pstmt = con.prepareStatement(sql);
                resultSet = pstmt.executeQuery();
                new ShowBorrowRecord(resultSet);
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            } finally {
                DbUtil.close(resultSet, pstmt, con);
            }
        });
        jButton6.addActionListener(e -> {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            UserDao userDao = new UserDao();
            NormalUser user = userDao.selectByName("dll");
            try {
                con = DbUtil.getConnection();
                String sql = "select distinct book.id,bookName,isbn,author,price,num from book,warehouse,admin " +
                        "where warehouse.adminId=? and book.toWareHouse=warehouse.wareHouseNum";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, admin.getId());
                resultSet = pstmt.executeQuery();
                new ShowList(resultSet, user);
            } catch (SQLException esp) {
                esp.printStackTrace();
            } finally {
                DbUtil.close(resultSet, pstmt, con);
            }
        });
        jButton7.addActionListener(e -> {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                con=DbUtil.getConnection();
                String sql="select * from normaluser where realSum=0";
                pstmt = con.prepareStatement(sql);
                resultSet=pstmt.executeQuery();
                new giud(resultSet, admin);
            } catch (SQLException esp) {
                esp.printStackTrace();
            }finally {
                DbUtil.close(resultSet,pstmt,con);
            }

        });
        jButton8.addActionListener(e -> {
            dispose();
            new statis(admin);
        });
    }

    public static void main(String[] args) {
        new AdminFuntion(new Admin());
    }
}
