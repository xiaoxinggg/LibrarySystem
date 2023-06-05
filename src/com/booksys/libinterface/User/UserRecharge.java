package com.booksys.libinterface.User;


import com.booksys.dao.UserDao;
import com.booksys.pojo.NormalUser;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

public class UserRecharge extends JFrame {
    static JPanel panel = new JPanel();
    static JButton button1 = new JButton(); //充值键
    static JLabel label1 = new JLabel(); //充值金额
    static JTextField textField = new JTextField();

    public UserRecharge(NormalUser normalUser) {

        super("充值界面");
        setBounds(700, 200, 500, 300);
        setLayout(null);
        setVisible(true);
        panelset();
        button1set();
        label1set();
        textFieldset();
        setActionListen(normalUser);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //panel;
    public void panelset() {
        panel.setBounds(0, 0, 500, 500);
        panel.setBackground(new Color(255, 255, 255));
        panel.setVisible(true);
        panel.setLayout(null);
        add(panel);
    }

    //充值键
    public void button1set() {
        button1 = new JButton("确认充值");
        button1.setBounds(170, 200, 150, 30);
        button1.setFont(new Font("宋体", BOLD, 18));
        panel.add(button1);
    }

    //充值金额标签
    public void label1set() {
        label1 = new JLabel();
        label1.setText("输入充值金额");
        label1.setBounds(50, 50, 300, 100);
        label1.setFont(new Font("宋体", Font.BOLD, 20));
        panel.add(label1);
    }

    //金额的输入
    public void textFieldset() {
        textField.setBounds(200, 80, 200, 40);
        panel.add(textField);
    }

    //设置监听
    public void setActionListen(NormalUser normalUser) {
        this.getRootPane().setDefaultButton(button1);
        button1.addActionListener(e -> {
            try {
                double str = Double.parseDouble(textField.getText());

                if(str < 0)
                {
                    JOptionPane.showMessageDialog(getContentPane(), "充值金额不能为负数");
                }
                else
                {
                    UserDao dao = new UserDao();
                    dao.Charge(normalUser, str);
                    double balance = dao.getBalanceSql(normalUser);
                    JOptionPane.showMessageDialog(getContentPane(), "充值成功的您! 当前余额为" + balance + "。");
                    dispose();
               //     new(UserFuntion(normalUser);
                }

            } catch (Exception esp) {
                JOptionPane.showMessageDialog(getContentPane(), "请输入正确形式的价格");
            }
        });
    }

//    public static void main(String[] args) {
//        new UserRecharge();
//    }
}
