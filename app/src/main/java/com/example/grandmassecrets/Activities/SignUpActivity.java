package com.example.grandmassecrets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.grandmassecrets.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //getSupportFragmentManager().beginTransaction().replace(R.id.sign_up_RLY_cont_layout, new SignUpFragment()).commit();

    }
}