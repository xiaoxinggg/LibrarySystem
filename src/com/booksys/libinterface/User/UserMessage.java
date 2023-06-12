package com.booksys.libinterface.User;

import com.booksys.pojo.NormalUser;
import com.booksys.util.JudgeOfDelay;
import com.booksys.util.Remind;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserMessage extends JFrame {
    int i = 0;

    public UserMessage(ResultSet result, NormalUser normalUser) throws SQLException {
        //设置窗口
        super("信息查询");
        Container container = this.getContentPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 1000, 400);
        //设置标签
        JLabel label = new JLabel("信息查询");
        label.setBounds(100, 20, 500, 40);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setForeground(new Color(242, 98, 77));
        container.add(label);
        // 表头（列名）
        Object[][] rowData1 = new Object[2][7];

        String[] columnNames = {"id","名字", "当前余额", "可借阅数量"};

        int cnt = 1;
        while (result.next()) {
            //获取信息
            int id = result.getInt("id");
            String userName = result.getString("userName");
            String balance = result.getString("balance");
            int sum = result.getInt("theorySum");

            //放入表格数组中
            rowData1[i][0] = id;
            rowData1[i][1] = userName;
            rowData1[i][2] = balance;
            rowData1[i][3] = sum;
//            rowData1[i][2] = borrower;
//            rowData1[i][3] = date;
            i++;
            cnt++;
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
        scrollPane.setBounds(100, 100, 700, 100);
        // 添加 滚动面板 到 内容面板
        container.add(scrollPane);
        Font font = new Font("黑体", Font.BOLD, 20);

//        //创建修改按钮
//        JButton button1 = new JButton("归还");
//        button1.setBounds(380, 700, 90, 30);
//        button1.setFont(font);
//        container.add(button1);
//
//        JButton button2 = new JButton("返回");
//        button2.setBounds(520, 700, 90, 30);
//        button2.setFont(font);
//        container.add(button2);
        //获得容器
        container.setBackground(new Color(255, 255, 255));
        setBounds(100, 100, 1000, 400);
        container.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}