package com.example.grandmassecrets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.grandmassecrets.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottom_nav_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nav_menu= findViewById(R.id.main_BNV_bottom_nav);
        bottom_nav_menu.getMenu().getItem(2).setEnabled(false);
    }
}