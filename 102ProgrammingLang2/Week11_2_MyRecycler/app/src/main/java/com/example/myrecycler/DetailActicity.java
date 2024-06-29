package com.example.myrecycler;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActicity extends AppCompatActivity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rating_detail);
//
//        Intent intent = getIntent();
//        String name = intent.getStringExtra("title");
//        int index = intent.getIntExtra("index", 0);
//        //받아온 이름을 textView에 작성함
//
//        Button nameBtn = (Button) findViewById(R.id.button5);
//        nameBtn.setText(name);
//
//        Button btn = (Button) findViewById(R.id.ratingButton);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), RatingActivity.class);
//                intent.putExtra("title", nameBtn.getText());
//                //intent.putExtra("index", position);
//                //StartActicity.launch(intent);
//            }
//        });
//    }

    float ratingValue;
    @Override
    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acticity);

        Intent intent = getIntent();
        String name = intent.getStringExtra("title");
        int index = intent.getIntExtra("index", 0);
        TextView nameView = (TextView) findViewById(R.id.detail_textView);
        nameView.setText(name);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingValue = ratingBar.getRating();
                Log.d("Detail", "rating : " + ratingValue);
            }
        });

        Button backBtn = (Button) findViewById(R.id.button);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                backIntent.putExtra("rating", ratingValue);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}