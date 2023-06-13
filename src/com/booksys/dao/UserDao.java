package com.booksys.dao;

import com.booksys.pojo.*;
import com.booksys.service.impl.UserService;
import com.booksys.util.DbUtil;
import com.booksys.util.JudgeOfDelay;
import com.booksys.util.Remind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao implements UserService {

    //删除用户
    public boolean deleteUser(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DbUtil.getConnection();
            String sql = "update normaluser set isDelete=? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, id);
            int ret = pstmt.executeUpdate();
            if (ret != 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    //普通用户登录
    public NormalUser login(NormalUser normalUser) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        NormalUser user = new NormalUser();
        try {
            con = DbUtil.getConnection();
            String sql= "select * from normaluser where id=? and password=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, normalUser.getId());
            pstmt.setString(2, normalUser.getPassword());
            resultSet = pstmt.executeQuery();
            while(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return null;
    }

    //管理员登录
    public Admin login(Admin admin) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        Admin user = new Admin();
        try {
            con = DbUtil.getConnection();
            String sql= "select * from admin where userName=? and password=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, admin.getUserName());
            pstmt.setString(2, admin.getPassword());
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                System.out.println("1");
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return null;
    }

    //username是unique约束的
    public NormalUser selectByName(String userName) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        NormalUser user = new NormalUser();
        try {
            con=DbUtil.getConnection();
            String sql="select* from normaluser where userName=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return null;
    }

    //注册
    public boolean addUser(User user) {
        if(user instanceof NormalUser) {
            NormalUser normalUser = (NormalUser)user;
            Connection con = null;
            PreparedStatement pstmt = null;
            try {
                con = DbUtil.getConnection();
                String sql = "insert into normaluser values(?,?,?,?,?,0,0)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, normalUser.getId());
                pstmt.setString(2, normalUser.getUserName());
                pstmt.setString(3, normalUser.getPassword());
                pstmt.setDouble(4, normalUser.getBalance());
                pstmt.setInt(5, normalUser.getUserType());
                int ret = pstmt.executeUpdate();
                if(ret != 1) {
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbUtil.close(null, pstmt, con);
            }
            return false;
        } else {
            Connection con = null;
            PreparedStatement pstmt = null;
            if(((Admin)user).getAdminPassword().equals("666666")) {
                try {
                    con = DbUtil.getConnection();
                    String sql = "insert into Admin values(null,?,?)";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, user.getUserName());
                    pstmt.setString(2, user.getPassword());
                    int ret = pstmt.executeUpdate();
                    if(ret != 1) {
                        return false;
                    } else {
                        return true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbUtil.close(null, pstmt, con);
                }
                return false;
            } else {

                return false;
            }
        }
    }

    //是否有图书快要逾期
    public ArrayList<BorrowRecord> isOverDue(NormalUser normalUser) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        ArrayList<BorrowRecord> list = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            String sql = "select * from borrowrecord where borrowerId=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, normalUser.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                BorrowRecord borrowRecord = new BorrowRecord();
                borrowRecord.setBookId(resultSet.getInt("bookId"));
                borrowRecord.setBorrowerId(resultSet.getInt("borrowerId"));
                borrowRecord.setBorrowTime(resultSet.getTimestamp("borrowTime"));
                borrowRecord.setIsReturn(resultSet.getInt("isReturn"));
                //没有逾期且返回的天数小于等于7
                if ((JudgeOfDelay.isExceedTime(borrowRecord.getBorrowTime().getTime()) || Remind.remind(borrowRecord.getBorrowTime().getTime())<=7) && borrowRecord.getIsReturn()==0) {
                    System.out.println(borrowRecord.getBookId()+"  "+borrowRecord.getBorrowTime());
                    list.add(borrowRecord);
                }

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    //交罚款
    public boolean PayFine(NormalUser normalUser, double fine) {
        double balance = new UserDao().getBalanceSql(normalUser)-fine;
        if(balance<0)
            return false;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "update normaluser set balance = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, balance);
            pstmt.setInt(2, normalUser.getId());
            int ret = pstmt.executeUpdate();
            if (ret != 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null, pstmt, con);
        }
        return false;
    }

    //充值
    public boolean Charge(NormalUser normalUser, double money) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "update normaluser set balance = ? where id = ?";
            double balance = getBalanceSql(normalUser)+money;
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, balance);
            pstmt.setInt(2, normalUser.getId());
            int ret = pstmt.executeUpdate();
            if (ret != 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null, pstmt, con);
        }
        return false;
    }

    //获取余额
    public double getBalanceSql(NormalUser normalUser) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DbUtil.getConnection();
            String sql = "select balance from normaluser where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, normalUser.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                double re = resultSet.getDouble("balance");
                System.out.println(re);
                return re;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null, pstmt, con);
        }
        return -1;
    }

    //显示用户基本信息
    public NormalUser showUserInfo(NormalUser normalUser) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet resultSet = null;
        NormalUser user = new NormalUser();
        try {
            con=DbUtil.getConnection();
            String sql="select* from normaluser where userName=? and password=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, normalUser.getUserName());
            pstmt.setString(2, normalUser.getPassword());
            resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setBalance(resultSet.getDouble("balance"));
                user.setTheorySum(resultSet.getInt("theorySum"));
                user.setRealSum(resultSet.getInt("realSum"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(resultSet,pstmt,con);
        }
        return null;
    }

    //用户修改密码
    /*
    * 参数：用户，新密码
    * */
    public boolean modifyPassword(NormalUser normalUser, String newPassword) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UserDao userDao = new UserDao();
        try {
            con = DbUtil.getConnection();
            String sql = "update normaluser set password=? where id=? and password=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, normalUser.getId());
            pstmt.setString(3, normalUser.getPassword());
            int ret = pstmt.executeUpdate();
            if (ret != 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    //
}