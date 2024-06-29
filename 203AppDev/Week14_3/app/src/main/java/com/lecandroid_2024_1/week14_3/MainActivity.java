package com.lecandroid_2024_1.week14_3;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView = this.findViewById(R.id.videoView);
        MediaController mc = new MediaController(this) {
            public void show() { super.show(0);}
        };
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse
                ("android.resource://" + getPackageName() + "/" + R.raw.trailer));
        videoView.start();
        videoView.requestFocus();
        mc.setEnabled(true);
        mc.show(0);
    }
}