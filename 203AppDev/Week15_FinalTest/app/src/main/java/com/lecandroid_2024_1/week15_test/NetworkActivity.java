package com.lecandroid_2024_1.week15_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NetworkActivity extends AppCompatActivity  {

    Button btn_home;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        textView = findViewById(R.id.textView);

        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNet = manager.getActiveNetworkInfo();
            if (activeNet != null) {
                if (activeNet.getType() == ConnectivityManager.TYPE_WIFI) {
                    textView.setText("wifi");
                    Log.i("mjc", "wifi");
                } else if (activeNet.getType() == ConnectivityManager.TYPE_MOBILE) {
                    textView.setText("mobile");
                    Log.i("mjc", "moblie");
                }
            }
        } catch (Exception e) {
        }

        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        } );
    }
}
