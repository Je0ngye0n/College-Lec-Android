package com.example.happyChristmas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //로딩 화면 보여주기 위한 handler 메서드
        //handler : 백그라운드 스레스에서 작업 후 UI 스레드에 내용 보냄
                  //러너블 객체를 사용하여 다른 스레드에 데이터를 전송하고 통신할 수 있음
                  //postDelayed 메서드를 사용하여 일정 시간이 지난 후에 특정 작업을 수행하도록 예약할 수 있음

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500); //3초 동안 인트로 표시!
    }
}
