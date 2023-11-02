package com.example.uibasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register_page extends AppCompatActivity {
    private EditText fullName;
    private EditText account;
    private EditText password;
    private AppCompatButton confirmR;
    private AppCompatButton cancel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        fullName = findViewById(R.id.NameInput);
        account = findViewById(R.id.AccountInput);
        password = findViewById(R.id.PasswordInput);
        confirmR = findViewById(R.id.ConfirmButton);
        cancel = findViewById(R.id.CancelButton);

        //按下確認註冊按鈕後會check輸入的資料
        confirmR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
                //如果輸入的資料無誤 則在畫面下方顯示註冊成功 反之則失敗
                if(fullName.getError() != null ||account.getError() != null ||password.getError() != null ){
                    Toast t = Toast.makeText(getApplicationContext(), "註冊失敗!", Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    Toast t = Toast.makeText(getApplicationContext(), "註冊成功!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }


        });

        //按下取消按鈕後會轉移到登入頁面
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_page.this, login_page.class);
                startActivity(intent);
            }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(fullName)) {
            fullName.setError("您必須輸入姓名!");
        }
        if (isEmpty(account)) {
            account.setError("您必須輸入帳號!");
        }
        if (isEmpty(password)) {
            password.setError("您必須輸入密碼!");
        }

    }
}