package com.example.uibasic;

public class Daily {
    private String userId;
    private String daily_id;
    private String daily_name;
    private Integer daily_goal;
    private Integer daily_done;
    private String daily_color;

    public Daily(String userId, String daily_id, String daily_name, Integer daily_goal, Integer daily_done, String daily_color){

        this.userId=userId;
        this.daily_id=daily_id;
        this.daily_name=daily_name;
        this.daily_goal=daily_goal;
        this.daily_done=daily_done;
        this.daily_color=daily_color;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDaily_id(String daily_id) {
        this.daily_id = daily_id;
    }

    public void setDaily_name(String daily_name) {
        this.daily_name = daily_name;
    }

    public void setDaily_goal(Integer daily_goal) {
        this.daily_goal = daily_goal;
    }

    public void setDaily_done(Integer daily_done) {
        this.daily_done = daily_done;
    }

    public void setDaily_color(String daily_color) {
        this.daily_color = daily_color;
    }

    public String getUserId() {
        return userId;
    }

    public String getDaily_id() {
        return daily_id;
    }

    public String getDaily_name() {
        return daily_name;
    }

    public Integer getDaily_goal() {
        return daily_goal;
    }

    public Integer getDaily_done() {
        return daily_done;
    }

    public String getDaily_color() {
        return daily_color;
    }
    // 构造函数、Getter和Setter方法等
}
