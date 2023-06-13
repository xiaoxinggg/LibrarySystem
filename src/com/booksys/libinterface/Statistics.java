package com.booksys.libinterface;

import com.booksys.pojo.Admin;
import com.booksys.pojo.HotBook;
import com.booksys.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statistics {
    //返回活跃用户个数
    public int getActiveUser() {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        int cnt = 0;
        try {
            con = DbUtil.getConnection();
            String sql= "select * from normaluser";
            pstmt = con.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while(resultSet.next()) {
                cnt++;
            }
            return cnt;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return 0;
    }

    //返回借阅数量
    public int getBorrowSum() {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        int cnt = 0;
        try {
            con = DbUtil.getConnection();
            String sql= "select * from borrowrecord";
            pstmt = con.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while(resultSet.next()) {
                cnt++;
            }
            return cnt;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return 0;
    }

    //最受欢迎图书
    public ResultSet getHotBooks() {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        HotBook hotBook = new HotBook();
        int cnt = 10;
        try {
            con = DbUtil.getConnection();
            String sql= "select bookName,count(*) cnt from borrowrecord,book " +
                    "where book.id=borrowrecord.bookId group by bookId order by count(*) desc";
            pstmt = con.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while(resultSet.next() && cnt!=0) {
                cnt--;
                hotBook.setBookName(resultSet.getString("bookName"));
                hotBook.setCnt(resultSet.getInt("cnt"));
//                System.out.println(hotBook.getBookName() + " " + hotBook.getCnt());
            }
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    public ResultSet getHotUser() {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        HotBook hotBook = new HotBook();
        int cnt = 10;
        try {
            con = DbUtil.getConnection();
            String sql= "select userName,count(*) cnt from borrowrecord,book " +
                    "where normaluser.id=borrowrecord.borrowerId group by borrowerId order by count(*) desc";
            pstmt = con.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while(resultSet.next() && cnt!=0) {
                cnt--;
                hotBook.setBookName(resultSet.getString("userName"));
                hotBook.setCnt(resultSet.getInt("cnt"));
//                System.out.println(hotBook.getBookName() + " " + hotBook.getCnt());
            }
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Statistics st = new Statistics();
//        System.out.println(st.getActiveUser());
        System.out.println(st.getBorrowSum());
        System.out.println(st.getHotBooks());
    }
}
