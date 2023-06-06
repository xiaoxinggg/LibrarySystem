package com.booksys.libinterface.Admin;


import com.booksys.dao.BookDao;

import javax.swing.*;
import java.awt.*;

public class ModifyBook extends JFrame {
    static JPanel jPanel;
    static JLabel jLabel1;
    static JLabel jLabel2;
    static JLabel jLabel3;
    static JLabel jLabel4;
    static JLabel jLabel5;
    static JButton jButton1;
    static JButton jButton2;
    static JTextField jTextField1;


    public ModifyBook(int id, int x) {
        super("图书管理系统");
        setBounds(500, 150, 1000, 500);
        //设置绝对布局
        setLayout(null);
        panelSet();
        button1Set();
        button2Set();
        label1Set();
        label2Set();
//        label3Set();
//        label4Set();
//        label5Set();
        label6Set(); //数量
        setActionListen(id, x);
        textSet();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void textSet() {
        jTextField1 = new JTextField();
        jTextField1.setBounds(400, 180, 300, 40);
        jPanel.add(jTextField1);


    }

    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("请填写要修改的书籍信息");
        jLabel1.setBounds(250, 50, 500, 100);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 40));
        jPanel.add(jLabel1);
    }

    public void panelSet() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1000, 500);
        jPanel.setBackground(new Color(255, 255, 255));
        add(jPanel);
    }

    public void button1Set() {
        jButton1 = new JButton("录入");
        jButton1.setBounds(300, 300, 100, 50);
        jPanel.add(jButton1);
    }

    public void button2Set() {
        jButton2 = new JButton("返回");
        jButton2.setBounds(550, 300, 100, 50);
        jPanel.add(jButton2);
    }

    public void label2Set() {
        jLabel2 = new JLabel();
        jLabel2.setText("修改内容");
        jLabel2.setBounds(200, 150, 300, 100);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel2);
    }

    public void label6Set() {
        jLabel5 = new JLabel();
        jLabel5.setText("数量");
        jLabel5.setBounds(200, 510, 300, 100);
        jLabel5.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel5);
    }

    public void setActionListen(int id, int x) {
        jButton1.addActionListener(e -> {
            dispose();
            String ele = jTextField1.getText();
            BookDao bookDao = new BookDao();
            if (x == 1) {
                //修改书名
                bookDao.modifyBookName(id, ele);
            } else if (x == 2) {
                //修改作者
                bookDao.modifyAuthor(id, ele);
            } else if (x == 3) {
                //修改isbn
                bookDao.modifyISBN(id, Integer.parseInt(ele));
            } else if (x == 4) {
                //修改价格
                bookDao.modifyPrice(id, Double.parseDouble(ele));
            } else if (x == 5) {
                //修改总数
                bookDao.modifyNum(id, Integer.parseInt(ele));
            }
        });
        jButton2.addActionListener(e -> {
            dispose();
          //  new ModifyBook(id, x);
        });
    }
}