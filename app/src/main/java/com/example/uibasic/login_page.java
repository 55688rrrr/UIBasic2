package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class login_page extends AppCompatActivity {
    private ImageButton login;
    private ImageButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

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

}