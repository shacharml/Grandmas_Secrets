package com.example.grandmassecrets.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class dataManager {

    private static dataManager single_instance = null;

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore dbFireStore;
    private final FirebaseStorage storage;
    // TODO: 21/06/2022 Check if realTime needed
//    private final FirebaseDatabase realTimeDB;

    public dataManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.dbFireStore = FirebaseFirestore.getInstance();;
        this.storage = FirebaseStorage.getInstance();

    }



}
