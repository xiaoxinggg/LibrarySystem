package com.booksys.libinterface.User;

import com.booksys.dao.BookDao;
import com.booksys.dao.BorrowerRecordDao;
import com.booksys.util.DbUtil;
import com.booksys.util.MyDialogDemo;
import com.booksys.pojo.*;
//import com.booksys.util.JudgeOfDelay;
import com.booksys.util.JudgeOfDelay;
import com.booksys.util.Remind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class ReturnBook extends JFrame {
    int i = 0;

    public ReturnBook(ResultSet result, NormalUser normalUser) throws SQLException {
        //设置窗口
        super("查找显示界面");
        Container container = this.getContentPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 1000, 800);
        //设置标签
        JLabel label = new JLabel("选中要归还的书,点击\"归还\"按钮可归还: ");
        label.setBounds(100, 20, 500, 40);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setForeground(new Color(242, 98, 77));
        container.add(label);
        // 表头（列名）
        Object[][] rowData1 = new Object[100][7];

        String[] columnNames = {"序号", "书名", "是否逾期"};

        int cnt = 1;
        while (result.next()) {
            //获取信息
            int bookId = result.getInt("id");
            String bookName = result.getString("bookName");
            String borrower = result.getString("borrower");
            Timestamp date = result.getTimestamp("borrowTime");

            //放入表格数组中
            rowData1[i][0] = cnt;
            rowData1[i][1] = bookName;
            long t = Remind.remind(date.getTime());
            if (t == 2147483647)
                rowData1[i][2] = "未逾期";
            else {
                if (JudgeOfDelay.isExceedTime(date.getTime())) {
                    double fine = new BigDecimal(t*0.1).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();;
                    rowData1[i][2] = "已逾期" + t + "天,需缴费" +  fine + "元";
                } else {
                    rowData1[i][2] = "还有" + t + "天逾期";
                }
            }
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
        scrollPane.setBounds(100, 100, 700, 600);
        // 添加 滚动面板 到 内容面板
        container.add(scrollPane);
        Font font = new Font("黑体", Font.BOLD, 20);

        //创建修改按钮
        JButton button1 = new JButton("归还");
        button1.setBounds(380, 700, 90, 30);
        button1.setFont(font);
        container.add(button1);

        JButton button2 = new JButton("返回");
        button2.setBounds(520, 700, 90, 30);
        button2.setFont(font);
        container.add(button2);

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
                        //书籍被借出, 执行借书的操作,更新数据库中借阅书籍的状态
                        BookDao bookDao = new BookDao();
                        BorrowerRecordDao borrowerRecordDao = new BorrowerRecordDao();
                        try {
                            //构造一个 有书名和借阅者 的借书记录
                            //改变 bookrecored1 书籍的状态
//                            flag = bookDao.returnBook(normalUser, borrowRecord, (String) rowData1[index][1]);
                            flag = bookDao.returnBook((Integer) rowData1[index][0], normalUser.getId());
                            borrowerRecordDao.modifyBorrowerRecord((String) rowData1[index][1], normalUser.getUserName());
                            System.out.println((String) rowData1[index][1]);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        MyDialogDemo myDialogDemo = flag ? new MyDialogDemo("归还成功") : new MyDialogDemo("归还失败");
//                        }
                    }
                } catch (Exception ex) {
                    new MyDialogDemo("未选中行!");
                }
            }
        });

        //返回主菜单
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserFuntion(normalUser);
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
//            con= DbUtil.getConnection();
//            String sql= "select * from book";
//            pstmt = con.prepareStatement(sql);
//            resultSet=pstmt.executeQuery();
//            new ReturnBook(resultSet, new UnderGraduate());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            DbUtil.close(resultSet,pstmt,con);
//        }
//    }
}