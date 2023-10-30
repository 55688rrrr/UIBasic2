package com.example.uibasic;

import java.util.Date;

public class Type1 {

    private String userId;
    private String type1_id;
    private String type1_name;
    private String type1_deadline;
    private Integer type1_done;
    private String type1_color;

    public Type1(String userId, String type1_id, String type1_name, String type1_deadline, Integer type1_done, String type1_color){

        this.userId=userId;
        this.type1_id=type1_id;
        this.type1_name=type1_name;
        this.type1_deadline=type1_deadline;
        this.type1_done=type1_done;
        this.type1_color=type1_color;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setType1_id(String type1_id) {
        this.type1_id = type1_id;
    }

    public void setType1_name(String type1_name) {
        this.type1_name = type1_name;
    }

    public void setType1_deadline(String type1_deadline) {
        this.type1_deadline = type1_deadline;
    }

    public void setType1_done(Integer type1_done) {
        this.type1_done = type1_done;
    }

    public void setType1_color(String type1_color) {
        this.type1_color = type1_color;
    }

    public String getUserId() {
        return userId;
    }

    public String getType1_id() {
        return type1_id;
    }

    public String getType1_name() {
        return type1_name;
    }

    public String getType1_deadline() {
        return type1_deadline;
    }

    public Integer getType1_done() {
        return type1_done;
    }

    public String getType1_color() {
        return type1_color;
    }
    // 构造函数、Getter和Setter方法等
}
