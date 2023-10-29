package com.example.uibasic;

public class Health {

    private String userId;
    private String health_id;
    private String health_name;
    private Integer health_goal;
    private Integer health_done;
    private String health_color;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHealth_id(String health_id) {
        this.health_id = health_id;
    }

    public void setHealth_name(String health_name) {
        this.health_name = health_name;
    }

    public void setHealth_goal(Integer health_goal) {
        this.health_goal = health_goal;
    }

    public void setHealth_done(Integer health_done) {
        this.health_done = health_done;
    }

    public void setHealth_color(String health_color) {
        this.health_color = health_color;
    }

    public String getUserId() {
        return userId;
    }

    public String getHealth_id() {
        return health_id;
    }

    public String getHealth_name() {
        return health_name;
    }

    public Integer getHealth_goal() {
        return health_goal;
    }

    public Integer getHealth_done() {
        return health_done;
    }

    public String getHealth_color() {
        return health_color;
    }
    // 构造函数、Getter和Setter方法等
}
