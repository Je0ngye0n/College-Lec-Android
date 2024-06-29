package com.example.week5;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 3;
    public DBHelper(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts ( _id INTEGER PRIMARY KEY"
                + " AUTOINCREMENT, name TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name;
    TextView edit_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        try {
          db = helper.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        edit_name = (EditText) findViewById(R.id.name);
        edit_result = (TextView) findViewById(R.id.textView);
    }

    public void insert(View target) {
        String name = edit_name.getText().toString();
        db.execSQL("INSERT INTO contacts VALUES (null, '" + name + "');");
        Toast.makeText(getApplicationContext(), "성공적으로 추가되었음", Toast.LENGTH_SHORT).show();
        edit_name.setText("");
    }

    public void select_all(View target) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM contacts", null);

        String s = "Id     Name   \r\n";
        while (cursor.moveToNext()) {
            s += cursor.getString(0) + "     ";
            s += cursor.getString(1) + "     \r\n";
        }
        edit_result.setText(s);
    }

    //전체 삭제 구현해보기!
    public void drop(View target) {
        
//        //나의 풀이 > 좀 별로...
//        String name = edit_name.getText().toString();
//        db.execSQL("DELETE FROM contacts");
//        edit_result.setText("");
        
        //교수님 풀이
        Cursor cursor;
        cursor = db.rawQuery("DELETE FROM contacts", null);

        String s = "Id     Name   \r\n";
        while (cursor.moveToNext()) {
            s += cursor.getString(0) + "     ";
            s += cursor.getString(1) + "     \r\n";
        }
        edit_result.setText(s);
    }
}