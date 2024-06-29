package com.example.week9_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText u_id = (EditText) findViewById(R.id.userId);
        EditText u_passwd = (EditText) findViewById(R.id.userPasswd);
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        TextView logScreen = (TextView) findViewById(R.id.logScreen);

        buttonLogin.setOnClickListener((e)-> {
            String userId = u_id.getText().toString();
            String userPw = u_passwd.getText().toString();

            String userInfo = userId + "\n" + userPw;
            logScreen.setText(userInfo);
        });

    }
}