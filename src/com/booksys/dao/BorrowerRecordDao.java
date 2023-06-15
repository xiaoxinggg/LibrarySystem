package com.booksys.dao;

import com.booksys.pojo.BorrowRecord;
import com.booksys.pojo.NormalUser;
import com.booksys.pojo.User;
import com.booksys.util.DbUtil;

import java.sql.*;
import java.util.Date;

/**
 * 借阅信息
 * */
public class BorrowerRecordDao {
    //添加借阅信息(这个信息会永远保留)
    public boolean addBorrowerRecord(BorrowRecord borrowRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "insert into borrowrecord values(?,?,?,null,0)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, borrowRecord.getBookId());
            pstmt.setInt(2, borrowRecord.getBorrowerId());
            pstmt.setTimestamp(3, new Timestamp(borrowRecord.getBorrowTime().getTime()));
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
    public boolean modifyBorrowerRecord(int bookId, int borrowerId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "update borrowrecord set returnTime=? where bookId = ? and borrowerId=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
            pstmt.setInt(2, bookId);
            pstmt.setInt(3, borrowerId);

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

    public Boolean IsOverBook(User user) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int a = 0, b = 0;
        try {
            con = DbUtil.getConnection();
            String sql = "select maxbooksum,realSum from normaluser, usertype where normaluser.id=usertype.typeno and normaluser.id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            resultSet = pstmt.executeQuery();
            while(resultSet.next()) {
                a = resultSet.getInt("maxbooksum");
                b = resultSet.getInt("realSum");
            }
            return a<=b ? false : true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null,pstmt,con);
        }
        return false;
    }

    public static void main(String[] args) {
        NormalUser normalUser = new NormalUser();
        normalUser.setId(1);
        new BorrowerRecordDao().IsOverBook(normalUser);
    }
}