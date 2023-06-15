package com.booksys.dao;

import com.booksys.util.DbUtil;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class AdminDao {
    public void addAccountDelRecord(int adminId, int userId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "insert accountdelrecord values (?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, adminId);
            pstmt.setInt(2, userId);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null, pstmt, con);
        }
    }
}
