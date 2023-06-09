package com.booksys.libinterface;

import com.booksys.libinterface.Admin.AdminRegist;
import com.booksys.libinterface.User.UserRegist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Regist extends JFrame {
    static JPanel jPanel = new JPanel();
    static JLabel jLabel1 = new JLabel();//标题 : 选择注册方式
    static JButton jButton1 = new JButton("普通用户注册"); //普通用户注册
    static JButton jButton2 = new JButton("管理员注册"); //管理员注册
    static JButton jButton3 = new JButton("退出系统");  //退出系统

    public Regist() {
        super("图书管理系统");
        setBounds(700, 220, 500, 500);
        setLayout(null);
        panelSet(); //panel
        label1Set(); //注册
        button1Set(); //普通
        button2Set(); //管理员
        button3Set(); //退出按键
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //结束程序
        setVisible(true); //可视化
    }

    public void panelSet() {  //初始化panel
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 500, 500); //大小
        jPanel.setBackground(new Color(255, 255, 255));  //背景颜色
        add(jPanel);
    }

    public void label1Set() {
        jLabel1.setText("请选择注册方式");
        jLabel1.setBounds(130, 45, 300, 100); //字体位置
        jLabel1.setFont(new Font("宋体", Font.TYPE1_FONT, 30)); //字体样式
        jPanel.add(jLabel1); //添加

    }

    //普通用户注册
    public void button1Set() {
        jButton1.setBounds(300, 300, 150, 30);
        jPanel.add(jButton1);
        MyActionListener myActionListener = new MyActionListener();
        jButton1.setActionCommand("1"); //为1 进入普通用户注册
        jButton1.addActionListener(myActionListener);
    }


    //管理员登入
    public void button2Set() {
        jButton2.setBounds(50, 300, 150, 30);
        jPanel.add(jButton2);
        MyActionListener myActionListener = new MyActionListener();

        jButton2.setActionCommand("2");
        jButton2.addActionListener(myActionListener);

    }

    //退出系统
    public void button3Set() {
        jButton3.setBounds(175, 200, 150, 30);
        jPanel.add(jButton3);
        MyActionListener myActionListener = new MyActionListener();
        jButton3.setActionCommand("3");
        jButton3.addActionListener(myActionListener);

    }


    public static void main(String[] args) {
        new Regist();
    }

    class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("1")) {
                dispose();
                 new UserRegist();

            } else if (e.getActionCommand().equals("2")) {
                dispose();
                new AdminRegist();
            } else if (e.getActionCommand().equals("3")) {
                dispose();
                JOptionPane.showMessageDialog(getContentPane(), "成功退出");
                System.exit(0);
            }
        }
    }
}
