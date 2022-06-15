package com.example.grandmassecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.google.android.material.button.MaterialButton;

public class SplashActivity extends AppCompatActivity {

    VideoView splash_VID_video;
//    ImageView splash_IMG_screenshot;
    MaterialButton splash_BTN_loginSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_splash);

        splash_BTN_loginSplash = findViewById(R.id.splash_BTN_loginSplash);
        splash_VID_video = findViewById(R.id.splash_VID_video);

        String path = "android.resource://com.example.grandmassecrets/"+R.raw.splash_video;
        Uri uri = Uri.parse(path);
        splash_VID_video.setVideoURI(uri);
        splash_VID_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        //for the video will be playing again and again
        splash_VID_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        //moving to the login/register page
        splash_BTN_loginSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this , LoginActivity.class));
                finish(); //the splash page id dead cant go back to it
            }
        });

    }
}