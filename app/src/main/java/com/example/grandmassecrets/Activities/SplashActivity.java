package com.example.grandmassecrets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.grandmassecrets.Firebase.DataManager;
import com.example.grandmassecrets.Listeners.Callback_isUserExist;
import com.example.grandmassecrets.R;
import com.google.android.material.button.MaterialButton;

public class SplashActivity extends AppCompatActivity {

    VideoView splash_VID_video;
    MaterialButton splash_BTN_loginSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_splash);

        //Check if the user is already existing
//        DataManager dataManager = DataManager.getInstance();
//        dataManager.setCallback_isUserExist(Callback_isUserExist);


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

//    Callback_isUserExist callback_isUserExist = new Callback_isUserExist() {
//        @Override
//        public void isExist() {
//            moveActivity(MainActivity.class);
//        }
//
//        @Override
//        public void createUser() {
//            moveActivity(SignUpActivity.class);
//        }
//    } ;

}