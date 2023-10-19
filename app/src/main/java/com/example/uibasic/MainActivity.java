package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

//0806
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;

//0807
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

//1018
//import android.view.animation.TranslateAnimation;
//可是只能extends一個而已... implements要接interface...
//1019 試著改變位置？
//import android.graphics.Canvas;
//import android.graphics.Bitmap;

public class MainActivity extends AppCompatActivity {

    //0806
    private GifImageView fish_gif;
    //0807
    /* ArrayList<GifImageView> fishList = new ArrayList<GifImageView>(); */
    //增加魚：fishList.add(fish0);
    //魚死掉之類的：fishList.remove(0);
    //不同類別，e.g. 運動、健康，可以設成fish_sport_0、fish_health_0之類的？
    //0930
    ArrayList<GifImageView> blueFishList = new ArrayList<GifImageView>();

    //0807
    /* Button addFish = findViewById(R.id.addFish); */
    //0925 用主畫面左邊那個button試做成新增魚的按鈕
    private ImageButton addFish;

    //0813
    private GifImageView seaweed_gif;
    private GifImageView barrel_gif;
    private GifImageView seaweed_gif_2;

    //1019 inner class執行TranslateAnimation？？
//    public TranslateAnimation(double fromX, double toX, double fromY, double toY) {
//        fromX = Math.random();
//        fromY = Math.random();

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

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

            //0807          //0925 用主畫面左邊那個button試做成新增魚的按鈕；重做0807的
            addFish = findViewById(R.id.imageButton3);
            addFish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //GifImageView fish0;
                    //fishList.add(fish0);
                    //System.out.print(R.drawable.blue1fish);
//                    1019 一開始先宣告了所以這邊註解掉
//                    GifImageView blue_fish_0 = findViewById(R.id.blue_fish_0);
                    //1019 一開始先隱藏，點了按鈕之後才會出現   （成功ㄌ！！！:DDD ）
                    blue_fish_0.setVisibility(View.VISIBLE);
//                    //1019 試著改變位置？
//                    Bitmap tryBitmap = blue_fish_0.getDrawingCache();   //後綴是自己跑出來的（？
//                    Canvas tryCanvas = new Canvas(tryBitmap);
//                    tryCanvas.drawBitmap(tryBitmap,0,0,null);
//                    blue_fish_0.draw();
                        //這邊可以設定位置！不論xml裡面是不是已經設定了
                        //Math.random()會產生0~1的數，所以再乘一個比較大的數，增加變化
                        //不過Math.random()產生的是double，setX、setY好像需要的是int？？
                        //另外還有個問題：之前是硬寫一個gif上去，所以魚的位置如果出現在螢幕右邊，就會直接游出去，不會自動碰到邊界就折返
                    blue_fish_0.setX((int)(Math.random()*1000));
                    blue_fish_0.setY((int)(Math.random()*2000));
                    try {
                        GifDrawable blueFish0 = new GifDrawable(getResources(), R.drawable.blue_fish);
                        blue_fish_0.setImageDrawable(blueFish0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //0930 （希望有懸浮視窗後可以直接從arraylist看到有幾隻魚？？）
                    //可以用blueFishList.size()得知list的大小aka魚的數量
                    blueFishList.add(blue_fish_0);
                    //❓能不能讓魚出現在隨機的位置？？？😟
                }
            });

            //0827 按中間那顆可以切到登入畫面
            ImageButton mdbt = findViewById(R.id.imageButton);
            mdbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, login_page.class);
                    startActivity(intent);
                }
            });

            //1008 右邊那顆切魚
            ImageButton rtbt = findViewById(R.id.imageButton2);
            rtbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                }
            });

        }
//    }
}



/*
--- 待辦 ---
從右游到左（原只有由左到右）
---
* 加🐟進去
* 不要硬寫gif上去？
* 🐟出現在隨機位置？
* 懸浮視窗？？
*/