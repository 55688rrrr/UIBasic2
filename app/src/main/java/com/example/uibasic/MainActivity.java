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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //0806
        GifImageView fish_gif = findViewById(R.id.fish_gif);
        try{
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.made_fish);
            fish_gif.setImageDrawable(gifDrawable);
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
--- 每週應完成的進度 ---
0807:
1. 魚可以左右游動
2. 背景裝飾物的gif
* gif檔會變亂碼？！
*/