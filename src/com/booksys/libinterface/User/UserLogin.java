package com.booksys.libinterface.User;

import com.booksys.libinterface.Inint;
import com.booksys.dao.UserDao;
import com.booksys.pojo.BookRecord;
import com.booksys.pojo.NormalUser;
import com.booksys.util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class UserLogin extends JFrame {
    static JButton jButton1 = new JButton();
    static JButton jButton2 = new JButton();
    static JPanel jPanel = new JPanel();
    static JLabel jLabel1 = new JLabel();
    static JLabel jLabel2 = new JLabel();
    static JLabel jLabel3 = new JLabel();
    static JTextField jTextField = new JTextField();
    static JPasswordField jPasswordField = new JPasswordField();

    public UserLogin() {
        super("图书管理系统");
        setBounds(700, 200, 500, 500);
        setLayout(null);
        panelSet(); //panel
        button1Set(); // 返回上一个界面
        button2Set(); //用户登入
        label1Set();//账号
        label2Set(); //密码
        label3Set(); //图书管理系统
        textSet(); //设置账号密码输入文本
        setActionListen(); //判断用户账号密码
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //账号
    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("账号");
        jLabel1.setBounds(100, 100, 300, 100);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 20));
        jPanel.add(jLabel1);
    }

    //密码
    public void label2Set() {
        jLabel2 = new JLabel();
        jLabel2.setText("密码");
        jLabel2.setBounds(100, 150, 300, 100);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 20));

        jPanel.add(jLabel2);
    }

    //图书管理
    public void label3Set() {
        jLabel3 = new JLabel();
        jLabel3.setText("图书管理系统");
        jLabel3.setBounds(150, 20, 300, 100);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 30));
        jPanel.add(jLabel3);
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
        jPasswordField.setEchoChar('*');

        jTextField.setBounds(150, 130, 200, 40);
        jPasswordField.setBounds(150, 190, 200, 40);

        jPanel.add(jTextField);
        jPanel.add(jPasswordField);
    }

    public void button2Set() {
        jButton2 = new JButton("用户登录");
        jButton2.setBounds(50, 300, 150, 30);
        jPanel.add(jButton2);

    }

    public void button1Set() {
        jButton1 = new JButton("返回");
        jButton1.setBounds(300, 300, 150, 30);
        jPanel.add(jButton1);
    }

    public void setActionListen() {

        //给回车键增加按钮监听事件（登录按钮）
        this.getRootPane().setDefaultButton(jButton2);
//
//        //登录按钮监听
        jButton2.addActionListener(e -> {
            UserDao dao = new UserDao();
            NormalUser user = new NormalUser();
            user.setUserName(jTextField.getText());
            user.setPassword(jPasswordField.getText());
            if (jTextField.getText() == null) {
                JOptionPane.showMessageDialog(getContentPane(), "账号不能为空!");
            } else if ("".equals(jPasswordField.getText())) {
                JOptionPane.showMessageDialog(getContentPane(), "密码不能为空!");
            } else if (!user.getPassword().equals(jPasswordField.getText()) || !user.getUserName().equals(jTextField.getText())) {
                JOptionPane.showMessageDialog(getContentPane(), "用户名或密码错误");
                System.exit(0);
            }
            if (dao.login(user) == null) {
                JOptionPane.showMessageDialog(getContentPane(), "输入信息不能为空！");
                dispose();
                new UserLogin();
            } else {
                JOptionPane.showMessageDialog(getContentPane(), "登录成功");
                dispose();
                if (dao.isOverDue(user).size() == 0) {
                    new UserFuntion(user);
                } else {
                    ArrayList<BookRecord> list = new ArrayList<>();
                    list = dao.isOverDue(user);
                    int t = list.size();
                    JOptionPane.showMessageDialog(getContentPane(), "有"+t+"本书即将逾期，请及时归还");
                    Connection con = null;
                    PreparedStatement pstmt = null;
                    ResultSet resultSet = null;
                    try {
                        con = DbUtil.getConnection();
                        String sql = "select * from bookrecord where borrower = ?";
                        pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, user.getUserName());
                        resultSet = pstmt.executeQuery();
                        new ReturnBook(resultSet, user);
                    } catch (SQLException esp) {
                        esp.printStackTrace();
                    } finally {
                        DbUtil.close(resultSet, pstmt, con);
                    }
                }

            }
        });

        jButton1.addActionListener(e -> {
            dispose();
            new Inint();
        });
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}
