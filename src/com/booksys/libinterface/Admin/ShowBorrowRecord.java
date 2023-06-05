package com.booksys.libinterface.Admin;

import com.booksys.dao.BookDao;
import com.booksys.dao.BorrowerRecordDao;
import com.booksys.dao.RecordDao;
import com.booksys.util.DbUtil;
import com.booksys.util.MyDialogDemo;
import com.booksys.pojo.BookRecord;
import com.booksys.pojo.BorrowRecord;
import com.booksys.pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowBorrowRecord extends JFrame {
    int i = 0;

    public ShowBorrowRecord(ResultSet result) throws SQLException {
        //设置窗口
        super("借阅记录");
        Container container = this.getContentPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 1000, 800);
        //设置标签
        JLabel label = new JLabel("选中要借阅的书,点击\"确定\"按钮可退出: ");
        label.setBounds(100, 20, 500, 40);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setForeground(new Color(255, 255, 255));
        container.add(label);
        // 表头（列名）
        Object[][] rowData1 = new Object[100][7];

        String[] columnNames = {"SNID", "书名", "借阅者", "借书时间", "还书时间"};

        while (result.next()) {
            //获取信息
            int id = result.getInt("id");
            String bookName = result.getString("bookName");
            String borrower = result.getString("borrower");
            Timestamp borrowTime = result.getTimestamp("borrowTime");
            Timestamp returnTime = result.getTimestamp("returnTime");
            String a = new SimpleDateFormat("yyyy-MM-dd").format(borrowTime.getTime());
            String b = "未归还";
            if(returnTime!=null)
                b = new SimpleDateFormat("yyyy-MM-dd").format(returnTime.getTime());

            //放入表格数组中
            rowData1[i][0] = i+1;
            rowData1[i][1] = bookName;
            rowData1[i][2] = borrower;
            rowData1[i][3] = a;
            rowData1[i][4] = b;
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
        JButton button1 = new JButton("确定");
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
}