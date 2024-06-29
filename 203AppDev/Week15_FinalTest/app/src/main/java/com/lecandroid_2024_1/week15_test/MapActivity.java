package com.lecandroid_2024_1.week15_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Uri uri = Uri.parse(String.format("geo:%f,%f?z=10", 37.30, 127.2));
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }
}
