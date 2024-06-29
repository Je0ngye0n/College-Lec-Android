package com.example.week6;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Thread w;
    boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart() {
        super.onStart();
        w = new Thread(new Runnable() { //핵심1
            public void run() {
                int i = 0;
                for (i = 0; i < 20 && running; i++) {
                    try {
                        Thread.sleep(1000); //핵심2
                    } catch (InterruptedException e) {
                    }
                    Log.v("mjc", "time=" + i);
                }
            }
        });
        running = true;
        w.start();
    }

    public void onStop() {
        super.onStop();
        running = false;
    }
}