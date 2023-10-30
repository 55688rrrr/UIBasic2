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

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;


public class login_page extends AppCompatActivity {

    private Connection con;

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


                // 通过SharedViewModel将用户数据传递给其他组件
                //sharedViewModel = ViewModelProvider(getActivity()).get(SharedViewModel.class);
                //sharedViewModel.setUser(user);

                new OutputDailyTask().execute(userId);

                //創建intent對象
                /*Intent intent = new Intent(login_page.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("USER_NAME", userName);*/

                // 創建一個 Intent 來跳轉到 UserDataActivity
                /*Intent intent = new Intent(login_page.this, UserDataActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("USER_NAME", userName);*/


                //啟動主畫面act
                //startActivity(intent);


            } else {
                // 登录失败
                Toast.makeText(context, "登入失败", Toast.LENGTH_SHORT).show();
                System.err.println("nono");
            }
        }
    }








    private class OutputDailyTask extends AsyncTask<String, Void, ArrayList<Daily>> {


        //private String userId;

        /*public OutputDailyTask(String userId) {
            this.userId = userId;
        }*/

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


                // 執行 AsyncTask
                new SaveDataToFileTask().execute(dailyList);

            } else {
                // 取出失敗
                //Toast.makeText(login_page.this, "登入失敗", Toast.LENGTH_SHORT).show();
                System.err.println("dd nono");
            }
        }
    }



    //Eat data
    /*private class OutputEatTask extends AsyncTask<String, Void, ArrayList<Eat>> {


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
    }*/

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
            // 顯示 Toast 訊息來確認資料是否成功儲存
            //Toast.makeText(getApplicationContext(), "資料儲存成功", Toast.LENGTH_SHORT).show();
            System.err.println("成功");
            // 跳轉到主畫面
            // 创建启动新活动的 Intent 对象
            Intent intent = new Intent(login_page.this, MainActivity.class);
            startActivity(intent);

        }
    }

        /*
        private class OutputDatabaseTask extends AsyncTask<String, Void, Boolean> {
            @Override
            protected Boolean doInBackground(String... params) {
                String account = params[0];
                String pass = params[1];
                String data="";

                if (con == null) {
                    // 数据库连接失败
                    return false;
                } else {
                    try {
                        String query = "SELECT * FROM user WHERE user_id = ? AND user_pass = ?";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.setString(1, account);
                        preparedStatement.setString(2, pass);
                        ResultSet resultSet = preparedStatement.executeQuery(query);

                        // 如果结果集不为空，说明登录成功
                        //System.err.println(resultSet.next());
                            // 从结果集中获取数据
                        while(resultSet.next())
                        {
                            String userId = resultSet.getString("user_id");
                            String userPass = resultSet.getString("user_pass");
                            String userGmail = resultSet.getString("user_gmail");
                            String userName = resultSet.getString("user_name");
                            data+= userId+","+userGmail+","+userName+"\n";
                        }

                        System.out.println(data);

                        return resultSet.next();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false; // 数据库操作失败
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
                }
            }
         */

    //login = findViewById(R.id.LoginButton);
    //register = findViewById(R.id.RegisterButton);

    //login.setOnClickListener(new View.OnClickListener() {
    //@Override
    //public void onClick(View v) {
    //Intent intent = new Intent(login_page.this,MainActivity.class);
    //startActivity(intent);
    //}
    //});

    //register.setOnClickListener(new View.OnClickListener() {
    //@Override
    //public void onClick(View v) {
    //Intent intent = new Intent(login_page.this,register_page.class);
    //startActivity(intent);
    //}
    //});

}