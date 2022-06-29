package com.example.grandmassecrets.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Firebase.DataManager;
import com.example.grandmassecrets.Firebase.FireStorage;
import com.example.grandmassecrets.Listeners.CallBack_ImageUpload;
import com.example.grandmassecrets.Objects.Group;
import com.example.grandmassecrets.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;

public class CreateGroupActivity extends AppCompatActivity {

    //Firebase
    private final DataManager dataManager = DataManager.getInstance();
    private FireStorage fireStorage;

    //Views
    private ShapeableImageView create_group_IMG_img;
    private FloatingActionButton create_group_FAB_edit;
    private TextInputEditText create_group_EDT_name;
    private TextInputEditText create_group_EDT_description;
    private Button create_group_BTN_save;
    private AppCompatImageButton create_group_BTN_back;

    //Attributes
    private String urlImg;
    //CallBack
    CallBack_ImageUpload callBack_Image_upload = new CallBack_ImageUpload() {
        @Override
        public void imageUrlAvailable(String url, Activity activity) {
            urlImg = url;
        }
    };
    private Group tempGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        findViews();
        //Init fireStorage
        fireStorage = FireStorage.getInstance();
        fireStorage.setCallBack_imageUpload(callBack_Image_upload);
        initButtons();

        //Temp Group Creation
        tempGroup = new Group();
        tempGroup.setGroupCreator(dataManager.getCurrentUser().getUid());
        tempGroup.getUsersIds().add(dataManager.getCurrentUser().getUid());

    }

    private void initButtons() {
        //Save to database the new Group
        create_group_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGroup();
            }
        });

        //Insert Group Photo from gallery with image picker
        create_group_FAB_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePick();
            }
        });

        //Back button clicked -> finish the activity
        create_group_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findViews() {
        create_group_IMG_img = findViewById(R.id.create_group_IMG_img);
        create_group_FAB_edit = findViewById(R.id.create_group_FAB_edit);
        create_group_EDT_name = findViewById(R.id.create_group_EDT_name);
        create_group_EDT_description = findViewById(R.id.create_group_EDT_description);
        create_group_BTN_save = findViewById(R.id.create_group_BTN_save);
        create_group_BTN_back = findViewById(R.id.create_group_BTN_back);
    }

    //Image Recipe Picker From Gallery
    private void imagePick() {
        ImagePicker.with(CreateGroupActivity.this)
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .crop(1f, 1f)
                .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    /**
     * This function Handel the Image Picker Result
     * The image will be store in the Firebase Storage.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri resultUri = data.getData();
        //Change the view to the new image
        create_group_IMG_img.setImageURI(resultUri);
        fireStorage.uploadImgToStorage(resultUri, dataManager.getCurrentIdGroup(), Keys.KEY_GROUP_PICTURES, CreateGroupActivity.this);

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

    //create  the new Group to database
    private void createNewGroup() {

        //current Group save on data manager and creator (IDS)
        dataManager.setCurrentIdGroup(tempGroup.getIdGroup());
        dataManager.setCurrentGroupCreator(dataManager.getCurrentUser().getUid());

        //Handel Image Picker & upload image to the Storage  and save the url
        if (urlImg != null)
            tempGroup.setImgGroup(urlImg);
        else {
            Toast.makeText(this, "U need to upload IMG ", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO: 29/06/2022 Add Validator 
        //Get Group Name
        if (create_group_EDT_name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Group Name can't be empty ", Toast.LENGTH_SHORT).show();
            return;
        } else
            tempGroup.setName(create_group_EDT_name.getText().toString());

        //get the description (can be Empty)
        tempGroup.setDescription(create_group_EDT_description.getText().toString());

        //Save and move to the next Activity
        SaveNewGroupToDatabase(tempGroup);
    }

    private void SaveNewGroupToDatabase(Group tempGroup) {
        //Save tempGroup to Recipe List
        DatabaseReference ref = dataManager.getRealTimeDB().getReference(Keys.KEY_GROUPS).child(tempGroup.getIdGroup());
        ref.child(Keys.KEY_GROUP_ID).setValue(tempGroup.getIdGroup());
        ref.child(Keys.KEY_GROUP_NAME).setValue(tempGroup.getName());
        ref.child(Keys.KEY_GROUP_DESCRIPTION).setValue(tempGroup.getDescription());
        ref.child(Keys.KEY_GROUP_IMG).setValue(tempGroup.getImgGroup());
        ref.child(Keys.KEY_GROUP_CREATOR).setValue(tempGroup.getGroupCreator());
        ref.child(Keys.KEY_GROUP_RECIPES_LIST).setValue(tempGroup.getRecipesIds());
        ref.child(Keys.KEY_GROUP_USERS_LIST).setValue(tempGroup.getUsersIds());

        //After The Save - need to add this Group to the User (creator) Group List
        //Add the Group to Current User
        dataManager.getCurrentUser().getGroupsIds().add(tempGroup.getIdGroup());
        DatabaseReference refUser = dataManager.getRealTimeDB()
                .getReference(Keys.KEY_USERS)
                .child(dataManager.getCurrentUser().getUid())
                .child(Keys.KEY_USER_GROUPS_IDS);

        refUser.child(dataManager.getCurrentUser().getGroupsIds().size() + "")
                .setValue(tempGroup.getIdGroup())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CreateGroupActivity.this, "new Group added to User creator DB",Toast.LENGTH_SHORT).show();
            }
        });


        //Move to the Next Activity in the application
        nextActivity();
    }

    // TODO: 29/06/2022 Change next activity if needed
    private void nextActivity() {
        startActivity(new Intent(CreateGroupActivity.this, MainActivity.class));
        finish();
    }
}