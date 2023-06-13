package com.booksys.libinterface.User;

//import com.xitian.dao.UserDao;
//import com.xitian.model.User;

import com.booksys.libinterface.Admin.AdminRegist;
import com.booksys.dao.UserDao;
import com.booksys.pojo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegist extends JFrame {
    static JButton jButton1 = new JButton(); //返回上一个
    static JButton jButton2 = new JButton(); //注册
    static JPanel jPanel = new JPanel();
    static JLabel jLabel1 = new JLabel();
    static JLabel jLabel2 = new JLabel();
    static JLabel jLabel3 = new JLabel();
    static JLabel jLabel4 = new JLabel();
    static JLabel jLabel5 = new JLabel();
    static JButton jButton_1 = new JButton(); //普通学生
    static JButton jButton_2 = new JButton(); //教职员工
    static JButton jButton_3 = new JButton(); //研究生

    static JTextField jTextField = new JTextField();
    static JTextField jTextField1 = new JTextField();
    static JPasswordField jPasswordField1 = new JPasswordField();
    static JPasswordField jPasswordField2 = new JPasswordField();

    public UserRegist() {
        super("图书管理系统");
        setBounds(700, 200, 500, 500);
        setLayout(null);
        panelSet(); //panel
        button1Set(); // 返回上一个界面
        button2Set(); //用户注册
        label1Set();//id
        label2Set(); //密码
        label3Set(); //图书管理系统
        label4Set(); //密码
        label5Set(); //用户名
        buttobn_1set();
        buttobn_2set();
        buttobn_3set();
        textSet(); //设置账号密码输入文本
        setActionListen(); //判断用户账号密码
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //账号
    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("id");
        jLabel1.setBounds(100, 90, 300, 100);
        jLabel1.setFont(new Font("宋体", Font.TYPE1_FONT, 20)); //字体样式
        jPanel.add(jLabel1);
    }

    //密码
    public void label2Set() {
        jLabel5 = new JLabel();
        jLabel5.setText("密码");
        jLabel5.setBounds(100, 230, 300, 100);
        jLabel5.setFont(new Font("宋体", Font.TYPE1_FONT, 20)); //字体样式
        jPanel.add(jLabel5);
    }

    public void label5Set() {
        jLabel2 = new JLabel();
        jLabel2.setText("用户名");
        jLabel2.setBounds(85, 160, 300, 100);
        jLabel2.setFont(new Font("宋体", Font.TYPE1_FONT, 20)); //字体样式
        jPanel.add(jLabel2);
    }

    //图书管理
    public void label3Set() {
        jLabel3 = new JLabel();
        jLabel3.setText("用户注册");
        jLabel3.setBounds(170, 20, 300, 100);
        jLabel3.setFont(new Font("宋体", Font.TYPE1_FONT, 20)); //字体样式
        jPanel.add(jLabel3);
    }

    public void label4Set() {
        jLabel4 = new JLabel();
        jLabel4.setText("选择身份类型");
        jLabel4.setBounds(170, 360, 300, 100);
        jLabel4.setFont(new Font("宋体", Font.TYPE1_FONT, 20)); //字体样式
        jPanel.add(jLabel4);
    }

    public void panelSet() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 500, 500);
        jPanel.setBackground(new Color(255, 255, 255)); //白色背景
        add(jPanel);
    }

    public void textSet() {
        // 用*来隐藏文本
        jPasswordField1.setEchoChar('*');

        jTextField.setBounds(150, 120, 200, 40);
        jTextField1.setBounds(150,190,200,40);
        jPasswordField1.setBounds(150, 260, 200, 40);

        jPanel.add(jTextField);
        jPanel.add(jPasswordField1);
        jPanel.add(jTextField1);

    }


    public void buttobn_1set() {
        jButton_1 = new JButton("普通学生");
        jButton_1.setBounds(20, 340, 150, 30);
        UserRegist.MyActionListener myActionListener = new UserRegist.MyActionListener();
        jButton_1.setActionCommand("1"); //为1 进入普通用户注册
        jButton_1.addActionListener(myActionListener);
        jPanel.add(jButton_1);
    }

    public void buttobn_2set() {
        jButton_2 = new JButton("教职员工");
        jButton_2.setBounds(160, 340, 150, 30);
        UserRegist.MyActionListener myActionListener = new UserRegist.MyActionListener();
        jButton_2.setActionCommand("2");
        jButton_2.addActionListener(myActionListener);
        jPanel.add(jButton_2);

    }

    public void buttobn_3set() {
        jButton_3 = new JButton("研究生");
        jButton_3.setBounds(310, 340, 150, 30);
        UserRegist.MyActionListener myActionListener = new UserRegist.MyActionListener();
        jButton_3.setActionCommand("3");
        jButton_3.addActionListener(myActionListener);
        jPanel.add(jButton_3);

    }

    //注册
    public void button2Set() {
        jButton2 = new JButton("用户注册");
        jButton2.setBounds(50, 400, 150, 30);
       // jPanel.add(jButton2);

    }

    //返回上一个
    public void button1Set() {
        jButton1 = new JButton("返回");
        jButton1.setBounds(300, 360, 150, 30);
       // jPanel.add(jButton1);
    }

    public void setActionListen() {

        //给回车键增加按钮监听事件（登录按钮）
        this.getRootPane().setDefaultButton(jButton2);
        this.getRootPane().setDefaultButton(jButton_1);
        this.getRootPane().setDefaultButton(jButton_2);
        this.getRootPane().setDefaultButton(jButton_3);

        UserDao dao = new UserDao();
        //本科生
        jButton_1.addActionListener(esp -> {
            NormalUser stu = new NormalUser();
            stu.setId(Integer.parseInt(jTextField.getText()));
            stu.setUserName(jTextField1.getText()); //赋值
            stu.setPassword(jPasswordField1.getText());

            stu.setTheorySum(10);
            stu.setBalance(10);
            if (stu.getUserName() == null || stu.getPassword() == null) {
                JOptionPane.showMessageDialog(getContentPane(), "注册信息不能为空");
                new UserRegist();
            } else if (dao.addUser(stu) == true) {
                JOptionPane.showMessageDialog(getContentPane(), "注册成功!");
                new UserFuntion(stu);
                dispose();
            } else {
                dispose();
                JOptionPane.showMessageDialog(getContentPane(), "该账号已存在，请重新注册！");
                new AdminRegist();
                dispose();
            }
        });
        //教职员工
        jButton_2.addActionListener(esp -> {
            Staff stu = new Staff();
            stu.setId(Integer.parseInt(jTextField.getText()));
            stu.setUserName(jTextField1.getText()); //赋值
            stu.setPassword(jPasswordField1.getText());
            stu.setTheorySum(30);
            stu.setBalance(10);
            if (stu.getUserName() == null || stu.getPassword() == null) {
                JOptionPane.showMessageDialog(getContentPane(), "注册信息不能为空");
                new UserRegist();
            } else if (dao.addUser(stu) == true) {
                JOptionPane.showMessageDialog(getContentPane(), "注册成功!");
                new UserFuntion(stu);
                dispose();
            } else {
                dispose();
                JOptionPane.showMessageDialog(getContentPane(), "该账号已存在，请重新注册！");
                new AdminRegist();
                dispose();
            }
        });
        //研究生
        jButton_3.addActionListener(esp -> {
            GraduateStudent stu = new GraduateStudent();
            stu.setId(Integer.parseInt(jTextField.getText()));
            stu.setUserName(jTextField1.getText()); //赋值
            stu.setPassword(jPasswordField1.getText());
            stu.setTheorySum(20);
            stu.setBalance(10);
            if (stu.getUserName() == null || stu.getPassword() == null) {
                JOptionPane.showMessageDialog(getContentPane(), "注册信息不能为空");
                new UserRegist();
            } else if (dao.addUser(stu) == true) {
                JOptionPane.showMessageDialog(getContentPane(), "注册成功!");
                new UserFuntion(stu);
                dispose();
            } else {
                dispose();
                JOptionPane.showMessageDialog(getContentPane(), "该账号已存在，请重新注册！");
                new AdminRegist();
                dispose();
            }
        });


        //   MyActionListener;
    }

    public static void main(String[] args) {
        new UserRegist();
    }

    class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("1")) {

            } else if (e.getActionCommand().equals("2")) {

            } else if (e.getActionCommand().equals("3")) {

            }
        }
    }
}
