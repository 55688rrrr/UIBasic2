package com.example.uibasic;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Daily>> dailyListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Eat>> eatListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Health>> healthListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Type1>> type1ListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Type2>> type2ListLiveData = new MutableLiveData<>();

    // 对应实体类的getter和setter方法

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public void setUser(User user) {
        userLiveData.setValue(user);
    }

    public LiveData<List<Daily>> getDailyListLiveData() {
        return dailyListLiveData;
    }

    public void setDailyList(List<Daily> dailyList) {
        dailyListLiveData.setValue(dailyList);
    }

    public LiveData<List<Eat>> getEatListLiveData() {
        return eatListLiveData;
    }

    public void setEatList(List<Eat> eatList) {
        eatListLiveData.setValue(eatList);
    }

    public LiveData<List<Health>> getHealthListLiveData() {
        return healthListLiveData;
    }

    public void setHealthList(List<Health> healthList) {
        healthListLiveData.setValue(healthList);
    }

    public LiveData<List<Type1>> getType1ListLiveData() {
        return type1ListLiveData;
    }

    public void setType1List(List<Type1> type1List) {
        type1ListLiveData.setValue(type1List);
    }

    public LiveData<List<Type2>> getType2ListLiveData() {
        return type2ListLiveData;
    }

    public void setType2List(List<Type2> type2List) {
        type2ListLiveData.setValue(type2List);
    }

    // 其他实体类的getter和setter方法
}