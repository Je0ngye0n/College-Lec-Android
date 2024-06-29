package com.lecandroid_2024_1.week9;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button get = findViewById(R.id.get);
        display = findViewById(R.id.display);

        get.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    ConnectivityManager manager = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNet = manager.getActiveNetworkInfo();
                    if (activeNet != null) {
                        if (activeNet.getType() == ConnectivityManager.TYPE_WIFI) {
                            display.setText(activeNet.toString());
                            Log.i("mjc", "wifi");
                        }
                        else if (activeNet.getType() == ConnectivityManager.TYPE_MOBILE) {
                            display.setText(activeNet.toString());
                            Log.i("mjc", "mobile");
                        }
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}