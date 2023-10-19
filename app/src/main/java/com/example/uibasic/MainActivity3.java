package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

//1009
import android.widget.ImageButton;
import android.content.Intent;
//1009 試試看？？
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

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

        //1009 試試看？？
        lvItems = (ListView) findViewById(R.id.lvItems);
        bfList = new ArrayList<GifDrawable>();
        bfList_Adapter = new ArrayAdapter<GifDrawable>(this,
                android.R.layout.simple_list_item_1, bfList);
        lvItems.setAdapter(bfList_Adapter);

        //1009
        ImageButton add_blueFish = findViewById(R.id.add_blueFish);
        add_blueFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GifImageView blue_fish_0 = findViewById(R.id.blue_fish_0);
                //1009 試試看的時候自己跑出來...？？？？
                //GifDrawable blueFish0 = null;
                try {
                    GifDrawable blueFish0 = new GifDrawable(getResources(), R.drawable.blue_fish);
                    blue_fish_0.setImageDrawable(blueFish0);
                        //1009 試試看？？
                        bfList.add(blueFish0);
                        bfList_Adapter.add(blueFish0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}



/*
試試看能不能：
1. 每次按下按鈕 魚都出現在不同位置     （寫在java還是xml？？）
2. 魚一開始是隱形的
*/