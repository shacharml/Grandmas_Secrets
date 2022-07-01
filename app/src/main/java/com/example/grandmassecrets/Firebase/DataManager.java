package com.example.grandmassecrets.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

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
        Log.d("manager",currentUser.toString());
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


    // Help Data Functions

    /**
     * GET REFERENCES
     */
    public DatabaseReference usersListReference(){
        return realTimeDB.getReference(Keys.KEY_USERS);
    }

    public DatabaseReference groupsListReference(){
        return realTimeDB.getReference(Keys.KEY_GROUPS);
    }

    public DatabaseReference recipesListReference(){
        return realTimeDB.getReference(Keys.KEY_RECIPES);
    }
    /**
     * READ FUNCTIONS
     */

    /**
     * every time the cuurent user from the auth
     * change in the database -> modify the data manager current user
     */
    public void currentUserChangeListener(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = usersListReference().child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);
                Log.d("manager", "data changed" + u.toString());
                setCurrentUser(u);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
            }
        });
    }

    /**
     * Write functions
     */

    //Function write user changes
    public void updateUserDatabase(){
        DatabaseReference myRef = usersListReference().child(currentUser.getUid());
        myRef.updateChildren(currentUser.toMap());
    }
}
