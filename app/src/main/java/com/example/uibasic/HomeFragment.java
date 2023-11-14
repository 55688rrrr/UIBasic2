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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {


    private ImageButton addFish;

    private ArrayList<Daily> dailyList;

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
        String jsonDailyData = JsonUtils.readJsonFromFile(getActivity(),"daily_data.json");
        // 解析 JSON 字串為 ArrayList<Daily> 物件
        ArrayList<Daily> dailyList = JsonUtils.parseJsonToDailyList(jsonDailyData);
        int lastIndex_daily = dailyList.size() - 1;

        BiguserId= dailyList.get(0).getUserId();

        System.out.println("jsonnnnnnnnnnnnn "+ dailyList.size());
        System.out.println("jsonnnnnnnnnnnnn "+ dailyList.get(0).getDaily_id());

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
        gifCount1 = eatList.size();
        System.out.println(gifCount1);

        // 動態添加GifImageView到RelativeLayout

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
            relativeLayout.addView(gifImageView);
        }

        //Health fish
        gifCount2 = healthList.size();
        System.out.println(gifCount2);

        // 動態添加GifImageView到RelativeLayout

        for (int i = 0; i < gifCount2; i++) {
            GifImageView gifImageView = new GifImageView(requireContext());
            // 設定GifImageView的圖片、寬度、高度以及隨機位置
            gifImageView.setImageResource(R.drawable.blue_yellow_fish); // 替換為你的Gif圖片資源
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 300;
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

        //Type2 fish
        gifCount4 = type2List.size();
        System.out.println(gifCount4);

        // 動態添加GifImageView到RelativeLayout

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
        ImageButton mdbt = view.findViewById(R.id.imageButton);
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
        ImageButton rtbt = view.findViewById(R.id.imageButton2);
        rtbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), MainActivity3.class);
                startActivity(intent);*/
            }
        });




        //new fish
        ImageButton new_fish = view.findViewById(R.id.imageButton3);
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
                        boolean isType2Checked = type2Check.isChecked();
                        Date currentDate = new Date();
                        // 创建 SimpleDateFormat 对象，指定日期格式
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        // 将 Date 对象转换为字符串
                        String dateString = dateFormat.format(currentDate);

                        // 根据 Spinner 选项确定数据类型
                        String selectedType = spinnerTaskType.getSelectedItem().toString();

                        // 创建相应的数据对象
                        if ("日常".equals(selectedType) && !isType2Checked) {
                            // 创建 Daily 对象
                            Daily newDaily = new Daily(BiguserId,dailyList.get(lastIndex_daily).getDaily_id()+"1", missionNameText, missionGoalText, 0, dateString);

                            // 将新数据添加到列表
                            System.out.println(gifCount+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
                            dailyList.add(newDaily);
                            gifCount = dailyList.size();
                            System.out.println(gifCount+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
                            JsonUtils.saveDataToJsonFile(getActivity(), "daily_data.json", dailyList);
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

                        } else if ("飲食".equals(selectedType)) {
                            // 类似地，创建 Eat 对象，并处理 Eat 数据

                            Eat newEat = new Eat(BiguserId,eatList.get(lastIndex_eat).getEat_id()+"1", missionNameText, missionGoalText, 0, dateString);

                            // 将新数据添加到列表
                            eatList.add(newEat);
                            JsonUtils.saveDataToJsonFile(getActivity(), "eat_data.json", eatList);

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
                            relativeLayout.addView(gifImageView);

                        } else if ("健康".equals(selectedType)) {
                            // 类似地，创建 Health 对象，并处理 Health 数据

                            Health newHealth = new Health(BiguserId,healthList.get(lastIndex_health).getHealth_id()+"1", missionNameText, missionGoalText, 0, dateString);

                            // 将新数据添加到列表
                            healthList.add(newHealth);
                            JsonUtils.saveDataToJsonFile(getActivity(), "health_data.json", healthList);

                            GifImageView gifImageView = new GifImageView(requireContext());
                            // 設定GifImageView的圖片、寬度、高度以及隨機位置
                            gifImageView.setImageResource(R.drawable.blue_yellow_fish); // 替換為你的Gif圖片資源
                            int width = ViewGroup.LayoutParams.MATCH_PARENT;
                            int height = 300;
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                            params.leftMargin = (int) (Math.random() * 800); // 隨機X坐標
                            params.topMargin =  (int) (Math.random() * 1500); // 隨機Y坐標
                            gifImageView.setLayoutParams(params);
                            // 將GifImageView添加到RelativeLayout
                            relativeLayout.addView(gifImageView);

                        } else if ("其他".equals(selectedType)) {
                            // 类似地，创建 Type2 对象，并处理 Type2 数据

                            Type2 newType2 = new Type2(BiguserId,type2List.get(lastIndex_type2).getType2_id()+"1", missionNameText, missionGoalText, 0, dateString);

                            // 将新数据添加到列表
                            type2List.add(newType2);
                            JsonUtils.saveDataToJsonFile(getActivity(), "type2_data.json", type2List);

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

                        }else {
                            // 类似地，创建 Type2 对象，并处理 Type2 数据

                            Type1 newType1 = new Type1(BiguserId,type1List.get(lastIndex_type1).getType1_id()+"1", missionNameText, missionDeadlineText, 0, dateString);

                            // 将新数据添加到列表
                            type1List.add(newType1);
                            JsonUtils.saveDataToJsonFile(getActivity(), "type1_data.json", type1List);

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