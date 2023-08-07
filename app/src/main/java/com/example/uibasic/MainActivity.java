package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//0806
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {

    //0806
    private GifImageView fish_gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //0806
    GifImageView fish_gif = findViewById(R.id.fish_gif);
    try{
        GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.fish_gif);
        fish_gif.setImageDrawable(gifDrawable);
    }catch (Exception e){
        e.printStackTrace();
    }

    }
}