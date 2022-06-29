package com.example.grandmassecrets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Objects.Group;
import com.example.grandmassecrets.R;
import com.google.firebase.database.DatabaseReference;

public class CreateGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
    }

    private void storeGroupInDatabase(Group newGroup) {
    }
}