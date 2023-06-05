package com.booksys.dao;

import com.booksys.pojo.Book;
import com.booksys.pojo.BookRecord;
import com.booksys.pojo.NormalUser;
import com.booksys.service.impl.BookService;
import com.booksys.util.DbUtil;
import com.booksys.util.GetClassify;
import com.booksys.util.JudgeOfDelay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements BookService {
    //1.添加图书
    public boolean addBook(Book book){
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "insert into book values(null,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, book.getBookName());
            pstmt.setInt(2, book.getIsbn());
            pstmt.setString(3, book.getAuthor());
            pstmt.setDouble(4, book.getPrice());
            pstmt.setInt(5, book.getNum());
            pstmt.setInt(6, GetClassify.getClassify(book.getIsbn()));
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

    //2.查看所有图书
    public List<Book> selectAll() {
        List<Book> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DbUtil.getConnection();
            String sql = "select * from book";
            pstmt = con.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setIsbn(resultSet.getInt("isbn"));
                book.setBookName(resultSet.getString("BookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getInt("price"));
                book.setNum(resultSet.getInt("num"));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(resultSet, pstmt, con);
        }
        return list;
    }

    //4.删除书籍
    public boolean deleteBook(String bookName) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.getConnection();
            String sql = "delete from book where bookName = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookName);
            int ret = pstmt.executeUpdate();
            return ret == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null, pstmt, con);
        }
        return false;
    }

    //5.借书
    public boolean borrowBook(String bookName) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DbUtil.getConnection();
            String sql = "update book set num=num-1 where bookName = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookName);
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

    //还书
    public boolean returnBook(NormalUser normalUser, BookRecord bookRecord, String bookName) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UserDao userDao = new UserDao();
        try {
            con = DbUtil.getConnection();
            String sql = "update book set num=num+1 where bookName = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookName);

            if (JudgeOfDelay.isExceedTime(bookRecord.getDate().getTime()))
                if (userDao.PayFine(normalUser, bookRecord)) {
                    System.out.println("缴费成功,余额为" + normalUser.getBalance());
                } else {
                    System.out.println("余额不足");
                    return false;
                }
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

    //修改书名
    public boolean modifyBookName(int id, String bookName) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UserDao userDao = new UserDao();
        try {
            con = DbUtil.getConnection();
            String sql = "update book set bookName=? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookName);
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

    //修改作者
    public boolean modifyAuthor(int id, String Author) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UserDao userDao = new UserDao();
        try {
            con = DbUtil.getConnection();
            String sql = "update book set author=? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Author);
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

    //修改isbn
    public boolean modifyISBN(int id, int isbn) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UserDao userDao = new UserDao();
        try {
            con = DbUtil.getConnection();
            String sql = "update book set isbn=? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, isbn);
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

    //修改价格
    public boolean modifyPrice(int id, double price) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UserDao userDao = new UserDao();
        try {
            con = DbUtil.getConnection();
            String sql = "update book set price=? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, price);
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

    //修改书本数量
    public boolean modifyNum(int id, int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UserDao userDao = new UserDao();
        try {
            con = DbUtil.getConnection();
            String sql = "update book set num = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
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

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        bookDao.modifyNum(16, 15);
    }
}