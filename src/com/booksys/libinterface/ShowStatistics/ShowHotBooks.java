package com.booksys.libinterface.ShowStatistics;

import com.booksys.pojo.*;
import com.booksys.util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ShowHotBooks extends JFrame {
    int i = 0;

    public ShowHotBooks(ResultSet result) throws SQLException {
        //设置窗口
        super("排行榜");
        Container container = this.getContentPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 1000, 800);
        //设置标签
        JLabel label = new JLabel("最受欢迎图书排行榜");
        label.setBounds(360, 50, 500 ,40);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setForeground(new Color(0, 0, 0));
        container.add(label);
        // 表头（列名）
        Object[][] rowData1 = new Object[100][7];

        String[] columnNames = {"排名","书名", "借阅次数"};

        int cnt = 1;
        while (result.next()) {
            //获取信息
            String bookName = result.getString("bookName");
            int count = result.getInt("cnt");

            //放入表格数组中
            rowData1[i][0] = cnt++;
            rowData1[i][1] = bookName;
            rowData1[i][2] = count;
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

        //获得容器
        container.setBackground(new Color(255, 255, 255));
        setBounds(1, 1, 1000, 800);
        container.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        Connection con = null;
//        PreparedStatement pstmt=null;
//        ResultSet resultSet = null;
//        HotBook hotBook = new HotBook();
//        int cnt = 10;
//        try {
//            con = DbUtil.getConnection();
//            String sql= "select bookName,count(*) cnt from borrowrecord,book " +
//                    "where book.id=borrowrecord.bookId group by bookId order by count(*) desc";
//            pstmt = con.prepareStatement(sql);
//            resultSet = pstmt.executeQuery();
//            new ShowHotBooks(resultSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}