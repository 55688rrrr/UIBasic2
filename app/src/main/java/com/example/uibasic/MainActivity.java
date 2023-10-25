package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
//        GifImageView fish_gif = findViewById(R.id.fish_gif);
//        try{
//            GifDrawable gifFish = new GifDrawable(getResources(), R.drawable.fish_3);
//            fish_gif.setImageDrawable(gifFish);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //0813
//        GifImageView seaweed_gif = findViewById(R.id.seaweed_gif);
//        GifImageView barrel_gif = findViewById(R.id.barrel_gif);
//        GifImageView seaweed_gif_2 = findViewById(R.id.seaweed_gif_2);
//        try{
//            GifDrawable gifSeaweed = new GifDrawable(getResources(), R.drawable.seaweed_1);
//            GifDrawable gifBarrel = new GifDrawable(getResources(), R.drawable.barrel_1);
//            GifDrawable gifSeaweed2 = new GifDrawable(getResources(), R.drawable.seaweed_1);
//            seaweed_gif.setImageDrawable(gifSeaweed);
//            barrel_gif.setImageDrawable(gifBarrel);
//            seaweed_gif_2.setImageDrawable(gifSeaweed2);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        //0807
//        /*
//        addFish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //GifImageView fish0;
//                //fishList.add(fish0);
//            }
//        });
//        */
//
//        //0827 按中間那顆可以切到登入畫面
//        mdbt = (ImageButton) LayoutInflater.from(getApplication()).inflate(R.layout.fragment_home, null);
 //       mdbt.setOnClickListener(new View.OnClickListener() {
 //           @Override
 //           public void onClick(View v) {
 //               Intent intent = new Intent(MainActivity.this, login_page.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

    //0827
    private ImageButton mdbt;



}




/*
--- 每週應完成的進度 ---
0807:
1. 魚可以左右游動
2. 背景裝飾物的gif
* gif檔會變亂碼？！
*/