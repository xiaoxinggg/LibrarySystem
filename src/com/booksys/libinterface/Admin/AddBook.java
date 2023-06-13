package com.booksys.libinterface.Admin;

import com.booksys.dao.BookDao;
import com.booksys.pojo.Admin;
import com.booksys.pojo.Book;

import javax.swing.*;
import java.awt.*;

public class AddBook extends JFrame {
    static JPanel jPanel;
    static JLabel jLabel1;
    static JLabel jLabel2;
    static JLabel jLabel3;
    static JLabel jLabel4;
    static JLabel jLabel5;
    static JLabel jLabel6;
    static JLabel jLabel7;
    static JButton jButton1;
    static JButton jButton2;
    static JTextField jTextField1;
    static JTextField jTextField2;
    static JTextField jTextField3;
    static JTextField jTextField4;
    static JTextField jTextField5;
    static JComboBox box1;//下拉列表框


    public AddBook(Admin admin) {
        super("图书管理系统");
        setBounds(500, 120, 1000, 890);
        //设置绝对布局
        setLayout(null);
        panelSet();
        button1Set();
        button2Set();
        label1Set();
        label2Set();
        label3Set();
        label4Set();
        label5Set();
        label6Set(); //数量
        label7Set(); //选择仓库地址
        setActionListen(admin);
        textSet();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void textSet() {
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField1.setBounds(400, 180, 300, 40);
        jTextField2.setBounds(400, 270, 300, 40);
        jTextField3.setBounds(400, 360, 300, 40);
        jTextField4.setBounds(400, 450, 300, 40);
        jTextField5.setBounds(400, 540, 300, 40);
        jPanel.add(jTextField1);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
        jPanel.add(jTextField4);
        jPanel.add(jTextField5);


        String[] province={"仓库1","仓库2","仓库3","仓库4","仓库5"};
        //城市
        box1=new JComboBox(province);//下拉列表框
        box1.setBounds(400, 620,300,40);
        jPanel.add(box1);

    }

    public void label1Set() {
        jLabel1 = new JLabel();
        jLabel1.setText("请填写要录入的书籍信息");
        jLabel1.setBounds(250, 50, 500, 100);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 40));
        jPanel.add(jLabel1);
    }

    public void panelSet() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1000, 900);
        jPanel.setBackground(new Color(255, 255, 255));
        add(jPanel);
    }

    public void button1Set() {
        jButton1 = new JButton("录入");
        jButton1.setBounds(300, 700, 100, 50);
        jPanel.add(jButton1);
    }

    public void button2Set() {
        jButton2 = new JButton("返回");
        jButton2.setBounds(550, 700, 100, 50);
        jPanel.add(jButton2);
    }

    public void label2Set() {
        jLabel2 = new JLabel();
        jLabel2.setText("书籍名称");
        jLabel2.setBounds(200, 150, 300, 100);
        jLabel2.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel2);
    }

    public void label3Set() {
        jLabel3 = new JLabel();
        jLabel3.setText("ISBN");
        jLabel3.setBounds(200, 240, 300, 100);
        jLabel3.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel3);
    }

    public void label4Set() {
        jLabel4 = new JLabel();
        jLabel4.setText("作者");
        jLabel4.setBounds(200, 330, 300, 100);
        jLabel4.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel4);
    }

    public void label5Set() {
        jLabel5 = new JLabel();
        jLabel5.setText("价格");
        jLabel5.setBounds(200, 420, 300, 100);
        jLabel5.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel5);
    }

    public void label6Set() {
        jLabel6 = new JLabel();
        jLabel6.setText("数量");
        jLabel6.setBounds(200, 510, 300, 100);
        jLabel6.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel6);
    }

    public void label7Set() {
        jLabel7 = new JLabel();
        jLabel7.setText("存放仓库");
        jLabel7.setBounds(200, 590, 300, 100);
        jLabel7.setFont(new Font("宋体", Font.BOLD, 25));
        jPanel.add(jLabel7);
    }

    public void setActionListen(Admin admin) {
        jButton1.addActionListener(e -> {
            BookDao dao = new BookDao();
            Book book = new Book();
            book.setBookName(jTextField1.getText());
            book.setIsbn(Integer.parseInt(jTextField2.getText()));
            book.setPrice(Double.parseDouble(jTextField4.getText()));
            book.setAuthor(jTextField3.getText());
            book.setNum(Integer.parseInt(jTextField5.getText()));
            book.setToWareHorse(box1.getSelectedIndex() + 1);

            boolean ret = dao.addBook(book); //添加新书
            if (ret) {
                System.out.println("新增成功");
                System.out.println("id" +book.getToWareHorse());
                JOptionPane.showMessageDialog(getContentPane(), "添加成功，请继续操作!");
                dispose();
                new AdminFuntion(admin); //再次进入管理员功能界面
            } else {
                System.out.println("新增失败");
                dispose();
                new AddBook(admin); //重新添加
            }

        });
//
        jButton2.addActionListener(e -> {
            dispose();
            new AdminFuntion(admin);
        });
    }


    public static void main(String[] args) {
        new AddBook(new Admin());
    }
}
