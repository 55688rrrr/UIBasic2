package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final TextView text_view = (TextView) findViewById(R.id.text_view);

        new Thread(new Runnable(){
            @Override
            public void run(){
                MysqlCon con = new MysqlCon();
                con.run();
                final String data = con.getData();
                Log.v("OK",data);
                text_view.post(new Runnable() {
                    public void run() {
                        text_view.setText(data);
                    }
                });

            }
        }).start();*/
    }
}