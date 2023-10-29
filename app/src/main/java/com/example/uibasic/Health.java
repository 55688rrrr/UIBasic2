package com.example.uibasic;

public class Health {

    private String userId;
    private String health_id;
    private String health_name;
    private String health_goal;
    private String health_done;
    private String health_color;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDaily_id(String daily_id) {
        this.health_id = health_id;
    }

    public void setDaily_name(String daily_name) {
        this.health_name = health_name;
    }

    public void setDaily_goal(String daily_goal) {
        this.health_goal = health_goal;
    }

    public void setDaily_done(String daily_done) {
        this.health_done = health_done;
    }

    public void setDaily_color(String daily_color) {
        this.health_color = health_color;
    }

    public String getUserId() {
        return userId;
    }

    public String getDaily_id() {
        return health_id;
    }

    public String getDaily_name() {
        return health_name;
    }

    public String getDaily_goal() {
        return health_goal;
    }

    public String getDaily_done() {
        return health_done;
    }

    public String getDaily_color() {
        return health_color;
    }
    // 构造函数、Getter和Setter方法等
}
