package com.example.uibasic;

public class User {
    private String userId;
    private String userName;

    public User(String userId, String userName) {
        this.userId=userId;
        this.userName=userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
    // 构造函数、Getter和Setter方法等
}
