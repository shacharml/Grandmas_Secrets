package com.example.grandmassecrets.Firebase;

import com.example.grandmassecrets.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class DataManager {

    private static DataManager single_instance = null;

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore dbFireStore; // ??
    private final FirebaseStorage storage;
    private final FirebaseDatabase realTimeDB;


    private User currentUser;
//    private String currentListUid;
//    private String currentListCreator;
//    private MyItem currentItem;
//    private String currentCategoryUid;
//    private String currentListTitle;
//    private String token;

    public DataManager() {
        firebaseAuth = FirebaseAuth.getInstance();
        dbFireStore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        realTimeDB = FirebaseDatabase.getInstance("https://superme-e69d5-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    public static DataManager initHelper() {
        if (single_instance == null) {
            single_instance = new DataManager();
        }
        return single_instance;
    }

    //Firebase Getters
    public FirebaseFirestore getDbFireStore() {
        return dbFireStore;
    }

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


    public User getCurrentUser() {
        return currentUser;
    }
    //builder
    public DataManager setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        return this;
    }
}
