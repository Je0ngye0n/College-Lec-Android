package com.example.happyChristmas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DecorationActivity extends AppCompatActivity {

    TextView countdownTextView;
    ImageView decoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration);

        countdownTextView = findViewById(R.id.dayCountView);
        decoImageView = findViewById(R.id.decoBackgroundView);

        Intent intent = getIntent();
        if (intent.getStringExtra("userProfile").equals("선물")){
            decoImageView.setImageResource(R.drawable.present);
        }
        else if (intent.getStringExtra("userProfile").equals("트리")) {
            decoImageView.setImageResource(R.drawable.christmas_tree);
        }
        else {
            decoImageView.setImageResource(R.drawable.sled);
        }

        displayCountdown();

        //그리드뷰
        ArrayList<ItemDeco> decoList = new ArrayList<>();

        decoList.add(new ItemDeco("종", R.drawable.bell));
        decoList.add(new ItemDeco("양말", R.drawable.christmas_sock));
        decoList.add(new ItemDeco("눈사람", R.drawable.snowman));
        decoList.add(new ItemDeco("꽃", R.drawable.poinsettia));
        decoList.add(new ItemDeco("막대 사탕", R.drawable.candy_cane));
        decoList.add(new ItemDeco("월계수", R.drawable.christmas_wreath));
        decoList.add(new ItemDeco("산타", R.drawable.santa_claus));

        DecoAdapter adapter = new DecoAdapter(decoList);

        GridView gridView = findViewById(R.id.gridView);
        
        //어댑터(데이터) 그리드뷰(UI)에 담음
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //아이템이 클릭되었을 때 ListView에서 해당 아이템의 데이터를 가져옴
                ItemDeco itemValue = (ItemDeco) parent.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), itemValue.getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //카운드 다운 문구 출력
    private void displayCountdown() {
        //LocalDate : 시간 정보를 포함하지 않고 날짜만을 나타냄
        LocalDate currentDate = LocalDate.now();

        // 크리스마스 날짜 설정
        LocalDate christmasDate = LocalDate.of(currentDate.getYear(), Month.DECEMBER, 25);

        // 크리스마스까지 남은 일수 계산
        long betweenDays = ChronoUnit.DAYS.between(currentDate, christmasDate);
        
        displayCountdownNum(betweenDays);
    }

    //남은 일수 UI 세팅
    private void displayCountdownNum(long christDays) {
        String countText = "크리스마스까지 " + christDays + "일 남았어!";
        countdownTextView.setText(countText);
    }
}
