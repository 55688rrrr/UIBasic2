package com.example.uibasic;

import android.os.AsyncTask;
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
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private ImageButton addFish;

    private ArrayList<Daily> dailyList;

    private Connection con;

    private View view;


    //private FrameLayout container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 在這裡創建 SaveDataToFileTask 的實例，並傳入 HomeFragment 的 Activity

        view = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化控制元件
        GifImageView blue_fish_0 = view.findViewById(R.id.blue_fish_0);
        //GifImageView fish_gif = view.findViewById(R.id.fish_gif);
        //GifImageView fish_gif_2 = view.findViewById(R.id.fish_gif_2);
        GifImageView bw_fish = view.findViewById(R.id.bw_fish);
        GifImageView bw_fish_2 = view.findViewById(R.id.bw_fish_2);
        GifImageView seaweed_gif = view.findViewById(R.id.seaweed_gif);
        GifImageView barrel_gif = view.findViewById(R.id.barrel_gif);
        GifImageView seaweed_gif_2 = view.findViewById(R.id.seaweed_gif_2);

        //json
        // 讀取 JSON 檔案中的 daily 資料
        String jsonDailyData = readJsonFromFile("daily_data.json");

        // 解析 JSON 字串為 ArrayList<Daily> 物件
        ArrayList<Daily> dailyList = parseJsonToDailyList(jsonDailyData);
        System.out.println("jsonnnnnnnnnnnnn "+ dailyList.size());

        for (Daily daily : dailyList) {
            System.out.println("User ID: " + daily.getUserId());
            System.out.println("Daily Id: " + daily.getDaily_id());
            System.out.println("Daily Name: " + daily.getDaily_name());
            System.out.println("Goal: " + daily.getDaily_goal());
            System.out.println("Done: " + daily.getDaily_done());
            System.out.println("Color: " + daily.getDaily_color());
            // 打印其他字段
        }

        //Intent intent = getActivity().getIntent();


        //System.out.println("User ID: "+ userId);
        //System.out.println("User Name: " + userName);

        // 创建 OutputDailyTask 对象并执行异步任务
        //用sqlCon連資料庫
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                sqlCon connection = new sqlCon();
                con = connection.run();
                //con = connection.getConnection();
                if (con == null) {
                    System.err.println("数据库连接失败：con 为 null");
                }else{
                    OutputDailyTask outputDailyTask = new OutputDailyTask(getActivity());
                    outputDailyTask.execute(userId);

                }

            }
        }).start();*/ // 启动新线程

        // 创建 OutputEatTask 对象并执行异步任务
        //用sqlCon連資料庫
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                sqlCon connection = new sqlCon();
                con = connection.run();
                //con = connection.getConnection();
                if (con == null) {
                    System.err.println("数据库连接失败：con 为 null");
                }else{
                    OutputEatTask OutputEatTask = new OutputEatTask(getActivity());
                    OutputEatTask.execute(userId);

                }

            }
        }).start();*/ // 启动新线程

        // 创建 OutputHealthTask 对象并执行异步任务
        //用sqlCon連資料庫
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                sqlCon connection = new sqlCon();
                con = connection.run();
                //con = connection.getConnection();
                if (con == null) {
                    System.err.println("数据库连接失败：con 为 null");
                }else{
                    OutputHealthTask OutputHealthTask = new OutputHealthTask(getActivity());
                    OutputHealthTask.execute(userId);

                }

            }
        }).start();*/
        // 启动新线程


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


        int gifCount = dailyList.size();
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

    //daily data
    private class OutputDailyTask extends AsyncTask<String, Void, ArrayList<Daily>> {


        public OutputDailyTask(FragmentActivity activity) {
        }

        @Override
        protected ArrayList<Daily>  doInBackground(String... params) {
            ArrayList<Daily> dailyList = new ArrayList<>();

            String account = params[0];

            if (con == null) {
                // 数据库连接失败
                return dailyList; // 返回空的ArrayList
            } else {
                try {
                    String query1 = "SELECT * FROM daily WHERE user_id = ? ";
                    PreparedStatement preparedStatement1 = con.prepareStatement(query1);
                    preparedStatement1.setString(1, account);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    while (resultSet1.next()) {
                        // 从结果集中获取数据
                        String dailyId = resultSet1.getString("daily_id");
                        String userId = resultSet1.getString("user_id");
                        String dailyName = resultSet1.getString("daily_name");
                        Integer dailyGoal = resultSet1.getInt("goal");
                        Integer dailyDone = resultSet1.getInt("done");
                        String dailycolor = resultSet1.getString("color");
                        // 其他字段也可以类似获取

                        // 创建User对象并添加到ArrayList中
                        Daily daily = new Daily(userId, dailyId, dailyName, dailyGoal, dailyDone, dailycolor);


                        // 设置其他字段

                        dailyList.add(daily);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 关闭连接
                        if (con != null && !con.isClosed()) {
                            con.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return dailyList;
            }
        }



        @Override
        protected void onPostExecute(ArrayList<Daily> dailyList) {
            if (!dailyList.isEmpty()) {
                // 日常資料取出成功
                //Toast.makeText(login_page.this, "登入成功", Toast.LENGTH_SHORT).show();
                System.err.println("dd gogo");


                for (Daily daily : dailyList) {
                    System.out.println("User ID: " + daily.getUserId());
                    System.out.println("Daily Id: " + daily.getDaily_id());
                    System.out.println("Daily Name: " + daily.getDaily_name());
                    System.out.println("Goal: " + daily.getDaily_goal());
                    System.out.println("Done: " + daily.getDaily_done());
                    System.out.println("Color: " + daily.getDaily_color());
                    // 打印其他字段
                }

                // 在这里动态添加GifImageView
                int gifCount = dailyList.size();
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

                System.out.println(dailyList.size());
                HomeFragment.this.dailyList = dailyList;
                System.out.println(HomeFragment.this.dailyList.size());



                // 创建Intent对象
                //Intent intent = new Intent(login_page.this, MainActivity.class);

                // 将ArrayList<Daily>放入Intent中
                //intent.putParcelableArrayListExtra("dailyList", (ArrayList<? extends Parcelable>) dailyList);

                //啟動主畫面act
                //startActivity(intent);
            } else {
                // 取出失敗
                System.err.println("dd nono");
            }
        }
    }

    //Eat data
    private class OutputEatTask extends AsyncTask<String, Void, ArrayList<Eat>> {


        public OutputEatTask(FragmentActivity activity) {
        }

        @Override
        protected ArrayList<Eat>  doInBackground(String... params) {
            ArrayList<Eat> eatList = new ArrayList<>();

            String account = params[0];

            if (con == null) {
                // 数据库连接失败
                return eatList; // 返回空的ArrayList
            } else {
                try {
                    String query1 = "SELECT * FROM eat WHERE user_id = ? ";
                    PreparedStatement preparedStatement1 = con.prepareStatement(query1);
                    preparedStatement1.setString(1, account);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    while (resultSet1.next()) {
                        // 从结果集中获取数据
                        String eatId = resultSet1.getString("eat_id");
                        String userId = resultSet1.getString("user_id");
                        String eatName = resultSet1.getString("eat_name");
                        Integer eatGoal = resultSet1.getInt("goal");
                        Integer eatDone = resultSet1.getInt("done");
                        String eatcolor = resultSet1.getString("color");
                        // 其他字段也可以类似获取

                        // 创建User对象并添加到ArrayList中
                        Eat eat = new Eat(userId, eatId, eatName, eatGoal, eatDone, eatcolor);


                        // 设置其他字段

                        eatList.add(eat);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 关闭连接
                        if (con != null && !con.isClosed()) {
                            con.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return eatList;
            }
        }



        @Override
        protected void onPostExecute(ArrayList<Eat> eatList) {
            if (!eatList.isEmpty()) {
                // 日常資料取出成功
                //Toast.makeText(login_page.this, "登入成功", Toast.LENGTH_SHORT).show();
                System.err.println("tt gogo");


                for (Eat eat : eatList) {
                    System.out.println("User ID: " + eat.getUserId());
                    System.out.println("Eat Id: " + eat.getEat_id());
                    System.out.println("Eat Name: " + eat.getEat_name());
                    System.out.println("Goal: " + eat.getEat_goal());
                    System.out.println("Done: " + eat.getEat_done());
                    System.out.println("Color: " + eat.getEat_color());
                    // 打印其他字段
                }

                // 在这里动态添加GifImageView
                int gifCount = eatList.size();
                System.out.println(gifCount);

                // 動態添加GifImageView到RelativeLayout
                RelativeLayout relativeLayout = view.findViewById(R.id.linearLayout);
                for (int i = 0; i < gifCount; i++) {
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

                System.out.println(eatList.size());
                //HomeFragment.this.dailyList = eatList;
                //System.out.println(HomeFragment.this.dailyList.size());



                // 创建Intent对象
                //Intent intent = new Intent(login_page.this, MainActivity.class);

                // 将ArrayList<Daily>放入Intent中
                //intent.putParcelableArrayListExtra("dailyList", (ArrayList<? extends Parcelable>) dailyList);

                //啟動主畫面act
                //startActivity(intent);
            } else {
                // 取出失敗
                System.err.println("tt nono");
            }
        }
    }

    //Health data
    private class OutputHealthTask extends AsyncTask<String, Void, ArrayList<Health>> {


        public OutputHealthTask(FragmentActivity activity) {
        }

        @Override
        protected ArrayList<Health>  doInBackground(String... params) {
            ArrayList<Health> healthList = new ArrayList<>();

            String account = params[0];

            if (con == null) {
                // 数据库连接失败
                return healthList; // 返回空的ArrayList
            } else {
                try {
                    String query1 = "SELECT * FROM health WHERE user_id = ? ";
                    PreparedStatement preparedStatement1 = con.prepareStatement(query1);
                    preparedStatement1.setString(1, account);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    while (resultSet1.next()) {
                        // 从结果集中获取数据
                        String healthId = resultSet1.getString("health_id");
                        String userId = resultSet1.getString("user_id");
                        String healthName = resultSet1.getString("health_name");
                        Integer healthGoal = resultSet1.getInt("goal");
                        Integer healthDone = resultSet1.getInt("done");
                        String healthcolor = resultSet1.getString("color");
                        // 其他字段也可以类似获取

                        // 创建User对象并添加到ArrayList中
                        Health health = new Health(userId, healthId, healthName, healthGoal, healthDone, healthcolor);


                        // 设置其他字段

                        healthList.add(health);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 关闭连接
                        if (con != null && !con.isClosed()) {
                            con.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return healthList;
            }
        }



        @Override
        protected void onPostExecute(ArrayList<Health> healthList) {
            if (!healthList.isEmpty()) {
                // 日常資料取出成功
                //Toast.makeText(login_page.this, "登入成功", Toast.LENGTH_SHORT).show();
                System.err.println("pp gogo");


                for (Health health : healthList) {
                    System.out.println("User ID: " + health.getUserId());
                    System.out.println("Health Id: " + health.getHealth_id());
                    System.out.println("Health Name: " + health.getHealth_name());
                    System.out.println("Goal: " + health.getHealth_goal());
                    System.out.println("Done: " + health.getHealth_done());
                    System.out.println("Color: " + health.getHealth_color());
                    // 打印其他字段
                }

                // 在这里动态添加GifImageView
                int gifCount = healthList.size();
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

                System.out.println(healthList.size());
                //HomeFragment.this.dailyList = eatList;
                //System.out.println(HomeFragment.this.dailyList.size());



                // 创建Intent对象
                //Intent intent = new Intent(login_page.this, MainActivity.class);

                // 将ArrayList<Daily>放入Intent中
                //intent.putParcelableArrayListExtra("dailyList", (ArrayList<? extends Parcelable>) dailyList);

                //啟動主畫面act
                //startActivity(intent);
            } else {
                // 取出失敗
                System.err.println("pp nono");
            }
        }
    }
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