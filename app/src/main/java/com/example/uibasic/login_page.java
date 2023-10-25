package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private AppCompatButton login;
    private AppCompatButton registerS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        account = findViewById(R.id.AccountInput);
        password = findViewById(R.id.PasswordInput);
        login = findViewById(R.id.LoginButton);
        registerS = findViewById(R.id.RegisterButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
                if(account.getError() != null ||password.getError() != null ){
                    Toast t = Toast.makeText(getApplicationContext(), "登入失敗!", Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    Toast t = Toast.makeText(getApplicationContext(), "登入成功!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        //按註冊按鈕會轉移到註冊頁面
        registerS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, register_page.class);
                startActivity(intent);
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void checkDataEntered() {
        if (isEmpty(account)) {
            account.setError("您必須輸入帳號!");
        }
        if (isEmpty(password)) {
            password.setError("您必須輸入密碼!");
        }
        //連結資料庫之後 這裡可以check資料庫是否有相對應的帳號/密碼，如果沒有就跳error
    }
}