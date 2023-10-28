package com.example.uibasic;
//1027

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



public class HomeFragment extends Fragment {
//public class HomeFragment extends FragmentActivity {
    GifImageView barrel_gif, seaweed_gif, seaweed_gif_2;
    GifImageView fish_gif, fish_gif_2, bw_fish, bw_fish_2;
    ImageButton mdbt, rtbt, addFish;
    GifImageView blue_fish_0;

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