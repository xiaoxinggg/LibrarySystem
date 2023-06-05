package com.booksys.dao;

import com.booksys.pojo.BorrowRecord;
import com.booksys.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 借阅信息
 * */
public class BorrowerRecordDao {
    //添加借阅信息(这个信息会永远保留，不提供给用户查看)
    public boolean addBorrowerRecord(BorrowRecord borrowRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "insert into borrowrecord values(null,?,?,?,null)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, borrowRecord.getBookName());
            pstmt.setString(2, borrowRecord.getBorrower());
            pstmt.setTimestamp(3, new Timestamp(borrowRecord.getBorrowerTime().getTime()));
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

    //修改借阅信息(这个信息会永远保留，不提供给用户查看)
    public boolean modifyBorrowerRecord(String bookName, String borrower) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "update borrowrecord set returnTime=? where bookName = ? and borrower=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
            pstmt.setString(2, bookName);
            pstmt.setString(3, borrower);

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
}