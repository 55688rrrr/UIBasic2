package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class login_page extends AppCompatActivity {

    private Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Button LoginButton = findViewById(R.id.LoginButton);
        EditText AccountInput = findViewById(R.id.AccountInput);
        EditText PasswordInput = findViewById(R.id.PasswordInput);

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
                        new OutputDatabaseTask().execute(account, pass);
                        new OutputDailyTask().execute(account);

                    }
                });
            }
        }).start(); // 启动新线程
    }

    public class User {
        private String userId;
        private String userName;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }
        // 构造函数、Getter和Setter方法等
    }

    public class Daily {
        private String userId;
        private String daily_id;
        private String daily_name;
        private String daily_goal;
        private String daily_done;
        private String daily_color;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setDaily_id(String daily_id) {
            this.daily_id = daily_id;
        }

        public void setDaily_name(String daily_name) {
            this.daily_name = daily_name;
        }

        public void setDaily_goal(String daily_goal) {
            this.daily_goal = daily_goal;
        }

        public void setDaily_done(String daily_done) {
            this.daily_done = daily_done;
        }

        public void setDaily_color(String daily_color) {
            this.daily_color = daily_color;
        }

        public String getUserId() {
            return userId;
        }

        public String getDaily_id() {
            return daily_id;
        }

        public String getDaily_name() {
            return daily_name;
        }

        public String getDaily_goal() {
            return daily_goal;
        }

        public String getDaily_done() {
            return daily_done;
        }

        public String getDaily_color() {
            return daily_color;
        }
        // 构造函数、Getter和Setter方法等
    }

        private class OutputDatabaseTask extends AsyncTask<String, Void, ArrayList<User>> {


            @Override
            protected ArrayList<User>  doInBackground(String... params) {
                ArrayList<User> userList = new ArrayList<>();

                String account = params[0];
                String pass = params[1];

                if (con == null) {
                    // 数据库连接失败
                    return userList; // 返回空的ArrayList
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
                            User user = new User();
                            user.setUserId(userId);
                            user.setUserName(userName);
                            // 设置其他字段

                            userList.add(user);
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

                    return userList;
                }
            }



            @Override
            protected void onPostExecute(ArrayList<User> userList) {
                if (!userList.isEmpty()) {
                    // 登录成功
                    Toast.makeText(login_page.this, "登入成功", Toast.LENGTH_SHORT).show();
                    System.err.println("gogo");

                    for (User user : userList) {
                        System.out.println("User ID: " + user.getUserId());
                        System.out.println("User Name: " + user.getUserName());
                        // 打印其他字段
                    }

                    //創建intent對象
                    Intent intent = new Intent(login_page.this, MainActivity.class);

                    //啟動主畫面act
                    startActivity(intent);


                } else {
                    // 登录失败
                    Toast.makeText(login_page.this, "登入失敗", Toast.LENGTH_SHORT).show();
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
                            String dailyGoal = resultSet1.getString("goal");
                            String dailyDone = resultSet1.getString("done");
                            String dailycolor = resultSet1.getString("color");
                            // 其他字段也可以类似获取

                            // 创建User对象并添加到ArrayList中
                            Daily daily = new Daily();

                            daily.setDaily_id(dailyId);
                            daily.setUserId(userId);
                            daily.setDaily_name(dailyName);
                            daily.setDaily_goal(dailyGoal);
                            daily.setDaily_done(dailyDone);
                            daily.setDaily_color(dailycolor);
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
                        System.out.println("User Daily Id: " + daily.getDaily_id());
                        System.out.println("User Daily Name: " + daily.getDaily_name());
                        //System.out.println("User ID: " + daily.getDaily_goal());
                        //System.out.println("User ID: " + daily.getUserId());
                        //System.out.println("User ID: " + daily.getUserId());
                        // 打印其他字段
                    }
                } else {
                    // 取出失敗
                    //Toast.makeText(login_page.this, "登入失敗", Toast.LENGTH_SHORT).show();
                    System.err.println("dd nono");
                }
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

