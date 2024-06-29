package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.RenderNode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //1. 변수 선언
//        //2. 뷰 가져오기
//        //3. 버튼으로 바꾸기
//
//        Button button = (Button) findViewById(R.id.button2);
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        int [] images = new int[]
//                {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
//
//        button.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                //랜덤하게 번호를 고름
//                Random r = new Random();
//                int index = r.nextInt(6);
//
//                //이미지 뷰에 있는 소스를 바꿔줌
//
//                imageView.setImageResource(images[index]);
//            }
//        });
//    }

    //멤버 변수 정의 하는 곳 => 여러 메소드에서 접근해야하는 변수

    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout_demo);

        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);

        textView1 = (TextView) findViewById(R.id.view1);
        textView2 = (TextView) findViewById(R.id.view2);
        textView2 = (TextView) findViewById(R.id.view3);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //로컬 변수 정의 하는 곳 => 이 메소드에서만 쓰고 버릴 변수

                //텍스트뷰에 있는 visibility값을 바꾸어 준다.
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);

                if (view.getId() == R.id.button1) {
                    textView1.setVisibility(View.VISIBLE);
                }
                else if (view.getId() == R.id.button2) {
                    textView2.setVisibility(View.VISIBLE);
                }
                else if (view.getId() == R.id.button3) {
                    textView3.setVisibility(View.VISIBLE);
                }
            }
        };

        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);
    }
}