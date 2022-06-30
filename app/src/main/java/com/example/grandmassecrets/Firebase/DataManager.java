package com.example.grandmassecrets.Firebase;

import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Objects.Group;
import com.example.grandmassecrets.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.security.Key;

public class DataManager {

    //Singleton
    private static DataManager single_instance = null;

    //Firebase
    private final FirebaseAuth firebaseAuth;    // for the login with phone number
    private final FirebaseDatabase realTimeDB;  // for save objects data
    private final FirebaseStorage storage;      // for pictures and videos

    //Current Object helpers
    private User currentUser;
    private String currentIdGroup;
    private String currentIdRecipe;
    private String currentGroupCreator;

    //Constructor
    public DataManager() {
        firebaseAuth = FirebaseAuth.getInstance();
        realTimeDB = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public static DataManager initHelper() {
        if (single_instance == null) {
            single_instance = new DataManager();
        }
        return single_instance;
    }

    //Firebase Getters

    public static DataManager getInstance() {
        return single_instance;
    }

    public FirebaseDatabase getRealTimeDB() {
        return realTimeDB;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    //Builder
    public User getCurrentUser() {
        return currentUser;
    }

    public DataManager setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        return this;
    }

    public String getCurrentIdGroup() {
        return currentIdGroup;
    }

    public DataManager setCurrentIdGroup(String currentIdGroup) {
        this.currentIdGroup = currentIdGroup;
        return this;
    }

    public String getCurrentIdRecipe() {
        return currentIdRecipe;
    }

    public DataManager setCurrentIdRecipe(String currentIdRecipe) {
        this.currentIdRecipe = currentIdRecipe;
        return this;
    }

    public String getCurrentGroupCreator() {
        return currentGroupCreator;
    }

    public DataManager setCurrentGroupCreator(String currentGroupCreator) {
        this.currentGroupCreator = currentGroupCreator;
        return this;
    }


    //DataBase Functions


}
