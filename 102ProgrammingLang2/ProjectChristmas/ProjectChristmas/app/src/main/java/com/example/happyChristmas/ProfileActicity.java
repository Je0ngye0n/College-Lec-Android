package com.example.happyChristmas;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActicity extends AppCompatActivity {

    ImageButton imageBtn_santa, imageBtn_elf, imageBtn_deer;
    ImageView imageView_profile;
    
    Button nextBtn;
    String profileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        imageBtn_santa = (ImageButton) findViewById(R.id.imageButton);
        imageBtn_elf = (ImageButton) findViewById(R.id.imageButton2);
        imageBtn_deer = (ImageButton) findViewById(R.id.imageButton3);
        imageView_profile = (ImageView) findViewById(R.id.profile_img);

        nextBtn = (Button) findViewById(R.id.setProfileBtn);
        
        imageBtn_santa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_profile.setImageResource(R.drawable.santa_claus);
                profileInfo = SetProfile("선물");

                Toast.makeText(getApplicationContext(), "산타 선택", Toast.LENGTH_SHORT).show();
            }
        });

        imageBtn_elf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_profile.setImageResource(R.drawable.elf);
                profileInfo = SetProfile("트리");
                
                Toast.makeText(getApplicationContext(), "요정 선택", Toast.LENGTH_SHORT).show();
            }
        });

        imageBtn_deer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_profile.setImageResource(R.drawable.deer);
                profileInfo = SetProfile("썰매");

                Toast.makeText(getApplicationContext(), "사슴 선택", Toast.LENGTH_SHORT).show();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DecorationActivity.class);
                intent.putExtra("userProfile", profileInfo);
                startActivity(intent);
            }
        });

    }

    private String SetProfile(String things) {
        nextBtn.setText(things + " 꾸미러 가기");
        nextBtn.setVisibility(View.VISIBLE);

        return things;
    }
}

