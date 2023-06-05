package com.booksys.libinterface.User;

import com.booksys.dao.BookDao;
import com.booksys.dao.BorrowerRecordDao;
import com.booksys.dao.RecordDao;
import com.booksys.util.MyDialogDemo;
import com.booksys.pojo.BookRecord;
import com.booksys.pojo.BorrowRecord;
import com.booksys.pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class BorrowBook extends JFrame {
    int i = 0;

    public BorrowBook(ResultSet result, User user) throws SQLException {
        //设置窗口
        super("借阅图书");
        Container container = this.getContentPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 1000, 800);
        //设置标签
        JLabel label = new JLabel("选中要借阅的书,点击\"借阅\"按钮可借阅: ");
        label.setBounds(100, 20, 500, 40);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setForeground(new Color(255, 255, 255));
        container.add(label);
        // 表头（列名）
        Object[][] rowData1 = new Object[100][7];

        String[] columnNames = {"SNID", "书名", "作者", "ISBN", "价格", "总数"};

        while (result.next()) {
            //获取信息
            int id = result.getInt("id");
            String bookName = result.getString("bookName");
            int isbn = result.getInt("isbn");
            String author = result.getString("author");
            double price = result.getDouble("price");
            int num = result.getInt("num");

            //放入表格数组中
            rowData1[i][0] = id;
            rowData1[i][1] = bookName;
            rowData1[i][2] = author;
            rowData1[i][3] = isbn;
            rowData1[i][4] = price;
            if (num == 0)
                rowData1[i][5] = "无";
            else
                rowData1[i][5] = num;
            i++;
        }
        // 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(rowData1, columnNames);

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 20));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(true);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(40);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 100, 700, 600);
        // 添加 滚动面板 到 内容面板
        container.add(scrollPane);
        Font font = new Font("黑体", Font.BOLD, 20);

        //创建修改按钮
        JButton button1 = new JButton("借阅");
        button1.setBounds(450, 700, 80, 30);
        button1.setFont(font);
        container.add(button1);

        //设置监听
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean flag;
                //获取当前的行列数
                int index = 0;  //1
                try {
                    index = table.getSelectedRow();
                    if (index >= i) {
                        new MyDialogDemo("请选中相应行!");
                    } else {
                        //判断书籍是否已被借出
                        if ("无".equals(rowData1[index][5])) {
                            new MyDialogDemo("该书已被借完！");
                        } else {
                            //书籍被借出, 执行借书的操作,更新数据库中借阅书籍的状态(书库和借阅表)
                            BookDao bookDao = new BookDao();
                            RecordDao recordDao = new RecordDao();
                            BorrowerRecordDao borrowerRecordDao = new BorrowerRecordDao();

                            BookRecord bookRecord = new BookRecord();
                            BorrowRecord borrowRecord = new BorrowRecord();
                            try {
                                flag = bookDao.borrowBook((String) rowData1[index][1]);

                                bookRecord.setBookName((String) rowData1[index][1]);
                                bookRecord.setBorrower(user.getUserName());

                                Timestamp t = new Timestamp(new Date().getTime());
                                bookRecord.setDate(t);
                                recordDao.addRecord(bookRecord);

                                borrowRecord.setBookName((String) rowData1[index][1]);
                                borrowRecord.setBorrower(user.getUserName());
                                borrowRecord.setBorrowerTime(t);
                                borrowerRecordDao.addBorrowerRecord(borrowRecord);
//                            System.out.println((String)rowData1[index][1]);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            MyDialogDemo myDialogDemo = flag ? new MyDialogDemo("借阅成功") : new MyDialogDemo("借阅失败");
                        }
                    }
                } catch (Exception ex) {
                    new MyDialogDemo("未选中行!");
                }
            }
        });

        //获得容器
        container.setBackground(new Color(255, 255, 255));
        setBounds(1, 1, 1000, 800);
        container.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet resultSet = null;
//        try {
//            con=DbUtil.getConnection();
//            String sql="select * from book";
//            pstmt = con.prepareStatement(sql);
//            resultSet=pstmt.executeQuery();
//            new BorrowBook(resultSet, );
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            DbUtil.close(resultSet,pstmt,con);
//        }
//    }
}