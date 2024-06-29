package com.lecandroid_2024_1.week_midtest_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts ( _id INTEGER PRIMARY KEY" +
                " AUTOINCREMENT, name TEXT);");
    }

    @Override
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

    public static final String TAG = "onStop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex){
            db = helper.getReadableDatabase();
        }

        edit_name = findViewById(R.id.edit_name);
        edit_result = findViewById(R.id.results);

    }

    public void insert(View target) {
        String name = edit_name.getText().toString();

        db.execSQL("INSERT INTO contacts VALUES (null, '" + name + "');");
        edit_name.setText("");

        SharedPreferences pref = getSharedPreferences("MyPrefs", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name);
        editor.apply();

    }

    public void selectAll(View target) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM contacts", null);

        String s = "";
        while (cursor.moveToNext()) {
            s += cursor.getString(1);
        }
        edit_result.setText(s);
    }

    public void onStop() {
        super.onStop();
        Log.d("TAG", "onStop");
    }
}