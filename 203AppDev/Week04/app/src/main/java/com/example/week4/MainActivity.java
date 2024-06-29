package com.example.week4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    //실습 예제 1, 2
//    String FILENAME = "test.txt";
//    EditText edit;


    //실습 예제 3
    public static final String PREFS_NAME = "MyPrefs";
    TextView name;
    EditText value;
    String sName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //실습 예제 1, 2
        //setContentView(R.layout.activity_main);

        //실습 예제 3
        setContentView(R.layout.activity_example3);


        //**실습 예제 01
//        edit = (EditText) findViewById(R.id.EditText01);
//        Button readButton = (Button) findViewById(R.id.read);
//        readButton.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                //파일이 없는 경우에도 프로그램이 돌아갈 수 있도록 try-catch 구문을 이용하여 예외 처리
//                try {
//                    FileInputStream fis = openFileInput(FILENAME);
//                    byte[] buffer = new byte[fis.available()]; //이 예제에서는 byte 단위로 한글자씩 읽어옴
//                    fis.read(buffer);
//                    edit.setText(new String(buffer));
//                    fis.close();
//                }
//                catch (IOException e) {
//                    //토스트 메시지 등을 이용하여 어떤 예외가 발생했는지 작성하면 좋다!
//                }
//            }
//        });
//
//        Button writeButton = (Button) findViewById(R.id.write);
//        writeButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                try {
//                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//                    fos.write(edit.getText().toString().getBytes()); //getBytes > 깨지지 않고 텍스트 가져올 수 있음
//                    fos.close();
//                }
//                catch (IOException e) {
//                }
//            }
//        });


//        //**실습 예제 02
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)==false) {
//            Toast.makeText(this, "외부 스토리지 실패", Toast.LENGTH_SHORT).show();
//        }
//
//        edit = (EditText) findViewById(R.id.EditText01);
//
//        Button readButton = (Button) findViewById(R.id.read);
//        readButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                File file = new File(getExternalFilesDir(null), FILENAME);
//                try {
//                    InputStream is;
//                    is = new FileInputStream(file);
//                    byte[] buffer = new byte[is.available()];
//                    is.read(buffer);
//                    edit.setText(new String(buffer));
//                    is.close();
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        Button writeButton = (Button) findViewById(R.id.write);
//        writeButton.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                File file = new File(getExternalFilesDir(null), FILENAME);
//                try {
//                    OutputStream os = new FileOutputStream(file);
//                    os.write(edit.getText().toString().getBytes());
//                    os.close();
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


            //**실습 예제 03
           name = (TextView) findViewById(R.id.TextView02);
          value = (EditText) findViewById(R.id.EditText02);

           SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            sName = settings.getString("Name", "");
            value.setText(sName);
        }

        @Override
        protected void onStop() {
            super.onStop();

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

           SharedPreferences.Editor editor = settings.edit();
           sName = value.getText().toString();
            editor.putString("Name", sName);

            editor.commit();
    }
}