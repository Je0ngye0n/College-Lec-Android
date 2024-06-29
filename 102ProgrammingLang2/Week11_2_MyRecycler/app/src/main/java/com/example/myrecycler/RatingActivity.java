package com.example.myrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RatingActivity extends AppCompatActivity {

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
