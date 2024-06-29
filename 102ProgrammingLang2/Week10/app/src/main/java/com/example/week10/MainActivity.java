package com.example.week10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Calendar today, someday;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today = Calendar.getInstance();
        someday = Calendar.getInstance();

        tv = (TextView) findViewById(R.id.textView);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthYear, int dayMonth) {
                someday.set(year, monthYear, dayMonth);
                long diff = someday.getTimeInMillis() - today.getTimeInMillis();
                long days = diff / (1000 * 60 * 60 * 24);
                tv.setText(days + "일 남았습니다.");
            }
        });
    }
}