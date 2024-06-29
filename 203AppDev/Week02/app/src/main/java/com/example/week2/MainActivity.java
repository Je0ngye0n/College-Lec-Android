package com.example.week2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MusicService";
    Button start, stop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View  v) {
                Log.d(TAG, "onClick() start");
                startService(new Intent(MainActivity.this, MusicService.class));
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View  v) {
                Log.d(TAG, "onClick() stop");
                stopService(new Intent(MainActivity.this, MusicService.class));
            }
        });
    }
}