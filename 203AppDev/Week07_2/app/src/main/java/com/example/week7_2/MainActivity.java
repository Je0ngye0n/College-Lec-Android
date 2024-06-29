package com.example.week7_2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button gameButton;
    private TextView scoreTextVew;
    private RelativeLayout layout;
    private int score = 0;
    private Handler handler = new Handler();
    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameButton = findViewById(R.id.gameButton);
        layout = findViewById(R.id.layout);
        scoreTextVew = findViewById(R.id.scoreTextVew);

        gameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                score++;
                scoreTextVew.setText("점수 : " + score);
                changeButtonPosition();
            }
        });

        //버튼을 안 눌렀더라도 3초 뒤에 버튼 위치를 변경
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeButtonPosition();
            }
        }, 3000);
    }

    private void changeButtonPosition() {
        int screenWidth = layout.getWidth();
        int screenHeight = layout.getHeight();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.leftMargin = random.nextInt(screenWidth - gameButton.getWidth());
        params.topMargin = random.nextInt(screenHeight - gameButton.getHeight());

        gameButton.setLayoutParams(params);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeButtonPosition();
            }
        }, 3000);
    }
}