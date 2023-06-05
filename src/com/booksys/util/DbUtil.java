package com.booksys.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 链接数据库
 */
public class DbUtil {
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/db_book";
    private static final String dbUserName = "root";
    private static final String dbPassword = "root";

    //只有首次调用getDataSource方法 才会实例化
    private static DataSource dataSource = null;

    public static DataSource getDataSource(){
        if(dataSource==null){
            dataSource=new MysqlDataSource();
            ((MysqlDataSource)dataSource).setUrl(dbUrl);
            ((MysqlDataSource)dataSource).setUser(dbUserName);
            ((MysqlDataSource)dataSource).setPassword(dbPassword);
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void close(ResultSet resultSet, PreparedStatement statement, Connection connection){//释放资源
        try {
            if(resultSet!=null) {
                resultSet.close();
            }
            if(statement!=null) {
                statement.close();
            }
            if(connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("成功");
        }
    }

    public static void main(String[] args) throws SQLException {
        DbUtil dbUtil = new DbUtil();
        DbUtil.getConnection();
    }
}
