package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

import android.view.View;
import android.view.LayoutInflater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Connection con;

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
            // 獲取從Intent中傳遞的用戶數據
            Intent intent = getIntent();
            String userId = intent.getStringExtra("USER_ID");
            String userName = intent.getStringExtra("USER_NAME");

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

            User user = new User(userId, userName);
            // 調用HomeFragment的updateUserData方法將用戶數據傳遞進去
            //HomeFragment.updateUserData(new User(userId, userName));

            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TimerFragment()).commit();
                break;
            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalendarFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                break;
            case R.id.nav_logout:


                //json
                // 讀取 JSON 檔案中的 daily 資料
                String jsonDailyData = JsonUtils.readJsonFromFile(this,"daily_data.json");
                // 解析 JSON 字串為 ArrayList<Daily> 物件
                ArrayList<Daily> dailyList = JsonUtils.parseJsonToDailyList(jsonDailyData);

                // 讀取 JSON 檔案中的 eat 資料
                String jsonEatData = JsonUtils.readJsonFromFile(this,"eat_data.json");
                // 解析 JSON 字串為 ArrayList<Eat> 物件
                ArrayList<Eat> eatList = JsonUtils.parseJsonToEatList(jsonEatData);

                // 讀取 JSON 檔案中的 health 資料
                String jsonHealthData = JsonUtils.readJsonFromFile(this,"health_data.json");
                // 解析 JSON 字串為 ArrayList<Health> 物件
                ArrayList<Health> healthList = JsonUtils.parseJsonToHealthList(jsonHealthData);

                // 讀取 JSON 檔案中的 type1 資料
                String jsonType1Data = JsonUtils.readJsonFromFile(this,"type1_data.json");
                // 解析 JSON 字串為 ArrayList<Type1> 物件
                ArrayList<Type1> type1List = JsonUtils.parseJsonToType1List(jsonType1Data);

                // 讀取 JSON 檔案中的 type2 資料
                String jsonType2Data = JsonUtils.readJsonFromFile(this,"type2_data.json");
                // 解析 JSON 字串為 ArrayList<Type2> 物件
                ArrayList<Type2> type2List = JsonUtils.parseJsonToType2List(jsonType2Data);

                //uploadDailyData(con, dailyList);

                JsonUtils.deleteAllFiles(this);
                Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, login_page.class);

                // 启动登录页面的Activity
                startActivity(intent);

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

    /*public static void uploadDailyData(Connection connection, ArrayList<Daily> dailyList) {
        // 使用 PreparedStatement 預防 SQL 注入
        String sql = "INSERT INTO daily (daily_id, user_id,  daily_name, goal, done, color) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // 逐一處理每個 Daily 物件
            for (Daily daily : dailyList) {
                statement.setInt(1, daily.getDaily_id());
                statement.setString(2, daily.getUserId());
                statement.setString(3, daily.getDaily_name());
                statement.setInt(4, daily.getDaily_goal());
                statement.setInt(5, daily.getDaily_progress());
                statement.setDate(6, daily.getCreated_at());

                // 執行 SQL 插入操作
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}