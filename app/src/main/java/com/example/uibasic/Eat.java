package com.example.uibasic;

public class Eat {

    private String userId;
    private String eat_id;
    private String eat_name;
    private Integer eat_goal;
    private Integer eat_done;
    private String eat_color;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEat_id(String eat_id) {
        this.eat_id = eat_id;
    }

    public void setEat_name(String eat_name) {
        this.eat_name = eat_name;
    }

    public void setEat_goal(Integer eat_goal) {
        this.eat_goal = eat_goal;
    }

    public void setEat_done(Integer eat_done) {
        this.eat_done = eat_done;
    }

    public void setEat_color(String eat_color) {
        this.eat_color = eat_color;
    }

    public String getUserId() {
        return userId;
    }

    public String getEat_id() {
        return eat_id;
    }

    public String getEat_name() {
        return eat_name;
    }

    public Integer getEat_goal() {
        return eat_goal;
    }

    public Integer getEat_done() {
        return eat_done;
    }

    public String getEat_color() {
        return eat_color;
    }
    // 构造函数、Getter和Setter方法等
}
