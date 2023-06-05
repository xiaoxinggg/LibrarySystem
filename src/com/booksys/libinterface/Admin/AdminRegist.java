//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.booksys.libinterface.Admin;

import com.booksys.Main;
import com.booksys.dao.UserDao;
import com.booksys.pojo.Admin;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminRegist extends JFrame {
    static JButton jButton1 = new JButton();
    static JButton jButton2 = new JButton();
    static JPanel jPanel = new JPanel();
    static JLabel jLabel1 = new JLabel();
    static JLabel jLabel2 = new JLabel();
    static JLabel jLabel3 = new JLabel();
    static JLabel jLabel4 = new JLabel();
    static JTextField jTextField = new JTextField();
    static JPasswordField jPasswordField1 = new JPasswordField();
    static JPasswordField jPasswordField2 = new JPasswordField();

    public AdminRegist() {
        super("图书管理系统");
        this.setBounds(700, 220, 500, 500);
        this.setLayout((LayoutManager)null);
        this.panelSet();
        this.button1Set();
        this.button2Set();
        this.label1Set();
        this.label2Set();
        this.label4Set();
        this.label3Set();
        this.textSet();
        this.setActionListen();
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("账号");
        jLabel1.setBounds(100, 120, 300, 100);
        jLabel1.setFont(new Font("宋体", 1, 20));
        jPanel.add(jLabel1);
    }

    public void label2Set() {
        jLabel2 = new JLabel();
        jLabel2.setText("密码");
        jLabel2.setBounds(100, 170, 300, 100);
        jLabel2.setFont(new Font("宋体", 1, 20));
        jPanel.add(jLabel2);
    }

    public void label4Set() {
        jLabel4 = new JLabel();
        jLabel4.setText("认证密码");
        jLabel4.setBounds(85, 220, 300, 100);
        jLabel4.setFont(new Font("宋体", 1, 15));
        jPanel.add(jLabel4);
    }

    public void label3Set() {
        jLabel3 = new JLabel();
        jLabel3.setText("图书管理系统");
        jLabel3.setBounds(150, 20, 300, 100);
        jLabel3.setFont(new Font("宋体", 1, 30));
        jPanel.add(jLabel3);
    }

    public void panelSet() {
        jPanel = new JPanel();
        jPanel.setLayout((LayoutManager)null);
        jPanel.setBounds(0, 0, 500, 500);
        jPanel.setBackground(new Color(255, 255, 255));
        this.add(jPanel);
    }

    public void textSet() {
        jPasswordField1.setEchoChar('*');
        jPasswordField2.setEchoChar('*');
        jTextField.setBounds(150, 150, 200, 40);
        jPasswordField1.setBounds(150, 200, 200, 40);
        jPasswordField2.setBounds(150, 250, 200, 40);
        jPanel.add(jTextField);
        jPanel.add(jPasswordField1);
        jPanel.add(jPasswordField2);
    }

    public void button2Set() {
        jButton2 = new JButton("注册");
        jButton2.setBounds(20, 330, 150, 30);
        jPanel.add(jButton2);
    }

    public void button1Set() {
        jButton1 = new JButton("返回");
        jButton1.setBounds(300, 330, 150, 30);
        jPanel.add(jButton1);
    }

    public void setActionListen() {
        this.getRootPane().setDefaultButton(jButton2);
        jButton2.addActionListener((e) -> {
            UserDao dao = new UserDao();
            Admin admin = new Admin();
            admin.setUserName(jTextField.getText());
            admin.setPassword(jPasswordField1.getText());
            admin.setAdminPassword(jPasswordField2.getText());
            if(admin.getPassword() == null || admin.getAdminPassword() == null)
            {
                JOptionPane.showMessageDialog(this.getContentPane(), "注册信息不能为空!");
                new AdminRegist();
                this.dispose();
            }
            else if (dao.addUser(admin)) {
                JOptionPane.showMessageDialog(this.getContentPane(), "注册成功!");
                new AdminFuntion(admin);
                this.dispose();
            } else {
                this.dispose();
                JOptionPane.showMessageDialog(this.getContentPane(), "注册失败!");
                new AdminRegist();
                this.dispose();
            }

        });
        jButton1.addActionListener((e) -> {
            this.dispose();
            new Main();
        });
    }

    public static void main(String[] args) {
        new AdminRegist();
    }
}
