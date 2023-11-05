package com.example.uibasic;

import static com.example.uibasic.JsonUtils.parseJsonToDailyList;
import static com.example.uibasic.JsonUtils.parseJsonToEatList;
import static com.example.uibasic.JsonUtils.parseJsonToHealthList;
import static com.example.uibasic.JsonUtils.parseJsonToType1List;
import static com.example.uibasic.JsonUtils.parseJsonToType2List;
import static com.example.uibasic.JsonUtils.readJsonFromFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

//1009
import android.widget.ImageButton;
import android.content.Intent;
//1009 試試看？？
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity3 extends AppCompatActivity {

    //1009
    //private ImageButton add_blueFish;

    //1009 試試看？？
    private ArrayList<GifDrawable> bfList;
    private ArrayAdapter<GifDrawable> bfList_Adapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //json
        // 讀取 JSON 檔案中的 daily 資料
        String jsonDailyData = readJsonFromFile(this,"daily_data.json");
        // 解析 JSON 字串為 ArrayList<Daily> 物件
        ArrayList<Daily> dailyList = parseJsonToDailyList(jsonDailyData);

        // 讀取 JSON 檔案中的 eat 資料
        String jsonEatData = readJsonFromFile(this,"eat_data.json");
        // 解析 JSON 字串為 ArrayList<Eat> 物件
        ArrayList<Eat> eatList = parseJsonToEatList(jsonEatData);

        // 讀取 JSON 檔案中的 health 資料
        String jsonHealthData = readJsonFromFile(this,"health_data.json");
        // 解析 JSON 字串為 ArrayList<Health> 物件
        ArrayList<Health> healthList = parseJsonToHealthList(jsonHealthData);

        // 讀取 JSON 檔案中的 type1 資料
        String jsonType1Data = readJsonFromFile(this,"type1_data.json");
        // 解析 JSON 字串為 ArrayList<Type1> 物件
        ArrayList<Type1> type1List = parseJsonToType1List(jsonType1Data);

        // 讀取 JSON 檔案中的 type2 資料
        String jsonType2Data = readJsonFromFile(this,"type2_data.json");
        // 解析 JSON 字串為 ArrayList<Type2> 物件
        ArrayList<Type2> type2List = parseJsonToType2List(jsonType2Data);

        ArrayList<Combine> combinedList = new ArrayList<>();

        for(int i=0; i<dailyList.size();i++)
        {
            String itemFromList1 = dailyList.get(i).getDaily_name();
            Integer itemFromList2 = 1;
            combinedList.add(new Combine(itemFromList1,itemFromList2));
        }

        for(int i=0; i<eatList.size();i++)
        {
            String itemFromList1 = eatList.get(i).getEat_name();
            Integer itemFromList2 = 2;
            combinedList.add(new Combine(itemFromList1,itemFromList2));
        }

        for(int i=0; i<healthList.size();i++)
        {
            String itemFromList1 = healthList.get(i).getHealth_name();
            Integer itemFromList2 = 3;
            combinedList.add(new Combine(itemFromList1,itemFromList2));
        }

        for(int i=0; i<type1List.size();i++)
        {
            String itemFromList1 = type1List.get(i).getType1_name();
            Integer itemFromList2 = 4;
            combinedList.add(new Combine(itemFromList1,itemFromList2));
        }

        for(int i=0; i<type2List.size();i++)
        {
            String itemFromList1 = type2List.get(i).getType2_name();
            Integer itemFromList2 = 5;
            combinedList.add(new Combine(itemFromList1,itemFromList2));
        }

        System.out.println("SSSSSSSSSSSSSSSSSS "+ combinedList);


        // 初始化 RecyclerView 和 Adapter Daily
        RecyclerView recyclerView1 = findViewById(R.id.recycler_view_data_daily);
        DataDailyAdapter adapter1 = new DataDailyAdapter(this,combinedList, dailyList, eatList, healthList, type1List, type2List);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);
        System.out.println("reeeeeeeeeeeeeeeeeeeeeee "+ combinedList.get(0));

        // 初始化 RecyclerView 和 Adapter Eat
        /*RecyclerView recyclerView2 = findViewById(R.id.recycler_view_data_eat);
        DataEatAdapter adapter2 = new DataEatAdapter(eatList);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(adapter2);

        // 初始化 RecyclerView 和 Adapter Health
        RecyclerView recyclerView3 = findViewById(R.id.recycler_view_data_health);
        DataHealthAdapter adapter3 = new DataHealthAdapter(healthList);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setAdapter(adapter3);

        // 初始化 RecyclerView 和 Adapter Type1
        RecyclerView recyclerView4 = findViewById(R.id.recycler_view_data_type1);
        DataType1Adapter adapter4 = new DataType1Adapter(type1List);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));
        recyclerView4.setAdapter(adapter4);

        // 初始化 RecyclerView 和 Adapter Type2
        RecyclerView recyclerView5 = findViewById(R.id.recycler_view_data_type2);
        DataType2Adapter adapter5 = new DataType2Adapter(type2List);
        recyclerView5.setLayoutManager(new LinearLayoutManager(this));
        recyclerView5.setAdapter(adapter5);*/

//        //1009 試試看？？
//        lvItems = (ListView) findViewById(R.id.lvItems);
//        bfList = new ArrayList<GifDrawable>();
//        bfList_Adapter = new ArrayAdapter<GifDrawable>(this,
//                android.R.layout.simple_list_item_1, bfList);
//        lvItems.setAdapter(bfList_Adapter);


    }

    /*private String readJsonFromFile(String fileName) {
        String json = "";
        try (InputStream inputStream = openFileInput(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private ArrayList<Daily> parseJsonToDailyList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Daily>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    private ArrayList<Eat> parseJsonToEatList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Eat>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    private ArrayList<Health> parseJsonToHealthList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Health>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    private ArrayList<Type1> parseJsonToType1List(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Type1>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    private ArrayList<Type2> parseJsonToType2List(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Type2>>() {}.getType();
        return gson.fromJson(json, listType);
    }*/
}



/*
試試看能不能：
1. 每次按下按鈕 魚都出現在不同位置     （寫在java還是xml？？）
2. 魚一開始是隱形的
*/