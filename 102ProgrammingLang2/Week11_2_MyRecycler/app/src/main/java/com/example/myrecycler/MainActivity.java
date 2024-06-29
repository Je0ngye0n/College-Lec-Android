package com.example.myrecycler;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView ratingTextView;
    ActivityResultLauncher<Intent> mStartActicity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK) {
                        Intent intent = o.getData();
                        float ratingValue = intent.getFloatExtra("rating", 0.0f);
                        ratingTextView = (TextView) findViewById(R.id.textView4);
                        ratingTextView.setText(String.valueOf(ratingValue));
                    }
                }
            });

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

        ArrayList<Data> restaurants = new ArrayList<>();
        for (int i=0; i < 7; i++) {
            restaurants.add(new Data(titles[i], 4.5, images[i]));
        }

        //어댑터에 보낼 리스너를 정의
        OnMyItemClickListener listener = new OnMyItemClickListener() {
            @Override
            public void OnItemClick(MyAdapter.ViewHolder holder, View view, int position) {
                //토스트 메시지 만들어 본다.(제대로 넘겨졌는지 확인하기 위해)
                Toast.makeText(getApplicationContext(),
                        restaurants.get(position).name + " 선택됨", Toast.LENGTH_LONG)
                        .show();
                //상세페이지 액티비티 호출함
                Intent intent = new Intent(getApplicationContext(), RatingActivity.class);
                intent.putExtra("title", restaurants.get(position).name);
                intent.putExtra("index", position);
                //mStartActicity.launch(intent);
                startActivity(intent);
            }
        };

        //adapter
        MyAdapter adapter = new MyAdapter(restaurants, listener);

        //리사이클러뷰 세팅
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}