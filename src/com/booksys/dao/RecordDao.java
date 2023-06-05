package com.booksys.dao;

import com.booksys.pojo.BookRecord;
import com.booksys.pojo.NormalUser;
import com.booksys.util.DbUtil;
//import com.booksys.util.JudgeOfDelay;

import java.sql.*;

/**
 * 书库信息
 * */
public class RecordDao {
    //添加书库信息
    public boolean addRecord(BookRecord bookRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "insert into bookrecord values(null,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookRecord.getBookName());
            pstmt.setString(2, bookRecord.getBorrower());
            pstmt.setTimestamp(3, new Timestamp(bookRecord.getDate().getTime()));
//            pstmt.setTimestamp(3, bookRecord.getLocalDate());
            int ret = pstmt.executeUpdate();
            if(ret != 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null,pstmt,con);
        }
        return false;
    }

    //根据书名和借书人找借书记录
    public BookRecord selectByBookRecord(String bookName, String borrower) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet=null;
        BookRecord bookRecord1 = new BookRecord();
        try {
            con=DbUtil.getConnection();
            String sql="select* from bookrecord where bookName=? and borrower=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookName);
            pstmt.setString(2, borrower);
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                bookRecord1.setId(resultSet.getInt("id"));
                bookRecord1.setBookName(resultSet.getString("BookName"));
                bookRecord1.setBorrower(resultSet.getString("borrower"));
                bookRecord1.setDate(resultSet.getTimestamp("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return bookRecord1;
    }

    //根据借书人找借书记录
    public BookRecord selectByBorrower(BookRecord bookRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet=null;
        BookRecord bookRecord1 = new BookRecord();
        try {
            con=DbUtil.getConnection();
            String sql="select * from bookrecord where bookName=? and borrower=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookRecord.getBookName());
            pstmt.setString(2, bookRecord.getBorrower());
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                bookRecord1.setId(resultSet.getInt("id"));
                bookRecord1.setBookName(resultSet.getString("BookName"));
                bookRecord1.setBorrower(resultSet.getString("borrower"));
                bookRecord1.setDate(resultSet.getTimestamp("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return bookRecord1;
    }

    //删除用户的借书记录（用户查看自己目前借的书是就看不到了）
    public boolean deleteRecord(NormalUser normalUser, BookRecord bookRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "delete from bookrecord where bookName = ? and borrower = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookRecord.getBookName());
            pstmt.setString(2, bookRecord.getBorrower());
            int ret = pstmt.executeUpdate();
            return ret == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(null,pstmt,con);
        }
        return false;
    }
}