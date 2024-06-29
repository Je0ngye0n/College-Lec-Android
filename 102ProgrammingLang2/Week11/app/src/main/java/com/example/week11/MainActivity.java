package com.example.week11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 데이터 생성
        String[] titles = {
                "명지분식",
                "진짜루",
                "황소식당",
                "미스터피자",
                "수인김치찜",
                "명지곱창",
                "스시하나에"
        };

        Integer[] images = {
                R.drawable.movie1,
                R.drawable.movie2,
                R.drawable.movie3,
                R.drawable.movie4,
                R.drawable.movie5,
                R.drawable.movie6,
                R.drawable.movie7
        };

        ArrayList<MyData> restaurants = new ArrayList<>();
        for (int i=0; i < 7; i++) {
            restaurants.add(new MyData(titles[i], 4.5, images[i]));
        }

        //2. 어댑터 준비
        MyAdapter adapter = new MyAdapter(this, R.layout.item_layout, restaurants);

        //3. 리스트뷰 가져와서 어댑터 장착
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}