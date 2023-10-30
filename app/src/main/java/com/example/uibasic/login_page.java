package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;


public class login_page extends AppCompatActivity {

    private Connection con;

    private String BiguserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Button RegisterButton = findViewById(R.id.RegisterButton);
        Button LoginButton = findViewById(R.id.LoginButton);
        EditText AccountInput = findViewById(R.id.AccountInput);
        EditText PasswordInput = findViewById(R.id.PasswordInput);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, register_page.class);
                startActivity(intent);
            }
        });

        //用sqlCon連資料庫
        new Thread(new Runnable() {
            @Override
            public void run() {
                sqlCon connection = new sqlCon();
                con = connection.run();
                //con = connection.getConnection();
                if (con == null) {
                    System.err.println("数据库连接失败：con 为 null");
                }
                // 这里放置你希望在线程中执行的代码
                LoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //點擊事件
                        String account = AccountInput.getText().toString();
                        String pass = PasswordInput.getText().toString();

                        //insert into db
                        //insertDataIntoDatabase(name, account, pass,con);
                        //new OutputDailyTask(login_page.this).execute(account);
                        new OutputDatabaseTask(getApplicationContext()).execute(account, pass);
                        //new OutputDailyTask(login_page.this).execute(account);

                    }
                });
            }
        }).start(); // 启动新线程
    }



    private class OutputDatabaseTask extends AsyncTask<String, Void, User> {

        private AppCompatActivity activity;
        private Context context;

        private ViewModelStoreOwner viewModelStoreOwner;



        public OutputDatabaseTask(ViewModelStoreOwner viewModelStoreOwner) {
            if (viewModelStoreOwner == null) {
                Log.e("OutputDatabaseTask", "ViewModelStoreOwner is null!");
            }
            this.viewModelStoreOwner = viewModelStoreOwner;
        }

        public OutputDatabaseTask(AppCompatActivity activity) {
            this.activity = activity;
        }

        public OutputDatabaseTask(Context context) {
            this.context = context;
        }

        @Override
        protected User   doInBackground(String... params) {
            User user=null;

            String account = params[0];
            String pass = params[1];

            if (con == null) {
                // 数据库连接失败
                return user; // 返回空的ArrayList
            } else {
                try {
                    String query = "SELECT * FROM user WHERE user_id = ? AND user_pass = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, account);
                    preparedStatement.setString(2, pass);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        // 从结果集中获取数据
                        String userId = resultSet.getString("user_id");
                        String userName = resultSet.getString("user_name");
                        // 其他字段也可以类似获取

                        // 创建User对象并添加到ArrayList中
                        user = new User(userId, userName);

                        // 设置其他字段


                    }



                } catch (Exception e) {
                    e.printStackTrace();
                    // 发生异常时，确保user不为null，可以赋予一个无效的User对象
                    user = new User("invalid", "invalid");
                } finally {
                    try {
                        // 关闭连接
                        if (con != null && !con.isClosed()) {
                            //con.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return user;
            }
        }



        @Override
        protected void onPostExecute(User user) {
            if (user!=null) {
                // 登录成功


                Toast.makeText(context, "登入成功", Toast.LENGTH_SHORT).show();
                System.err.println("gogo");

                String userId = user.getUserId();
                String userName = user.getUserName();

                //傳入daily
                //new OutputDailyTask(login_page.this).execute(userId);

                // 打印用户的帐号和密码
                System.out.println("User ID: " + userId);
                System.out.println("User Name: " + userName);

                BiguserId=userId;
                System.out.println("User Nameaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa: " + BiguserId);


                new OutputDailyTask().execute(userId);



            } else {
                // 登录失败
                Toast.makeText(context, "登入失败", Toast.LENGTH_SHORT).show();
                System.err.println("nono");
            }
        }
    }








    private class OutputDailyTask extends AsyncTask<String, Void, ArrayList<Daily>> {



        @Override
        protected ArrayList<Daily>  doInBackground(String... params) {
            ArrayList<Daily> dailyList = new ArrayList<>();

            String account = params[0];

            if (con == null) {
                // 数据库连接失败
                System.out.println("Con is null!!!!!!!!!!!!!!!!!!!!!!!");
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
                        // 先不要關閉連結
                        if (con != null && !con.isClosed()) {
                            //con.close();
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
                System.err.println("dd gogo");


                // 執行 AsyncTask
                new SaveDataToFileTask().execute(dailyList);

            } else {
                // 取出失敗
                //Toast.makeText(login_page.this, "登入失敗", Toast.LENGTH_SHORT).show();
                System.err.println("dd nono");
            }
        }
    }

    //daily save
    private class SaveDataToFileTask extends AsyncTask<ArrayList<Daily>, Void, Void> {


        @Override
        protected Void doInBackground(ArrayList<Daily>... params) {
            // 取得傳入的資料
            ArrayList<Daily> dailyList = params[0];

            // 將資料轉換為 JSON 字串
            Gson gson = new Gson();
            //String jsonUser = gson.toJson(user); // 假設你的 User 物件是 user
            String jsonDailyList = gson.toJson(dailyList);

            // 將 JSON 字串儲存到檔案中
            //saveJsonToFile("user_data.json", jsonUser);
            saveJsonToFile("daily_data.json", jsonDailyList);

            return null;
        }

        private void saveJsonToFile(String fileName, String json) {
            try (FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
                // 寫入 JSON 字串到檔案中
                outputStreamWriter.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 在執行完儲存任務後，你可以在這裡執行任何後續操作，例如切換到下一個畫面
            System.err.println("成功");

            new OutputEatTask().execute(BiguserId);

        }
    }

    //Eat data
    private class OutputEatTask extends AsyncTask<String, Void, ArrayList<Eat>> {



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
                            //con.close();
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

                // 執行 AsyncTask
                new SaveEatDataToFileTask().execute(eatList);


            } else {
                // 取出失敗
                System.err.println("tt nono");
            }
        }
    }

    //Eat save
    private class SaveEatDataToFileTask extends AsyncTask<ArrayList<Eat>, Void, Void> {


        @Override
        protected Void doInBackground(ArrayList<Eat>... params) {
            // 取得傳入的資料
            ArrayList<Eat> eatList = params[0];

            // 將資料轉換為 JSON 字串
            Gson gson = new Gson();
            //String jsonUser = gson.toJson(user); // 假設你的 User 物件是 user
            String jsonEatList = gson.toJson(eatList);

            // 將 JSON 字串儲存到檔案中
            //saveJsonToFile("user_data.json", jsonUser);
            saveJsonToFile("eat_data.json", jsonEatList);

            return null;
        }

        private void saveJsonToFile(String fileName, String json) {
            try (FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
                // 寫入 JSON 字串到檔案中
                outputStreamWriter.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 在執行完儲存任務後，你可以在這裡執行任何後續操作，例如切換到下一個畫面
            // 顯示 Toast 訊息來確認資料是否成功儲存
            System.err.println("Eat成功");

            new OutputHealthTask().execute(BiguserId);

        }
    }

    //Health data
    private class OutputHealthTask extends AsyncTask<String, Void, ArrayList<Health>> {


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
                            //con.close();
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
                System.err.println("pp gogo");

                new SaveHealthDataToFileTask().execute(healthList);


            } else {
                // 取出失敗
                System.err.println("pp nono");
            }
        }
    }

    //Health save
    private class SaveHealthDataToFileTask extends AsyncTask<ArrayList<Health>, Void, Void> {


        @Override
        protected Void doInBackground(ArrayList<Health>... params) {
            // 取得傳入的資料
            ArrayList<Health> healthList = params[0];

            // 將資料轉換為 JSON 字串
            Gson gson = new Gson();
            //String jsonUser = gson.toJson(user); // 假設你的 User 物件是 user
            String jsonHealthList = gson.toJson(healthList);

            // 將 JSON 字串儲存到檔案中
            //saveJsonToFile("user_data.json", jsonUser);
            saveJsonToFile("health_data.json", jsonHealthList);

            return null;
        }

        private void saveJsonToFile(String fileName, String json) {
            try (FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
                // 寫入 JSON 字串到檔案中
                outputStreamWriter.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 在執行完儲存任務後，你可以在這裡執行任何後續操作，例如切換到下一個畫面
            // 顯示 Toast 訊息來確認資料是否成功儲存

            System.err.println("Heath成功");


            new OutputType1Task().execute(BiguserId);

        }
    }

    //Type1 data
    private class OutputType1Task extends AsyncTask<String, Void, ArrayList<Type1>> {



        @Override
        protected ArrayList<Type1>  doInBackground(String... params) {
            ArrayList<Type1> type1List = new ArrayList<>();

            String account = params[0];

            if (con == null) {
                // 数据库连接失败
                return type1List; // 返回空的ArrayList
            } else {
                try {
                    String query1 = "SELECT * FROM type1 WHERE user_id = ? ";
                    PreparedStatement preparedStatement1 = con.prepareStatement(query1);
                    preparedStatement1.setString(1, account);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    while (resultSet1.next()) {
                        // 从结果集中获取数据
                        String type1Id = resultSet1.getString("type1_id");
                        String userId = resultSet1.getString("user_id");
                        String type1Name = resultSet1.getString("type1_name");
                        Date type1date = resultSet1.getDate("deadline");
                        Integer type1Done = resultSet1.getInt("done");
                        String type1color = resultSet1.getString("color");
                        // 其他字段也可以类似获取

                        // 将Date对象转换为特定格式的字符串
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String formattedDate = dateFormat.format(type1date);

                        // 创建User对象并添加到ArrayList中
                        Type1 type1 = new Type1(userId, type1Id, type1Name, formattedDate, type1Done, type1color);


                        // 设置其他字段

                        type1List.add(type1);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {

                        if (con != null && !con.isClosed()) {
                            //con.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return type1List;
            }
        }



        @Override
        protected void onPostExecute(ArrayList<Type1> type1List) {
            if (!type1List.isEmpty()) {
                // 日常資料取出成功

                // 執行 AsyncTask
                new SaveType1DataToFileTask().execute(type1List);

            } else {
                // 取出失敗
                System.err.println("tt nono");
            }
        }
    }

    //Type1 save
    private class SaveType1DataToFileTask extends AsyncTask<ArrayList<Type1>, Void, Void> {


        @Override
        protected Void doInBackground(ArrayList<Type1>... params) {
            // 取得傳入的資料
            ArrayList<Type1> type1List = params[0];

            // 將資料轉換為 JSON 字串
            Gson gson = new Gson();
            //String jsonUser = gson.toJson(user); // 假設你的 User 物件是 user
            String jsonType1List = gson.toJson(type1List);

            // 將 JSON 字串儲存到檔案中
            //saveJsonToFile("user_data.json", jsonUser);
            saveJsonToFile("type1_data.json", jsonType1List);

            return null;
        }

        private void saveJsonToFile(String fileName, String json) {
            try (FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
                // 寫入 JSON 字串到檔案中
                outputStreamWriter.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 在執行完儲存任務後，你可以在這裡執行任何後續操作，例如切換到下一個畫面
            // 顯示 Toast 訊息來確認資料是否成功儲存

            System.err.println("Type1成功");

            new OutputType2Task().execute(BiguserId);

        }
    }

    //Type2 data
    private class OutputType2Task extends AsyncTask<String, Void, ArrayList<Type2>> {


        @Override
        protected ArrayList<Type2>  doInBackground(String... params) {
            ArrayList<Type2> type2List = new ArrayList<>();

            String account = params[0];

            if (con == null) {
                // 数据库连接失败
                return type2List; // 返回空的ArrayList
            } else {
                try {
                    String query1 = "SELECT * FROM type2 WHERE user_id = ? ";
                    PreparedStatement preparedStatement1 = con.prepareStatement(query1);
                    preparedStatement1.setString(1, account);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    while (resultSet1.next()) {
                        // 从结果集中获取数据
                        String type2Id = resultSet1.getString("type2_id");
                        String userId = resultSet1.getString("user_id");
                        String type2Name = resultSet1.getString("type2_name");
                        Integer type2Goal = resultSet1.getInt("goal");
                        Integer type2Done = resultSet1.getInt("done");
                        String type2color = resultSet1.getString("color");
                        // 其他字段也可以类似获取

                        // 创建User对象并添加到ArrayList中
                        Type2 type2 = new Type2(userId, type2Id, type2Name, type2Goal, type2Done, type2color);


                        // 设置其他字段

                        type2List.add(type2);
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

                return type2List;
            }
        }



        @Override
        protected void onPostExecute(ArrayList<Type2> type2List) {
            if (!type2List.isEmpty()) {
                // 日常資料取出成功
                //Toast.makeText(login_page.this, "登入成功", Toast.LENGTH_SHORT).show();
                System.err.println("22 gogo");


                for (Type2 type2 : type2List) {
                    System.out.println("User ID: " + type2.getUserId());
                    System.out.println("Type2 Id: " + type2.getType2_id());
                    System.out.println("Type2 Name: " + type2.getType2_name());
                    System.out.println("Goal: " + type2.getType2_goal());
                    System.out.println("Done: " + type2.getType2_done());
                    System.out.println("Color: " + type2.getDaily_color());
                    // 打印其他字段
                }
                new SaveType2DataToFileTask().execute(type2List);


            } else {
                // 取出失敗
                System.err.println("22 nono");
            }
        }
    }

    //Type2 save
    private class SaveType2DataToFileTask extends AsyncTask<ArrayList<Type2>, Void, Void> {


        @Override
        protected Void doInBackground(ArrayList<Type2>... params) {
            // 取得傳入的資料
            ArrayList<Type2> type2List = params[0];

            // 將資料轉換為 JSON 字串
            Gson gson = new Gson();
            //String jsonUser = gson.toJson(user); // 假設你的 User 物件是 user
            String jsonType2List = gson.toJson(type2List);

            // 將 JSON 字串儲存到檔案中
            //saveJsonToFile("user_data.json", jsonUser);
            saveJsonToFile("type2_data.json", jsonType2List);

            return null;
        }

        private void saveJsonToFile(String fileName, String json) {
            try (FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
                // 寫入 JSON 字串到檔案中
                outputStreamWriter.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 在執行完儲存任務後，你可以在這裡執行任何後續操作，例如切換到下一個畫面
            // 顯示 Toast 訊息來確認資料是否成功儲存
            //Toast.makeText(getApplicationContext(), "資料儲存成功", Toast.LENGTH_SHORT).show();
            System.err.println("Type2成功");

            // 跳轉到主畫面
            // 创建启动新活动的 Intent 对象
            Intent intent = new Intent(login_page.this, MainActivity.class);
            startActivity(intent);

        }
    }


}