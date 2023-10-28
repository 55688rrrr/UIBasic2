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
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class register_page extends AppCompatActivity{

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


        Button ConfirmButton = findViewById(R.id.ConfirmButton);
        EditText NameInput = findViewById(R.id.NameInput);
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
                ConfirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //點擊事件
                        String name = NameInput.getText().toString();
                        String account = AccountInput.getText().toString();
                        String pass = PasswordInput.getText().toString();

                        //insert into db
                        //insertDataIntoDatabase(name, account, pass,con);
                        new InsertDatabaseTask().execute(name, account, pass);

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

    /*private void insertDataIntoDatabase (String name,String account,String pass,Connection con){

        if (con == null) {
            // 输出一条错误消息或记录日志以指示连接问题
            System.err.println("数据库连接失败：con 为 null");
        } else {
            // 执行插入操作
            String query = "INSERT INTO user (user_id, user_pass, user_gmail, user_name) VALUES (?, ?, ?, ?)";
            try {
                //Connection con = DriverManager.getConnection(url, db_user, db_password);
                //String query = "INSERT INTO user (user_id, user_pass, user_gmail, user_name) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, account);
                preparedStatement.setString(2, pass);
                preparedStatement.setString(3, "");
                preparedStatement.setString(4, name);
                preparedStatement.executeUpdate();

                con.close();
                System.err.println("gogo");
                // 数据库操作成功
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }*/

    private class InsertDatabaseTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String name = params[0];
            String account = params[1];
            String pass = params[2];

            if (con == null) {
                // 数据库连接失败
                return false;
            } else {
                try {
                    String query = "INSERT INTO user (user_id, user_pass, user_gmail, user_name) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, account);
                    preparedStatement.setString(2, pass);
                    preparedStatement.setString(3, "");
                    preparedStatement.setString(4, name);
                    preparedStatement.executeUpdate();

                    con.close();
                    return true; // 数据库操作成功
                } catch (Exception e) {
                    e.printStackTrace();
                    return false; // 数据库操作失败
                }
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                // 数据库操作成功
                System.err.println("gogo");
                Toast.makeText(register_page.this, "註冊成功", Toast.LENGTH_SHORT).show();

                // 创建Intent对象
                Intent intent = new Intent(register_page.this, login_page.class);

                // 启动登录页面的Activity
                startActivity(intent);
            } else {
                // 数据库操作失败
                Toast.makeText(register_page.this, "註冊失敗", Toast.LENGTH_SHORT).show();
            }
        }
    }


}

/*public class register_page extends AppCompatActivity {
    private AppCompatButton confirm;
    private AppCompatButton cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        confirm = findViewById(R.id.ConfirmButton);
        cancel = findViewById(R.id.CancelButton);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_page.this, login_page.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_page.this, login_page.class);
                startActivity(intent);
            }
        });

    }
}*/