package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

//0806
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;

//0807
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //0806
    private GifImageView fish_gif;
    //0807
    /* ArrayList<GifImageView> fishList = new ArrayList<GifImageView>(); */
    //增加魚：fishList.add(fish0);
    //魚死掉之類的：fishList.remove(0);
    //不同類別，e.g. 運動、健康，可以設成fish_sport_0、fish_health_0之類的？

    //0807
    /* Button addFish = findViewById(R.id.addFish); */

    //0813
    private GifImageView seaweed_gif;
    private GifImageView barrel_gif;
    private GifImageView seaweed_gif_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //0806
        GifImageView fish_gif = findViewById(R.id.fish_gif);
        //0821
        GifImageView fish_gif_2 = findViewById(R.id.fish_gif_2);
        GifImageView bw_fish = findViewById(R.id.bw_fish);
        try{
            GifDrawable gifFish = new GifDrawable(getResources(), R.drawable.blue_fish);
            fish_gif.setImageDrawable(gifFish);
            //0821
            GifDrawable gifFish2 = new GifDrawable(getResources(), R.drawable.blue_fish);
            fish_gif_2.setImageDrawable(gifFish2);
            GifDrawable bwFish = new GifDrawable(getResources(), R.drawable.black_white_fish);
            bw_fish.setImageDrawable(bwFish);
        }catch (Exception e){
            e.printStackTrace();
        }

        //0813
        GifImageView seaweed_gif = findViewById(R.id.seaweed_gif);
        GifImageView barrel_gif = findViewById(R.id.barrel_gif);
        GifImageView seaweed_gif_2 = findViewById(R.id.seaweed_gif_2);
        try{
            GifDrawable gifSeaweed = new GifDrawable(getResources(), R.drawable.seaweed_1);
            GifDrawable gifBarrel = new GifDrawable(getResources(), R.drawable.barrel_1);
            GifDrawable gifSeaweed2 = new GifDrawable(getResources(), R.drawable.seaweed_1);
            seaweed_gif.setImageDrawable(gifSeaweed);
            barrel_gif.setImageDrawable(gifBarrel);
            seaweed_gif_2.setImageDrawable(gifSeaweed2);
        }catch (Exception e){
            e.printStackTrace();
        }

        //0807
        /*
        addFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GifImageView fish0;
                //fishList.add(fish0);
            }
        });
        */

    }
}



/*
--- 待辦 ---
1. 讓黑白魚也游起來
2. 從右游到左（原只有由左到右）
3. 串連各頁面？？🫠
*/