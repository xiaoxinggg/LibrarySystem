package com.booksys.libinterface.Admin;

import com.booksys.dao.AdminDao;
import com.booksys.dao.BookDao;
import com.booksys.dao.UserDao;
import com.booksys.pojo.Admin;
import com.booksys.util.MyDialogDemo;
import com.booksys.pojo.UnderGraduate;
import com.booksys.pojo.User;
import com.booksys.util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class giud extends JFrame{
    int i =0;
    public giud(ResultSet result, Admin admin) throws SQLException {
        //设置窗口
        super("删除用户显示界面");
        Container container = this.getContentPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,1000,800);
        //设置标签
        JLabel label = new JLabel("选中要删除的用户,点击\"删除\"按钮可删除: ");
        label.setBounds(100,20,500,40);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setForeground(new Color(242, 98, 77));
        container.add(label);
        // 表头（列名）
        Object[][] rowData1 = new Object[100][7];

        String[] columnNames = {"id","用户名", "密码", "余额", "用户类型", "借阅数量"};

        int cnt = 1;
        while(result.next()) {


            //获取信息
            int id = result.getInt("id");
            String userName = result.getString("userName");
            int password = result.getInt("password");
            String balance = result.getString("balance");
            double userType = result.getDouble("userType");
            int realSum = result.getInt("realSum");
            int delet = result.getInt("isDelete");
            if(delet == 1)
                continue;


            //放入表格数组中
            rowData1[i][0] = id;
            rowData1[i][1] = userName;
            rowData1[i][2] = password;
            rowData1[i][3] = balance;
            rowData1[i][4] = userType;
            rowData1[i][5] = realSum;

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
        scrollPane.setBounds(100,100,700,600);
        // 添加 滚动面板 到 内容面板
        container.add(scrollPane);
        Font font = new Font("黑体", Font.BOLD, 20);

        //创建修改按钮
        JButton button1 = new JButton("删除");
        button1.setBounds(450,700,80,30);
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
                    System.out.println("index = " + index + " i = " + i);
                    if( index>=i) {
                        new MyDialogDemo("请选中相应行!");
                    }else if((int)rowData1[index][5] > 0){
                        new MyDialogDemo("删除失败, 您还有书未归还");

                    }
                    else {

                        //判断书籍是否已被借出
//                        if ("无".equals(rowData1[index][5])) {
//                            new MyDialogDemo("该书已被借完！");
//                        } else {
                        //书籍被借出, 执行借书的操作,更新数据库中借阅书籍的状态
                        UserDao userDao = new UserDao();
                        try {
                            flag = userDao.deleteUser((int)rowData1[index][0]);
                            new AdminDao().addAccountDelRecord(admin.getId(), (int)rowData1[index][0]);
                            System.out.println((int)rowData1[index][0]);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        MyDialogDemo myDialogDemo = flag ? new MyDialogDemo("删除成功") : new MyDialogDemo("删除失败");
//                        }
                    }
                } catch (Exception ex) {
                    new MyDialogDemo("未选中行!");
                }
            }
        });

        //获得容器
        container.setBackground(new Color(255, 255, 255));
        setBounds(1,1,1000,800);
        container.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con=DbUtil.getConnection();
            String sql="select * from normaluser";
            pstmt = con.prepareStatement(sql);
            resultSet=pstmt.executeQuery();
            new giud(resultSet, new Admin());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
    }
}