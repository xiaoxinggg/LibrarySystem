package com.booksys.libinterface.Admin;

import com.booksys.libinterface.Admin.AdminLogin;
import com.booksys.libinterface.Statistics;
import com.booksys.libinterface.User.UserLogin;
import com.booksys.libinterface.ShowStatistics.*;
import com.booksys.pojo.HotBook;
import com.booksys.util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class statis extends JFrame {
    static JPanel jPanel = new JPanel();
    static JLabel jLabel1 = new JLabel();//标题 : 用户量
    static JLabel jLabel2 = new JLabel();//标题 : 图书
    static JButton jButton1 = new JButton("查看最受欢迎图书"); //普通用户登入
    static JButton jButton2 = new JButton("查看读者借阅排行榜"); //管理员登入
    static JButton jButton3 = new JButton("查看各类别图书流通情况");  //退出系统


    public statis() {
        super("图书管理系统");
        setBounds(700, 220, 500, 500);
        setLayout(null);
        panelSet(); //panel
        label1Set(); //登入
        bottonSet();
        setActionListen();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //结束程序
        setVisible(true); //可视化
    }

    public void setActionListen() {

        jButton3.addActionListener(e -> {
            Connection con = null;
            PreparedStatement pstmt=null;
            ResultSet resultSet = null;
            HotBook hotBook = new HotBook();
            try {
                con = DbUtil.getConnection();
                String sql= "SELECT bookclass.class,COUNT(*) cnt FROM bookclass,borrowrecord,book " +
                        "WHERE book.id=borrowrecord.bookId AND book.class=bookclass.classNo GROUP BY class";
                pstmt = con.prepareStatement(sql);
                resultSet = pstmt.executeQuery();
                new ShowBookSortSum(resultSet);
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        });


        jButton2.addActionListener(e -> {
            Connection con = null;
            PreparedStatement pstmt=null;
            ResultSet resultSet = null;
            HotBook hotBook = new HotBook();
            int cnt = 10;
            try {
                con = DbUtil.getConnection();
                String sql= "select userName,count(*) cnt from borrowrecord,normaluser " +
                        "where normaluser.id=borrowrecord.borrowerId group by borrowerId order by count(*) desc";
                pstmt = con.prepareStatement(sql);
                resultSet = pstmt.executeQuery();
                new ShowActiveUser(resultSet);
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        });

        jButton1.addActionListener(e -> {
            Connection con = null;
            PreparedStatement pstmt=null;
            ResultSet resultSet = null;
            HotBook hotBook = new HotBook();
            int cnt = 10;
            try {
                con = DbUtil.getConnection();
                String sql= "select bookName,count(*) cnt from borrowrecord,book " +
                        "where book.id=borrowrecord.bookId group by bookId order by count(*) desc";
                pstmt = con.prepareStatement(sql);
                resultSet = pstmt.executeQuery();
                new ShowHotBooks(resultSet);
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        });
    }

    public void bottonSet() {
        jButton1.setBounds(150, 200, 200, 40);
        jPanel.add(jButton1);

        jButton2.setBounds(150, 270, 200, 40);
        jPanel.add(jButton2);

        jButton3.setBounds(150, 340, 200, 40);
        jPanel.add(jButton3);

    }

    public void panelSet() {  //初始化panel
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 500, 500); //大小
        jPanel.setBackground(new Color(255, 255, 255));  //背景颜色
        add(jPanel);
    }

    public void label1Set() {
        Statistics statistics = new Statistics();
        String s = "活跃用户量: " + statistics.getActiveUser();
        jLabel1.setText(s);
        jLabel1.setBounds(150, 45, 300, 100); //字体位置
        jLabel1.setFont(new Font("宋体", Font.TYPE1_FONT, 20)); //字体样式
        jPanel.add(jLabel1); //添加


        String s2 = "图书借阅数量: " + statistics.getBorrowSum();
        jLabel2.setText(s2);
        jLabel2.setBounds(150, 100, 300, 100); //字体位置
        jLabel2.setFont(new Font("宋体", Font.TYPE1_FONT, 20)); //字体样式
        jPanel.add(jLabel2); //添加

    }


    public static void main(String[] args) {
        new statis();
    }

    class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("1")) {
                dispose();
                new UserLogin();

            } else if (e.getActionCommand().equals("2")) {
                dispose();
                new AdminLogin();
            } else if (e.getActionCommand().equals("3")) {
                dispose();
                JOptionPane.showMessageDialog(getContentPane(), "成功退出");
                System.exit(0);
            }
        }
    }
}
