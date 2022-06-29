package com.example.grandmassecrets;

import android.app.Application;

import com.example.grandmassecrets.Firebase.DataManager;
import com.example.grandmassecrets.Firebase.FireStorage;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FireStorage.initHelper(this);
        DataManager.initHelper();
    }
}
