package com.example.uibasic;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlCon {
    // 資料庫定義
    String mysql_ip = "資料庫IP";
    int mysql_port = 3306; // Port 預設為 3306
    String db_name = "資料庫名稱";
    //String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String url = "jdbc:mysql://db4free.net:3306/fish_tank1";
    String db_user = "fishtank1";
    String db_password = "108306009";

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }


}
