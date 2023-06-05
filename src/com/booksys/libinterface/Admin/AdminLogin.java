package com.booksys.libinterface.Admin;

//import com.xitian.dao.UserDao;
//import com.xitian.model.User;

import com.booksys.libinterface.Inint;
import com.booksys.dao.UserDao;
import com.booksys.pojo.Admin;

import javax.swing.*;
import java.awt.*;

public class AdminLogin extends JFrame {
    static JButton jButton1 = new JButton();
    static JButton jButton2 = new JButton();
    static JPanel jPanel = new JPanel();
    static JLabel jLabel1 = new JLabel();
    static JLabel jLabel2 = new JLabel();
    static JLabel jLabel3 = new JLabel();
    static JLabel jLabel4 = new JLabel(); //系统认证密码
    static JTextField jTextField = new JTextField();
    static JPasswordField jPasswordField1 = new JPasswordField();
    static JPasswordField jPasswordField2 = new JPasswordField();


    public AdminLogin() {
        super("图书管理系统");
        setBounds(700, 200, 500, 500);
        setLayout(null);
        panelSet(); //panel
        button1Set(); // 返回上一个界面
        button2Set(); //用户登入
        label1Set();//账号
        label2Set(); //密码
        label3Set(); //图书管理系统
        label4Set(); //认证密码
        textSet(); //设置账号密码输入文本
        setActionListen(); //判断用户账号密码
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //账号
    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("账号");
        jLabel1.setBounds(70, 100, 300, 100);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 20));
        jPanel.add(jLabel1);
    }

    //密码
    public void label2Set() {
        jLabel2 = new JLabel();
        jLabel2.setText("密码");
        jLabel2.setBounds(70, 170, 300, 100);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 20));

        jPanel.add(jLabel2);
    }

    //图书管理
    public void label3Set() {
        jLabel3 = new JLabel();
        jLabel3.setText("管理员登入");
        jLabel3.setBounds(150, 20, 300, 100);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 30));
        jPanel.add(jLabel3);
    }

    //认证密码
    public void label4Set() {
        jLabel4 = new JLabel();
        jLabel4.setText("系统密码认证");
        jLabel4.setBounds(50, 230, 300, 100);
        jLabel4.setFont(new Font("宋体", Font.BOLD, 15));
        jPanel.add(jLabel4);
    }

    public void panelSet() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 500, 500);
        jPanel.setBackground(new Color(255, 255, 255));
        add(jPanel);
    }

    public void textSet() {
        // 用*来隐藏文本
        jPasswordField1.setEchoChar('*');
        jPasswordField2.setEchoChar('*');


        jTextField.setBounds(150, 130, 200, 40);
        jPasswordField1.setBounds(150, 195, 200, 40);
        jPasswordField2.setBounds(150, 260, 200, 40);

        jPanel.add(jTextField);
        jPanel.add(jPasswordField1);
        jPanel.add(jPasswordField2);
    }

    public void button2Set() {
        jButton2 = new JButton("登录");
        jButton2.setBounds(50, 340, 150, 30);
        jPanel.add(jButton2);

    }

    public void button1Set() {
        jButton1 = new JButton("返回");
        jButton1.setBounds(300, 340, 150, 30);
        jPanel.add(jButton1);
    }

    public void setActionListen() {

        //给回车键增加按钮监听事件（登录按钮）
        this.getRootPane().setDefaultButton(jButton2);
//
//        //登录按钮监听  判断是否登入成功
        jButton2.addActionListener(e -> {
            UserDao dao = new UserDao();
            Admin admin = new Admin();
            admin.setUserName(jTextField.getText());
            admin.setPassword(jPasswordField1.getText());
            admin.setAdminPassword(jPasswordField2.getText());
            System.out.println(admin.getUserName());
            System.out.println(admin.getPassword());
            if (admin.getPassword() == null) {
                JOptionPane.showMessageDialog(getContentPane(), "密码不能为空!");
            } else if (dao.login(admin) == null) {
                JOptionPane.showMessageDialog(getContentPane(), "登入失败!");
                dispose();
                new AdminLogin();
            } else if (admin.getAdminPassword().equals("666666")) {
                JOptionPane.showMessageDialog(getContentPane(), "登入成功!");
                dispose();
                new AdminFuntion(admin);
            } else {
                JOptionPane.showMessageDialog(getContentPane(), "管理员验证密码错误");
                dispose();
                new AdminLogin();
            }
        });

        jButton1.addActionListener(e -> {
            dispose();
            new Inint();
        });
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
