package com.example.uibasic;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private List<User> userList;
    private List<Daily> dailyList;
    private List<Eat> eatList;
    private List<Health> healthList;
    private List<Type1> type1List;
    private List<Type2> type2List;

    // 对应实体类的getter和setter方法

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Daily> getDailyList() {
        return dailyList;
    }

    public void setDailyList(List<Daily> dailyList) {
        this.dailyList = dailyList;
    }

    public List<Eat> getEatList() {
        return eatList;
    }

    public void setEatList(List<Eat> eatList) {
        this.eatList = eatList;
    }

    public List<Health> getHealthList() {
        return healthList;
    }

    public void setHealthList(List<Health> healthList) {
        this.healthList = healthList;
    }

    public List<Type1> getType1List() {
        return type1List;
    }

    public void setType1List(List<Type1> type1List) {
        this.type1List = type1List;
    }

    public List<Type2> getType2List() {
        return type2List;
    }

    public void setType2List(List<Type2> type2List) {
        this.type2List = type2List;
    }

    // 其他实体类的getter和setter方法
}