package com.example.grandmassecrets.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grandmassecrets.Firebase.DataManager;
import com.example.grandmassecrets.Firebase.FireStorage;
import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Objects.User;
import com.example.grandmassecrets.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    // Firebase
    private final DataManager dataManager = DataManager.getInstance();
//    private final FirebaseFirestore db = dataManager.getDbFireStore();
    private FireStorage fireStorage ;

    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    // Attributes
    private Button sign_up_BTN_sign;
    private EditText sign_upf_EDT_first_name;
    private EditText sign_upf_EDT_last_name;
    // Profile Picture
    private FloatingActionButton signup_FAB_profile_edit;
    private ShapeableImageView sign_up_IMG_profile;
    private User tempUser;
    private String uriImg;
    private String urlImg;

    // TODO: 28/06/2022 Add Validator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fireStorage = FireStorage.getInstance();
        fireStorage.setCallBack_uploadImg(callBack_uploadImg);
        //getSupportFragmentManager().beginTransaction().replace(R.id.sign_up_RLY_cont_layout, new SignUpFragment()).commit();


        findViews();
        initButtons();

    }

    private void initButtons() {

        // The Sign In Button -> SAVE
        sign_up_BTN_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 28/06/2022 Add validators
                if (!sign_upf_EDT_first_name.getText().toString().isEmpty() && !sign_upf_EDT_last_name.getText().toString().isEmpty()) {

                    String userID = dataManager.getFirebaseAuth().getCurrentUser().getUid();
                    String userLastName = sign_upf_EDT_last_name.getText().toString();
                    String userFirstName = sign_upf_EDT_first_name.getText().toString();
                    String userPhoneNumber = dataManager.getFirebaseAuth().getCurrentUser().getPhoneNumber();
                    //Create a temp user from the data (after getting the photo insert to data base)
                    tempUser = new User(userID, userFirstName, userLastName, userPhoneNumber);

                    if (uriImg != null)
                        tempUser.setImg(uriImg);

                    // TODO: 28/06/2022 finish
                    dataManager.setCurrentUser(tempUser);
                    storeUserInDB(tempUser);

                } else { //the fields are Empty
                    Toast.makeText(SignUpActivity.this, "All Fields are Required", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


        // Pick Image from Gallery or Take Picture
        signup_FAB_profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(SignUpActivity.this)
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .crop(1f, 1f)
                        .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }


    // TODO: 28/06/2022 Check if i can do it otherwise
    private void storeUserInDB(User tempUser) {

        //Store the user UID by Phone number
        DatabaseReference myRef = realtimeDB.getReference(Keys.KEY_PHONE_TO_UID).child(tempUser.getPhoneNumber());
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    myRef.setValue(tempUser.getUid());
                }
            }
        });

        //Store the user in Firestore by UID when stored successfully move to Main Activity


//        db.collection(Keys.KEY_USERS)
//                .document(tempUser.getUid())
//                .set(tempUser)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("pttt", "DocumentSnapshot Successfully written!");
//                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
//                        finish();
//                    }
//
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("pttt", "Error adding document", e);
//                    }
//                });

    }

    private void findViews() {
        sign_up_BTN_sign = findViewById(R.id.sign_up_BTN_sign);
        sign_upf_EDT_first_name = findViewById(R.id.sign_upf_EDT_first_name);
        sign_upf_EDT_last_name = findViewById(R.id.sign_upf_EDT_last_name);
        signup_FAB_profile_edit = findViewById(R.id.signup_FAB_profile_edit);
        sign_up_IMG_profile = findViewById(R.id.sign_up_IMG_profile);
    }


    // TODO: 28/06/2022 Check if i can do it otherwise

    /**
     * This function Handel the Date Picker Result
     * The image will be store in the Firebase Storage.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri resultUri = data.getData();
        sign_up_IMG_profile.setImageURI(resultUri);
        fireStorage.uploadImgToStorage(resultUri,dataManager.getCurrentUser().getUid(),Keys.KEY_PROFILE_PICTURES,this);

        /*
        //View Indicates the process of the image uploading by Disabling the button
        sign_up_BTN_sign.setEnabled(false);

        //Get URI Data and show the image on the ImageView
        Uri uri = data.getData();
        sign_up_IMG_profile.setImageURI(uri);


        //Reference to where image will store in Storage
        // the UID in the images profiles
        StorageReference userRef = dataManager.getStorage()
                .getReference()
                .child(Keys.KEY_PROFILE_PICTURES)
                .child(dataManager.getFirebaseAuth().getCurrentUser().getUid());

        //Get the data from an ImageView as bytes
        sign_up_IMG_profile.setDrawingCacheEnabled(true);
        sign_up_IMG_profile.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) sign_up_IMG_profile.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();

        //Start The upload task
        UploadTask uploadTask = userRef.putBytes(bytes);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    //If upload was successful, We want to get the image url from the storage
                    userRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //Set the profile URL to the object we created
                            uriImg = uri.toString();


                            //View Indicates the process of the image uploading Done by making the button Enabled
                            sign_up_BTN_sign.setEnabled(true);
                        }
                    });
                }
            }
        });
*/
    }

    FireStorage.CallBack_UploadImg callBack_uploadImg=new FireStorage.CallBack_UploadImg() {
        @Override
        public void urlReady(String url, Activity activity) {
            urlImg=url;
        }
    };
}