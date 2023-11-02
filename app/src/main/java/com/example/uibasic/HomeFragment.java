package com.example.uibasic;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

//0806
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;
//0807
import android.widget.ImageButton;
//????
import android.content.Intent;

import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
//public class HomeFragment extends FragmentActivity {
    GifImageView barrel_gif, seaweed_gif, seaweed_gif_2;
    GifImageView fish_gif, fish_gif_2, bw_fish, bw_fish_2;
    ImageButton mdbt, rtbt, addFish;
    GifImageView blue_fish_0;


    //高昂寫好的！
    private ImageButton addFish;
    private ArrayList<Daily> dailyList;
    private Connection con;
    private View view;
    //private FrameLayout container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 在這裡創建 SaveDataToFileTask 的實例，並傳入 HomeFragment 的 Activity
        view = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化控制元件
        //GifImageView blue_fish_0 = view.findViewById(R.id.blue_fish_0);
        //GifImageView fish_gif = view.findViewById(R.id.fish_gif);
        //GifImageView fish_gif_2 = view.findViewById(R.id.fish_gif_2);
        //GifImageView bw_fish = view.findViewById(R.id.bw_fish);
        //GifImageView bw_fish_2 = view.findViewById(R.id.bw_fish_2);
        GifImageView seaweed_gif = view.findViewById(R.id.seaweed_gif);
        GifImageView barrel_gif = view.findViewById(R.id.barrel_gif);
        GifImageView seaweed_gif_2 = view.findViewById(R.id.seaweed_gif_2);

        //json
        // 讀取 JSON 檔案中的 daily 資料
        String jsonDailyData = readJsonFromFile("daily_data.json");
        // 解析 JSON 字串為 ArrayList<Daily> 物件
        ArrayList<Daily> dailyList = parseJsonToDailyList(jsonDailyData);
        System.out.println("jsonnnnnnnnnnnnn "+ dailyList.size());

        // 讀取 JSON 檔案中的 eat 資料
        String jsonEatData = readJsonFromFile("eat_data.json");
        // 解析 JSON 字串為 ArrayList<Eat> 物件
        ArrayList<Eat> eatList = parseJsonToEatList(jsonEatData);
        System.out.println("jsonnnnnnnnnnnnn "+ eatList.size());

        // 讀取 JSON 檔案中的 health 資料
        String jsonHealthData = readJsonFromFile("health_data.json");
        // 解析 JSON 字串為 ArrayList<Health> 物件
        ArrayList<Health> healthList = parseJsonToHealthList(jsonHealthData);
        System.out.println("jsonnnnnnnnnnnnn "+ healthList.size());

        // 讀取 JSON 檔案中的 type1 資料
        String jsonType1Data = readJsonFromFile("type1_data.json");
        // 解析 JSON 字串為 ArrayList<Type1> 物件
        ArrayList<Type1> type1List = parseJsonToType1List(jsonType1Data);
        System.out.println("jsonnnnnnnnnnnnn "+ type1List.size());

        // 讀取 JSON 檔案中的 type2 資料
        String jsonType2Data = readJsonFromFile("type2_data.json");
        // 解析 JSON 字串為 ArrayList<Type2> 物件
        ArrayList<Type2> type2List = parseJsonToType2List(jsonType2Data);
        System.out.println("jsonnnnnnnnnnnnn "+ type2List.size());


        /*this.container = view.findViewById(R.id.container);

        // 控制魚的數量
        int fishCount = 10;

        // 動態生成多個魚
        for (int i = 0; i < fishCount; i++) {
            // 創建新的ImageView
            ImageView fish = new ImageView(requireContext());
            fish.setImageResource(R.drawable.fish._1); // 設定魚的圖片


            // 控制魚的大小（這裡使用LayoutParams設定寬高為100dp）
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            fish.setLayoutParams(params);

            // 控制魚的隨機位置
            Random random = new Random();
            int x = random.nextInt(this.container.getWidth()); // 隨機X坐標
            int y = random.nextInt(this.container.getHeight()); // 隨機Y坐標
            fish.setX(x);
            fish.setY(y);

            // 將ImageView添加到FrameLayout中
            this.container.addView(fish);
        }*/

        //daily fish
        int gifCount = dailyList.size();
        System.out.println(gifCount);

        // 動態添加GifImageView到RelativeLayout
        RelativeLayout relativeLayout = view.findViewById(R.id.linearLayout);
        for (int i = 0; i < gifCount; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());
            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 300;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout.addView(gifImageView);
        }

        //Eat fish
        int gifCount1 = eatList.size();
        System.out.println(gifCount1);

        // 動態添加GifImageView到RelativeLayout
        RelativeLayout relativeLayout1 = view.findViewById(R.id.linearLayout);
        for (int i = 0; i < gifCount1; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());
            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.black_white_fish); // 替換為你的Gif圖片資源
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 300;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout1.addView(gifImageView);
        }

        //Health fish
        int gifCount2 = healthList.size();
        System.out.println(gifCount2);

        // 動態添加GifImageView到RelativeLayout
        RelativeLayout relativeLayout2 = view.findViewById(R.id.linearLayout);
        for (int i = 0; i < gifCount2; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());
            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 300;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout2.addView(gifImageView);
        }

        //Type1 fish
        int gifCount3 = type1List.size();
        System.out.println(gifCount3);

        // 動態添加GifImageView到RelativeLayout
        RelativeLayout relativeLayout3 = view.findViewById(R.id.linearLayout);
        for (int i = 0; i < gifCount3; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());
            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 300;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout3.addView(gifImageView);
        }

        //Type2 fish
        int gifCount4 = type2List.size();
        System.out.println(gifCount4);

        // 動態添加GifImageView到RelativeLayout
        RelativeLayout relativeLayout4 = view.findViewById(R.id.linearLayout);
        for (int i = 0; i < gifCount4; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());
            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 300;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout4.addView(gifImageView);
        }


        // 隨機魚按鈕
        /*addFish = view.findViewById(R.id.imageButton3);
        addFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blue_fish_0.setVisibility(View.VISIBLE);
                blue_fish_0.setX((int) (Math.random() * 1000));
                blue_fish_0.setY((int) (Math.random() * 2000));
                try {
                    GifDrawable blueFish0 = new GifDrawable(getResources(), R.drawable.blue_fish);
                    blue_fish_0.setImageDrawable(blueFish0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/

        ImageButton mdbt = view.findViewById(R.id.imageButton);
        mdbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), login_page.class);
                startActivity(intent);
            }
        });

        ImageButton rtbt = view.findViewById(R.id.imageButton2);
        rtbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                startActivity(intent);
            }
        });


        return view;
    }


    private String readJsonFromFile(String fileName) {
        String json = "";
        try (InputStream inputStream = getActivity().openFileInput(fileName);
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
    }
}

/* 之前寫好的，merge後先被隱藏了... 再看看跑的情況如何吧
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //隱藏事先寫好的xml
        blue_fish_0 = (GifImageView) rootView.findViewById(R.id.blue_fish_0);
        blue_fish_0.setVisibility(View.INVISIBLE);


        //背景們
        seaweed_gif = (GifImageView) rootView.findViewById(R.id.seaweed_gif);
        barrel_gif = (GifImageView) rootView.findViewById(R.id.barrel_gif);
        seaweed_gif_2 = (GifImageView) rootView.findViewById(R.id.seaweed_gif_2);
        fish_gif = (GifImageView) rootView.findViewById(R.id.fish_gif);
        fish_gif_2 = (GifImageView) rootView.findViewById(R.id.fish_gif_2);
        bw_fish = (GifImageView) rootView.findViewById(R.id.bw_fish);
        bw_fish_2 = (GifImageView) rootView.findViewById(R.id.bw_fish_2);
        try {
            GifDrawable gifSeaweed = new GifDrawable(getResources(), R.drawable.seaweed_1);
            GifDrawable gifBarrel = new GifDrawable(getResources(), R.drawable.barrel_1);
            GifDrawable gifSeaweed2 = new GifDrawable(getResources(), R.drawable.seaweed_1);
            seaweed_gif.setImageDrawable(gifSeaweed);
            barrel_gif.setImageDrawable(gifBarrel);
            seaweed_gif_2.setImageDrawable(gifSeaweed2);
            GifDrawable gifFish = new GifDrawable(getResources(), R.drawable.blue_fish);
            fish_gif.setImageDrawable(gifFish);
            GifDrawable gifFish2 = new GifDrawable(getResources(), R.drawable.blue_fish);
            fish_gif_2.setImageDrawable(gifFish2);
            GifDrawable bwFish = new GifDrawable(getResources(), R.drawable.black_white_fish);
            bw_fish.setImageDrawable(bwFish);
            GifDrawable bwFish2 = new GifDrawable(getResources(), R.drawable.black_white_fish);
            bw_fish_2.setImageDrawable(bwFish2);
        } catch (Exception e) {
            e.printStackTrace();
        }



        //三個按鈕
        mdbt = rootView.findViewById(R.id.imageButton);
        mdbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), login_page.class);
                startActivity(intent);
            }
        });

        rtbt = rootView.findViewById(R.id.imageButton2);
        rtbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity3.class);
                startActivity(intent);
            }
        });

        addFish = rootView.findViewById(R.id.imageButton3);
        addFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blue_fish_0.setVisibility(View.VISIBLE);
                blue_fish_0.setX((int) (Math.random() * 1000));
                blue_fish_0.setY((int) (Math.random() * 2000));
                try {
                    GifDrawable blueFish0 = new GifDrawable(getResources(), R.drawable.blue_fish);
                    blue_fish_0.setImageDrawable(blueFish0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;

    }



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home);
//
//        GifImageView fish_gif = View.findViewById(R.id.fish_gif);
//        try {
//            GifDrawable gifFish = new GifDrawable(getResources(), R.drawable.blue_fish);
//            fish_gif.setImageDrawable(gifFish);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ImageButton mdbt = findViewById(R.id.imageButton);
//        mdbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext().class, login_page.class);
//                startActivity(intent);
//            }
//        });
//    }


}
*/



/*
--- 待辦 ---
從右游到左（原只有由左到右）
---
* 先寫好多個🐟ㄉxml？應該可以randomly選取要呼叫哪一個檔案？？？？
* 不要硬寫gif上去？
* 懸浮視窗？？（像頁面那樣可以輸入文字之類的，不要只是寫死的訊息...）
*/

/*
總結到10/19 目前的功能：
1. 主畫面中間的按鈕可以跳去登入／註冊頁面，可跳出鍵盤輸入文字，跳出登入／註冊成功的訊息 *連結資料庫後檢驗正確性*
2. 主畫面右邊的按鈕連接新增魚的頁面，點了要新增的魚種就跳回主頁面 *應該要能輸入名稱之類的？？*
3. 主畫面左邊的按鈕暫作為有新增功能，按一下會顯示出寫好的xml（一開始先隱藏）；出現位置隨機 *所以盡量不要用寫死的gif...🥲*
*/