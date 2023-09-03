package com.example.uibasic;
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


public class RegisterActivity extends AppCompatActivity{

    /*private Connection connectToDatabase() throws SQLException {
        String dbUrl = "jdbc:mysql://db4free.net:3306/fish_tank1";
        String dbUser = "fishtank1";
        String dbPassword = "108306009";

        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        return connection;
    }*/

    private Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        //用sqlCon連資料庫
        Button ConfirmButton = findViewById(R.id.ConfirmButton);
        EditText NameInput = findViewById(R.id.NameInput);
        EditText AccountInput = findViewById(R.id.AccountInput);
        EditText PasswordInput = findViewById(R.id.PasswordInput);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sqlCon connection = new sqlCon();
                connection.run();
                // 这里放置你希望在线程中执行的代码
                ConfirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //點擊事件
                        String name = NameInput.getText().toString();
                        String account = AccountInput.getText().toString();
                        String pass = PasswordInput.getText().toString();

                        //insert into db
                        insertDataIntoDatabase(name, account, pass);
                    }
                });
            }
        }).start(); // 启动新线程


        /*try {
            Connection connection = connectToDatabase();
            if (connection != null) {
                // 连接成功后，你可以执行数据库操作
                // 例如，执行查询、插入、更新等操作

                // 打印成功连接的消息到日志
                Log.d("DatabaseConnection", "数据库连接成功");
            } else {
                // 连接失败
                Log.e("DatabaseConnection", "数据库连接失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理连接错误
            Log.e("DatabaseConnection", "数据库连接异常: " + e.getMessage());
        }*/
        /*
        //db
        String mysql_ip = "資料庫IP";
        int mysql_port = 3306; // Port 預設為 3306
        String db_name = "資料庫名稱";
        //String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
        String url = "jdbc:mysql://db4free.net:3306/fish_tank1";
        String db_user = "fishtank1";
        String db_password = "108306009";

        //jdbc
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }*/

        //gogo
        /*Button ConfirmButton = findViewById(R.id.ConfirmButton);
        EditText NameInput = findViewById(R.id.NameInput);
        EditText AccountInput = findViewById(R.id.AccountInput);
        EditText PasswordInput = findViewById(R.id.PasswordInput);

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //點擊事件
                String name = NameInput.getText().toString();
                String account = AccountInput.getText().toString();
                String pass = PasswordInput.getText().toString();

                //insert into db
                insertDataIntoDatabase(name, account, pass);
            }
        });*/

    }

    private void insertDataIntoDatabase(String name,String account,String pass){
        /*String mysql_ip = "資料庫IP";
        int mysql_port = 3306; // Port 預設為 3306
        String db_name = "資料庫名稱";
        //String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
        String url = "jdbc:mysql://db4free.net:3306/fish_tank1";
        String db_user = "fishtank1";
        String db_password = "108306009";*/
        try {
            //Connection con = DriverManager.getConnection(url, db_user, db_password);
            String query = "INSERT INTO user (user_id, user_pass, user_gmail, user_name) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, name);
            preparedStatement.executeUpdate();

            con.close();

            // 数据库操作成功
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
