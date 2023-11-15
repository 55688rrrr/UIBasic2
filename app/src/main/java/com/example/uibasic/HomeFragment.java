package com.example.uibasic;

import static com.example.uibasic.JsonUtils.parseJsonToDailyList;
import static com.example.uibasic.JsonUtils.readJsonFromFile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

//0806
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;
//0807
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
//????
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeFragment extends Fragment {


    private ImageButton addFish;

    private ArrayList<Daily> dailyList;

    private ArrayList<User> userList;

    private Connection con;

    private View view;

    private String BiguserId;

    private Integer gifCount = 0;
    private Integer gifCount1 = 0;
    private Integer gifCount2 = 0;
    private Integer gifCount3 = 0;
    private Integer gifCount4 = 0;

    //private Context context;

    //private EditText missionDeadlineInput;


    //private FrameLayout container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 在這裡創建 SaveDataToFileTask 的實例，並傳入 HomeFragment 的 Activity

        view = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化控制元件
        GifImageView seaweed_gif = view.findViewById(R.id.seaweed_gif);
        GifImageView barrel_gif = view.findViewById(R.id.barrel_gif);
        GifImageView seaweed_gif_2 = view.findViewById(R.id.seaweed_gif_2);

        ImageButton mdbt = view.findViewById(R.id.imageButton);
        ImageButton share = view.findViewById(R.id.imageButton2);
        ImageButton share_back = view.findViewById(R.id.share_back);
        share_back.setVisibility(View.GONE);
        ImageButton new_fish = view.findViewById(R.id.imageButton3);


        //json

        // 讀取 JSON 檔案中的 user 資料
        String jsonuserData = JsonUtils.readJsonFromFile(getActivity(),"user_data.json");
        // 解析 JSON 字串為 ArrayList<Daily> 物件
        User biguser = JsonUtils.parseJsonToUser(jsonuserData);

        // 讀取 JSON 檔案中的 daily 資料
        String jsonDailyData = JsonUtils.readJsonFromFile(getActivity(),"daily_data.json");
        // 解析 JSON 字串為 ArrayList<Daily> 物件
        ArrayList<Daily> dailyList = JsonUtils.parseJsonToDailyList(jsonDailyData);
        int lastIndex_daily = dailyList.size() - 1;

        BiguserId= biguser.getUserId();

        System.out.println("jsonnnnnnnnnnnnn "+ dailyList.size());

        // 讀取 JSON 檔案中的 eat 資料
        String jsonEatData = JsonUtils.readJsonFromFile(getActivity(),"eat_data.json");
        // 解析 JSON 字串為 ArrayList<Eat> 物件
        ArrayList<Eat> eatList = JsonUtils.parseJsonToEatList(jsonEatData);
        int lastIndex_eat = eatList.size() - 1;
        System.out.println("jsonnnnnnnnnnnnn "+ eatList.size());

        // 讀取 JSON 檔案中的 health 資料
        String jsonHealthData = JsonUtils.readJsonFromFile(getActivity(),"health_data.json");
        // 解析 JSON 字串為 ArrayList<Health> 物件
        ArrayList<Health> healthList = JsonUtils.parseJsonToHealthList(jsonHealthData);
        int lastIndex_health = healthList.size() - 1;
        System.out.println("jsonnnnnnnnnnnnn "+ healthList.size());

        // 讀取 JSON 檔案中的 type1 資料
        String jsonType1Data = JsonUtils.readJsonFromFile(getActivity(),"type1_data.json");
        // 解析 JSON 字串為 ArrayList<Type1> 物件
        ArrayList<Type1> type1List = JsonUtils.parseJsonToType1List(jsonType1Data);
        int lastIndex_type1 = type1List.size() - 1;
        System.out.println("jsonnnnnnnnnnnnn "+ type1List.size());

        // 讀取 JSON 檔案中的 type2 資料
        String jsonType2Data = JsonUtils.readJsonFromFile(getActivity(),"type2_data.json");
        // 解析 JSON 字串為 ArrayList<Type2> 物件
        ArrayList<Type2> type2List = JsonUtils.parseJsonToType2List(jsonType2Data);
        int lastIndex_type2 = type2List.size() - 1;
        System.out.println("jsonnnnnnnnnnnnn "+ type2List.size());

        //檢查是否超過七天
        // 获取今天的日期
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // 将今天的日期转换为字符串，假设日期格式为 "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String todayDateString = dateFormat.format(currentDate);

        //daily 檢查
        Iterator<Daily> dailyIterator = dailyList.iterator();
        boolean dataChanged = false;

        while (dailyIterator.hasNext()) {
            Daily daily = dailyIterator.next();
            String dailyColor = daily.getDaily_color();

            try {
                Date dailyDate = dateFormat.parse(dailyColor);
                calendar.setTime(dailyDate);

                // 使用 Calendar 的 add 方法判断日期是否超过七天
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                Date sevenDaysLater = calendar.getTime();
                System.out.println("not nullllllllllllllllllllllllllllll");

                // 如果 dailyDate 在 sevenDaysLater 之前，从列表中移除该项
                if (sevenDaysLater.before(currentDate)) {
                    dailyIterator.remove();
                    dataChanged = true;

                }
            } catch (ParseException e) {
                e.printStackTrace();
                // 处理日期解析异常
            }
        }

        // 遍历 eatList，执行相同的操作
        Iterator<Eat> eatIterator = eatList.iterator();
        boolean dataChanged1 = false;
        while (eatIterator.hasNext()) {
            Eat eat = eatIterator.next();
            String eatColor = eat.getEat_color();

            try {
                Date eatDate = dateFormat.parse(eatColor);
                calendar.setTime(eatDate);

                // 使用 Calendar 的 add 方法判断日期是否超过七天
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                Date sevenDaysLater = calendar.getTime();

                // 如果 eatDate 在 sevenDaysLater 之前，从列表中移除该项
                if (sevenDaysLater.before(currentDate)) {
                    eatIterator.remove();
                    dataChanged1 = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // 处理日期解析异常
            }
        }

        // 遍历 healthList，执行相同的操作
        Iterator<Health> healthIterator = healthList.iterator();
        boolean dataChanged2 = false;
        while (healthIterator.hasNext()) {
            Health health = healthIterator.next();
            String healthColor = health.getHealth_color();

            try {
                Date healthDate = dateFormat.parse(healthColor);
                calendar.setTime(healthDate);

                // 使用 Calendar 的 add 方法判断日期是否超过七天
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                Date sevenDaysLater = calendar.getTime();

                // 如果 healthDate 在 sevenDaysLater 之前，从列表中移除该项
                if (sevenDaysLater.before(currentDate)) {
                    healthIterator.remove();
                    dataChanged2 = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // 处理日期解析异常
            }
        }

        // 遍历 type1List，检查每一项的 type1_deadline 是否在今天日期之前
        Iterator<Type1> type1Iterator = type1List.iterator();
        boolean dataChanged3 = false;
        while (type1Iterator.hasNext()) {
            Type1 type1 = type1Iterator.next();
            String type1Deadline = type1.getType1_deadline();

            try {
                Date deadlineDate = dateFormat.parse(type1Deadline);

                // 如果 deadlineDate 在今天日期之前，从列表中移除该项
                if (deadlineDate.before(currentDate)) {
                    type1Iterator.remove();
                    dataChanged3 = true;
                    System.out.println("has been deleted!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println(currentDate);
                    System.out.println(deadlineDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // 处理日期解析异常
            }
        }

        // 遍历 type2List，执行相同的操作
        Iterator<Type2> type2Iterator = type2List.iterator();
        boolean dataChanged4 = false;
        while (type2Iterator.hasNext()) {
            Type2 type2 = type2Iterator.next();
            String type2Color = type2.getDaily_color();

            try {
                Date type2Date = dateFormat.parse(type2Color);
                calendar.setTime(type2Date);

                // 使用 Calendar 的 add 方法判断日期是否超过七天
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                Date sevenDaysLater = calendar.getTime();

                // 如果 type2Date 在 sevenDaysLater 之前，从列表中移除该项
                if (sevenDaysLater.before(currentDate)) {
                    type2Iterator.remove();
                    dataChanged4 = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // 处理日期解析异常
            }
        }

        // 保存 每個List 到 JSON 文件
        if (dataChanged) {
            JsonUtils.saveDataToJsonFile(getActivity(), "daily_data.json", dailyList);
        }
        if (dataChanged1) {
            JsonUtils.saveDataToJsonFile(getActivity(), "eat_data.json", eatList);
        }
        if (dataChanged2) {
            JsonUtils.saveDataToJsonFile(getActivity(), "health_data.json", healthList);
        }
        if (dataChanged3) {
            JsonUtils.saveDataToJsonFile(getActivity(), "type1_data.json", type1List);
        }
        if (dataChanged4) {
            JsonUtils.saveDataToJsonFile(getActivity(), "type2_data.json", type2List);
        }


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
        gifCount = dailyList.size();
        System.out.println(gifCount);

        // 動態添加GifImageView到RelativeLayout
        RelativeLayout relativeLayout = view.findViewById(R.id.linearLayout);
        for (int i = 0; i < gifCount; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());

            // 取得該位置的 Daily
            Daily daily = dailyList.get(i);
            // 根据比例计算鱼的大小
            int dailyDone = daily.getDaily_done();
            int dailyGoal = daily.getDaily_goal();
            double progressRatio = (double) dailyDone / dailyGoal;

            // 計算魚的大小
            int baseSize = 2000;
            int width, height;

            // 根據 progressRatio 範圍設定魚的大小
            if (progressRatio < 1.0 / 3) {
                width = (int) (baseSize * 1.0 / 3);
                height = (int) (baseSize * 1.0 / 3);
            } else if (progressRatio < 2.0 / 3) {
                width = (int) (baseSize * 2.0 / 3);
                height = (int) (baseSize * 2.0 / 3);
            } else {
                width = baseSize;
                height = baseSize;
            }

            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout.addView(gifImageView);
        }

        //Eat fish
        gifCount1 = eatList.size();
        System.out.println(gifCount1);

        // 動態添加GifImageView到RelativeLayout

        for (int i = 0; i < gifCount1; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());

            // 取得該位置的 Eat
            Eat eat = eatList.get(i);
            // 根据比例计算鱼的大小
            int eatDone = eat.getEat_done();
            int eatGoal = eat.getEat_goal();
            double progressRatio = (double) eatDone / eatGoal;

            // 計算魚的大小
            int baseSize = 40000;
            int width, height;

            // 根據 progressRatio 範圍設定魚的大小
            if (progressRatio < 1.0 / 3) {
                width = (int) (baseSize * 1.0 / 3);
                height = (int) (baseSize * 1.0 / 3);
            } else if (progressRatio < 2.0 / 3) {
                width = (int) (baseSize * 2.0 / 3);
                height = (int) (baseSize * 2.0 / 3);
            } else {
                width = baseSize;
                height = baseSize;
            }

            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.black_white_fish); // 替換為你的Gif圖片資源
            //int width = ViewGroup.LayoutParams.MATCH_PARENT;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout.addView(gifImageView);
        }

        //Health fish
        gifCount2 = healthList.size();
        System.out.println(gifCount2);

        // 動態添加GifImageView到RelativeLayout

        for (int i = 0; i < gifCount2; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());

            // 取得該位置的 Health
            Health health = healthList.get(i);
            // 根据比例计算鱼的大小
            int healthDone = health.getHealth_done();
            int healthGoal = health.getHealth_goal();
            double progressRatio = (double) healthDone / healthGoal;

            // 計算魚的大小
            int baseSize = 2000;
            int width, height;

            // 根據 progressRatio 範圍設定魚的大小
            if (progressRatio < 1.0 / 3) {
                width = (int) (baseSize * 1.0 / 3);
                height = (int) (baseSize * 1.0 / 3);
            } else if (progressRatio < 2.0 / 3) {
                width = (int) (baseSize * 2.0 / 3);
                height = (int) (baseSize * 2.0 / 3);
            } else {
                width = baseSize;
                height = baseSize;
            }

            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_yellow_fish); // 替換為你的Gif圖片資源
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout.addView(gifImageView);
        }

        //Type1 fish
        gifCount3 = type1List.size();
        System.out.println(gifCount3);

        // 動態添加GifImageView到RelativeLayout

        for (int i = 0; i < gifCount3; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());

            // 取得該位置的 Type1
            Type1 type1 = type1List.get(i);
            // 根据比例计算鱼的大小
            int type1Done = type1.getType1_done();

            // 計算魚的大小
            int baseSize = 2000;
            int width, height;

            // 根據 progressRatio 範圍設定魚的大小
            if (type1Done <1) {
                width = (int) (baseSize * 1.0 / 2);
                height = (int) (baseSize * 1.0 / 2);
            } else {
                width = baseSize;
                height = baseSize;
            }

            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout.addView(gifImageView);
        }

        //Type2 fish
        gifCount4 = type2List.size();
        System.out.println(gifCount4);

        // 動態添加GifImageView到RelativeLayout

        for (int i = 0; i < gifCount4; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());

            // 取得該位置的 Type2
            Type2 type2 = type2List.get(i);
            // 根据比例计算鱼的大小
            int type2Done = type2.getType2_done();
            int type2Goal = type2.getType2_goal();
            double progressRatio = (double) type2Done / type2Goal;

            // 計算魚的大小
            int baseSize = 2000;
            int width, height;

            // 根據 progressRatio 範圍設定魚的大小
            if (progressRatio < 1.0 / 3) {
                width = (int) (baseSize * 1.0 / 3);
                height = (int) (baseSize * 1.0 / 3);
            } else if (progressRatio < 2.0 / 3) {
                width = (int) (baseSize * 2.0 / 3);
                height = (int) (baseSize * 2.0 / 3);
            } else {
                width = baseSize;
                height = baseSize;
            }

            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout.addView(gifImageView);
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

        //feed

        mdbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                startActivity(intent);
                /*Intent intent = new Intent(getActivity(), login_page.class);
                startActivity(intent);*/
            }
        });


        //share

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdbt.setVisibility(View.GONE);
                share.setVisibility(View.GONE);
                new_fish.setVisibility(View.GONE);
                share_back.setVisibility(View.VISIBLE);
            }
        });

        //share_back

        share_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdbt.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                new_fish.setVisibility(View.VISIBLE);
                share_back.setVisibility(View.GONE);
            }
        });




        //new fish

        new_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 創建 BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);



                // 找到布局中的元件
                //CheckBox checkBox1 = bottomSheetDialog.findViewById(R.id.checkBox1);
                CheckBox type2Check = bottomSheetDialog.findViewById(R.id.type2Check);
                Button confirmButton = bottomSheetDialog.findViewById(R.id.confirmButton);
                EditText missionNameInput = bottomSheetDialog.findViewById(R.id.missionNameInput);
                EditText missionGoalInput = bottomSheetDialog.findViewById(R.id.missionGoalInput);
                EditText missionDeadlineInput = bottomSheetDialog.findViewById(R.id.missionDeadlineInput);
                TextView missionName = bottomSheetDialog.findViewById(R.id.missionName);
                TextView missionGoal = bottomSheetDialog.findViewById(R.id.missionGoal);
                TextView missionDeadline = bottomSheetDialog.findViewById(R.id.missionDeadline);

                // 找到布局中的 Spinner
                Spinner spinnerTaskType = bottomSheetDialog.findViewById(R.id.spinnerTaskType);

                // 設置 Spinner 選項
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.task_types, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTaskType.setAdapter(adapter);

                /*
                // 找到布局中的相关控件
                missionDeadlineInput = view.findViewById(R.id.missionDeadlineInput);

                // 添加点击事件监听器
                missionDeadlineInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog();
                    }
                });*/


                // 添加 Spinner 選擇監聽事件
                spinnerTaskType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // 根據 Spinner 選擇的項目，動態顯示或隱藏相應的輸入框和按鈕
                        switch (position) {
                            case 0:
                            case 1:
                            case 2:
                                // 顯示或隱藏相應的元件，這裡以 CheckBox 為例
                                type2Check.setVisibility(View.GONE);
                                missionNameInput.setVisibility(View.VISIBLE);
                                missionGoalInput.setVisibility(View.VISIBLE);
                                missionDeadlineInput.setVisibility(View.GONE);
                                missionName.setVisibility(View.VISIBLE);
                                missionGoal.setVisibility(View.VISIBLE);
                                missionDeadline.setVisibility(View.GONE);
                                break;
                            case 3:
                                type2Check.setVisibility(View.VISIBLE);
                                // 添加 CheckBox 監聽事件
                                type2Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        // 根據 CheckBox 的狀態，顯示或隱藏相應的輸入框
                                        if (isChecked) {
                                            missionNameInput.setVisibility(View.VISIBLE);
                                            missionGoalInput.setVisibility(View.GONE);
                                            missionDeadlineInput.setVisibility(View.VISIBLE);
                                            missionName.setVisibility(View.VISIBLE);
                                            missionGoal.setVisibility(View.GONE);
                                            missionDeadline.setVisibility(View.VISIBLE);
                                        } else {
                                            missionNameInput.setVisibility(View.VISIBLE);
                                            missionGoalInput.setVisibility(View.VISIBLE);
                                            missionDeadlineInput.setVisibility(View.GONE);
                                            missionName.setVisibility(View.VISIBLE);
                                            missionGoal.setVisibility(View.VISIBLE);
                                            missionDeadline.setVisibility(View.GONE);
                                        }
                                    }
                                });

                                /*case 4:
                                // 顯示或隱藏相應的元件，這裡以 CheckBox 為例
                                missionNameInput.setVisibility(View.VISIBLE);
                                missionGoalInput.setVisibility(View.GONE);
                                missionDeadlineInput.setVisibility(View.VISIBLE);
                                missionName.setVisibility(View.VISIBLE);
                                missionGoal.setVisibility(View.GONE);
                                missionDeadline.setVisibility(View.VISIBLE);
                                break;*/
                            // 添加更多選項的處理邏輯
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // 在這裡處理沒有選擇任何項目的情況
                    }
                });

                // 添加確認按鈕監聽事件
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 在這裡處理確認按鈕的邏輯
                        // 获取用户输入的数据
                        String missionNameText = missionNameInput.getText().toString();
                        //Integer missionGoalText = Integer.valueOf(missionGoalInput.getText().toString());
                        Integer missionGoalText=0;
                        String missionGoalInputText = missionGoalInput.getText().toString();
                        if (!missionGoalInputText.isEmpty()) {
                            missionGoalText = Integer.valueOf(missionGoalInputText);
                            // 接下来的逻辑
                        } else {
                            // 处理字符串为空的情况
                        }
                        String missionDeadlineText = missionDeadlineInput.getText().toString();
                        Pattern pattern = Pattern.compile("\\d{4}/\\d{2}/\\d{2}");
                        Matcher matcher = pattern.matcher(missionDeadlineText);

                        boolean isType2Checked = type2Check.isChecked();

                        // 获取当前日期
                        Calendar calendar = Calendar.getInstance();
                        Date currentDate = calendar.getTime();

                        // 将日期往前一天
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                        Date previousDate = calendar.getTime();

                        // 创建 SimpleDateFormat 对象，指定日期格式
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        // 将 Date 对象转换为字符串
                        //String currentDateStr = dateFormat.format(currentDate);
                        String previousDateStr = dateFormat.format(previousDate);


                        System.out.println("Previous Date: " + previousDateStr);


                        // 根据 Spinner 选项确定数据类型
                        String selectedType = spinnerTaskType.getSelectedItem().toString();

                        // 创建相应的数据对象
                        if ("日常".equals(selectedType)) {
                            // 创建 Daily 对象
                            Daily newDaily;
                            if(dailyList.isEmpty()){
                                newDaily = new Daily(BiguserId,BiguserId+"_1", missionNameText, missionGoalText, 0, previousDateStr);
                            }else {
                                newDaily = new Daily(BiguserId,dailyList.get(lastIndex_daily).getDaily_id()+"1", missionNameText, missionGoalText, 0, previousDateStr);
                            }

                            // 将新数据添加到列表
                            System.out.println(gifCount+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
                            dailyList.add(newDaily);
                            gifCount = dailyList.size();
                            System.out.println(gifCount+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
                            JsonUtils.saveDataToJsonFile(getActivity(), "daily_data.json", dailyList);

                            //new fish
                            GifImageView gifImageView = new GifImageView(requireContext());
                            // 設定GifImageView的圖片、寬度、高度以及隨機位置
                            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
                            //int width = ViewGroup.LayoutParams.MATCH_PARENT;
                            int width = (int) (2000 * 1.0 / 3);
                            int height = (int) (2000 * 1.0 / 3);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
                            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
                            gifImageView.setLayoutParams(params);
                            // 將GifImageView添加到RelativeLayout
                            relativeLayout.addView(gifImageView);

                        } else if ("飲食".equals(selectedType)) {
                            // 类似地，创建 Eat 对象，并处理 Eat 数据

                            Eat newEat;
                            if(eatList.isEmpty()){
                                newEat = new Eat(BiguserId,BiguserId+"_01", missionNameText, missionGoalText, 0, previousDateStr);
                            }else {
                                newEat = new Eat(BiguserId,eatList.get(lastIndex_eat).getEat_id()+"1", missionNameText, missionGoalText, 0, previousDateStr);
                            }

                            // 将新数据添加到列表
                            eatList.add(newEat);
                            JsonUtils.saveDataToJsonFile(getActivity(), "eat_data.json", eatList);

                            GifImageView gifImageView = new GifImageView(requireContext());
                            // 設定GifImageView的圖片、寬度、高度以及隨機位置
                            gifImageView.setImageResource(R.drawable.black_white_fish); // 替換為你的Gif圖片資源
                            //int width = ViewGroup.LayoutParams.MATCH_PARENT;
                            int width = (int) (40000 * 1.0 / 3);
                            int height = (int) (40000 * 1.0 / 3);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
                            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
                            gifImageView.setLayoutParams(params);
                            // 將GifImageView添加到RelativeLayout
                            relativeLayout.addView(gifImageView);

                        } else if ("健康".equals(selectedType)) {
                            // 类似地，创建 Health 对象，并处理 Health 数据

                            Health newHealth;
                            if(healthList.isEmpty()){
                                newHealth = new Health(BiguserId,BiguserId+"_001", missionNameText, missionGoalText, 0, previousDateStr);
                            }else {
                                newHealth = new Health(BiguserId,healthList.get(lastIndex_health).getHealth_id()+"1", missionNameText, missionGoalText, 0, previousDateStr);
                            }

                            // 将新数据添加到列表
                            healthList.add(newHealth);
                            JsonUtils.saveDataToJsonFile(getActivity(), "health_data.json", healthList);

                            GifImageView gifImageView = new GifImageView(requireContext());
                            // 設定GifImageView的圖片、寬度、高度以及隨機位置
                            gifImageView.setImageResource(R.drawable.blue_yellow_fish); // 替換為你的Gif圖片資源
                            int width = (int) (2000 * 1.0 / 3);
                            int height = (int) (2000 * 1.0 / 3);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
                            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
                            gifImageView.setLayoutParams(params);
                            // 將GifImageView添加到RelativeLayout
                            relativeLayout.addView(gifImageView);

                        } else if ("其他".equals(selectedType) && !isType2Checked) {
                            // 类似地，创建 Type2 对象，并处理 Type2 数据

                            Type2 newType2;
                            if(type2List.isEmpty()){
                                newType2 = new Type2(BiguserId,BiguserId+"_00001", missionNameText, missionGoalText, 0, previousDateStr);

                            }else {
                                newType2 = new Type2(BiguserId,type2List.get(lastIndex_type2).getType2_id()+"1", missionNameText, missionGoalText, 0, previousDateStr);

                            }

                            // 将新数据添加到列表
                            type2List.add(newType2);
                            JsonUtils.saveDataToJsonFile(getActivity(), "type2_data.json", type2List);

                            GifImageView gifImageView = new GifImageView(requireContext());
                            // 設定GifImageView的圖片、寬度、高度以及隨機位置
                            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
                            int width = (int) (2000 * 1.0 / 3);
                            int height = (int) (2000 * 1.0 / 3);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
                            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
                            gifImageView.setLayoutParams(params);
                            // 將GifImageView添加到RelativeLayout
                            relativeLayout.addView(gifImageView);

                        }else if(isType2Checked){
                            // 类似地，创建 Type1 对象，并处理 Type1 数据



                            Type1 newType1;
                            String formattedDate1="";
                            try {
                                // 创建两个日期格式，一个用于解析原始日期，另一个用于格式化新日期
                                SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd");
                                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

                                // 获取当前日期的年份
                                Calendar calendar1 = Calendar.getInstance();
                                int currentYear = calendar1.get(Calendar.YEAR);

                                // 解析原始日期
                                Date originalDate = inputFormat.parse(missionDeadlineText);
                                calendar1.setTime(originalDate);
                                calendar1.set(Calendar.YEAR, currentYear);


                                // 格式化新日期
                                String formattedDate = outputFormat.format(calendar1.getTime());
                                formattedDate1=formattedDate;

                            } catch (ParseException e) {
                                e.printStackTrace();
                                // 处理日期解析异常
                            }
                            if(type1List.isEmpty()){
                                newType1 = new Type1(BiguserId,BiguserId+"_0001", missionNameText, formattedDate1, 0, previousDateStr);

                            }else {
                                newType1 = new Type1(BiguserId,type1List.get(lastIndex_type1).getType1_id()+"1", missionNameText, formattedDate1, 0, previousDateStr);

                            }

                            // 打印结果
                            System.out.println("Save Deadline Date: "+newType1.getType1_deadline());
                            System.out.println("Save Deadline Date: hiiiiiiiiiiiiiiiiiiiiii");// 输出：2023-11-20

                            // 将新数据添加到列表
                            type1List.add(newType1);
                            JsonUtils.saveDataToJsonFile(getActivity(), "type1_data.json", type1List);

                            GifImageView gifImageView = new GifImageView(requireContext());
                            // 設定GifImageView的圖片、寬度、高度以及隨機位置
                            gifImageView.setImageResource(R.drawable.blue_fish); // 替換為你的Gif圖片資源
                            int width = (int) (2000 * 1.0 / 2);
                            int height = (int) (2000 * 1.0 / 2);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
                            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
                            gifImageView.setLayoutParams(params);
                            // 將GifImageView添加到RelativeLayout
                            relativeLayout.addView(gifImageView);
                        }


                        // 讀取 JSON 檔案中的 daily 資料
                        //String jsonDailyData = JsonUtils.readJsonFromFile(getActivity(),"daily_data.json");
                        // 解析 JSON 字串為 ArrayList<Daily> 物件
                        //ArrayList<Daily> dailyList777 = parseJsonToDailyList(jsonDailyData);
                        //System.out.println(dailyList777.get(lastIndex_daily+1).getDaily_name()+"oooooooooooooooo");
                        // 關閉 BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });

                // 在 show 之前設置 BottomSheetDialog 的高度
                bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        // 在這裡設置 BottomSheetDialog 的高度
                        View bottomSheetView = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                        if (bottomSheetView != null) {
                            BottomSheetBehavior.from(bottomSheetView).setPeekHeight(
                                    getResources().getDimensionPixelSize(R.dimen.bottom_sheet_dialog_height)
                            );
                        }
                    }
                });


                // 顯示 BottomSheetDialog

                bottomSheetDialog.show();

            }
        });


        return view;
    }



    /*
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
    }*/

    /*
    private void showDatePickerDialog() {
        // 使用当前日期作为 DatePickerDialog 的默认值
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 创建 DatePickerDialog 并设置选择日期的监听器
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),  // 使用当前 Fragment 的 Context
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        // 将选择的日期设置到 EditText
                        missionDeadlineInput.setText(String.format(Locale.getDefault(), "%d/%02d/%02d", selectedYear, selectedMonth + 1, selectedDay));
                    }
                },
                year, month, day);

        // 显示 DatePickerDialog
        datePickerDialog.show();
    }*/

}

/*public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //1026 試試看包進inner class？？？ :'/
        class try1026 extends AppCompatActivity {
            ImageButton addFish;

            @Override
            protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_home);

                //1019 事先在xml寫好的檔案 一開始先隱藏
                GifImageView blue_fish_0 = findViewById(R.id.blue_fish_0);
                blue_fish_0.setVisibility(View.INVISIBLE);

                //0806
                GifImageView fish_gif = findViewById(R.id.fish_gif);
                //0821
                GifImageView fish_gif_2 = findViewById(R.id.fish_gif_2);
                GifImageView bw_fish = findViewById(R.id.bw_fish);
                //0827
                GifImageView bw_fish_2 = findViewById(R.id.bw_fish_2);
                try {
                    GifDrawable gifFish = new GifDrawable(getResources(), R.drawable.blue_fish);
                    fish_gif.setImageDrawable(gifFish);
                    //0821
                    GifDrawable gifFish2 = new GifDrawable(getResources(), R.drawable.blue_fish);
                    fish_gif_2.setImageDrawable(gifFish2);
                    GifDrawable bwFish = new GifDrawable(getResources(), R.drawable.black_white_fish);
                    bw_fish.setImageDrawable(bwFish);
                    //0827
                    GifDrawable bwFish2 = new GifDrawable(getResources(), R.drawable.black_white_fish);
                    bw_fish_2.setImageDrawable(bwFish2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //0813
                GifImageView seaweed_gif = findViewById(R.id.seaweed_gif);
                GifImageView barrel_gif = findViewById(R.id.barrel_gif);
                GifImageView seaweed_gif_2 = findViewById(R.id.seaweed_gif_2);
                try {
                    GifDrawable gifSeaweed = new GifDrawable(getResources(), R.drawable.seaweed_1);
                    GifDrawable gifBarrel = new GifDrawable(getResources(), R.drawable.barrel_1);
                    GifDrawable gifSeaweed2 = new GifDrawable(getResources(), R.drawable.seaweed_1);
                    seaweed_gif.setImageDrawable(gifSeaweed);
                    barrel_gif.setImageDrawable(gifBarrel);
                    seaweed_gif_2.setImageDrawable(gifSeaweed2);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //0925 用主畫面左邊那個button試做成新增魚的按鈕；重做0807的
                addFish = findViewById(R.id.imageButton3);
                addFish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //1019 一開始先隱藏，點了按鈕之後才會出現   （成功ㄌ！！！:DDD ）
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


                //0827 按中間那顆可以切到登入畫面
                ImageButton mdbt = findViewById(R.id.imageButton);
                mdbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), login_page.class);
                        startActivity(intent);
                    }
                });


                //1008 右邊那顆切魚
                ImageButton rtbt = findViewById(R.id.imageButton2);
                rtbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                        startActivity(intent);
                    }
                });

            }
        }

            //1026  移到下面，不然上面的inner class不會被執行🥲
            //欸不是，跑是跑得動了，但還是沒有用到啊？？？？？？？
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

}*/



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