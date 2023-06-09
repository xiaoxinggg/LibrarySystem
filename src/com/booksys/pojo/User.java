package com.booksys.pojo;

abstract public class User {
    protected int id;
    protected String userName;
    protected String password;
    protected int userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {return userType;}

    public void setUserType(int userType){this.userType = userType;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passwored='" + password + '\'' +
                '}';
    }
//    public abstract Object getPasswored(String text);
}
