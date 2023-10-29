package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

//0806
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;
//0807
import android.widget.FrameLayout;
import android.widget.ImageButton;
//????
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class HomeFragment extends Fragment {

    private ImageButton addFish;
    private int gifCount = 5;

    //private FrameLayout container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化控制元件
        GifImageView blue_fish_0 = view.findViewById(R.id.blue_fish_0);
        //GifImageView fish_gif = view.findViewById(R.id.fish_gif);
        //GifImageView fish_gif_2 = view.findViewById(R.id.fish_gif_2);
        GifImageView bw_fish = view.findViewById(R.id.bw_fish);
        GifImageView bw_fish_2 = view.findViewById(R.id.bw_fish_2);
        GifImageView seaweed_gif = view.findViewById(R.id.seaweed_gif);
        GifImageView barrel_gif = view.findViewById(R.id.barrel_gif);
        GifImageView seaweed_gif_2 = view.findViewById(R.id.seaweed_gif_2);

        User user1 = new User();
        String a1= user1.getUserId();
        String a2= user1.getUserName();
        System.out.println("User ID: ");
        System.out.println("User ID: " + a1);

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String userId = user.getUserId();
                // 在这里使用用户数据进行操作
                System.out.println("User ID: " + userId);
                System.out.println("User ID: " + userId);
                System.out.println("User ID: " + userId);
                System.out.println("User ID: " + userId);
            } else {
                // 用户数据为 null，处理逻辑
                System.out.println("User 数据为 null");
            }
        });

        sharedViewModel.getDailyListLiveData().observe(getViewLifecycleOwner(), dailyList -> {
            // 當日常數據變化時的觀察代碼
            if (dailyList != null && !dailyList.isEmpty()) {
                // 在這裡使用日常數據進行操作
                System.out.println("Daily List Size: " + dailyList.size());
            } else {
                // 日常數據為空或為 null，處理邏輯
                System.out.println("Daily List 為空或為 null");
            }
        });


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
            params.topMargin = -500+(int) (Math.random() * 1500); // 隨機Y坐標
            gifImageView.setLayoutParams(params);
            // 將GifImageView添加到RelativeLayout
            relativeLayout.addView(gifImageView);
        }


        // 設定事件監聽
        addFish = view.findViewById(R.id.imageButton3);
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