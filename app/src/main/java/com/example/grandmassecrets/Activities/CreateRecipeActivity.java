package com.example.grandmassecrets.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Constants.NutritionFactsNames;
import com.example.grandmassecrets.Constants.UnitOfMeasure;
import com.example.grandmassecrets.Firebase.DataManager;
import com.example.grandmassecrets.Firebase.FireStorage;
import com.example.grandmassecrets.Objects.Ingredient;
import com.example.grandmassecrets.Objects.NutritionFacts;
import com.example.grandmassecrets.Objects.Recipe;
import com.example.grandmassecrets.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class CreateRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    //Fire base
    private final DataManager dataManager = DataManager.getInstance();
    private FireStorage fireStorage ;
    private String urlImg;

    //Attributes
    private final ArrayList<Ingredient> ingredients = new ArrayList<>(); //List of the ingredients
    private final ArrayList<NutritionFacts> facts = new ArrayList<>(); //List of the NutritionFacts

    //Views
    private LinearLayoutCompat create_recipe_LAY_ingredients_list;
    private MaterialButton create_recipe_BTN_add;
    private MaterialButton create_recipe_BTN_save;
    private TextInputEditText create_recipe_EDT_recipe;
    private TextInputEditText create_recipe_EDT_name;
    private TextInputEditText create_recipe_EDT_sub_title;
    private ImageButton button1, button2, button3, button4, button5;
    private ImageButton create_recipe_BTN_edit;
    private ImageView nutrition_IMG_nut1;
    private ImageView nutrition_IMG_nut2;
    private ImageView nutrition_IMG_nut3;
    private ImageView nutrition_IMG_nut4;
    private ImageView nutrition_IMG_nut5;
    private ShapeableImageView create_recipe_IMG_dish;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        findViews();
        fireStorage = FireStorage.getInstance();
        fireStorage.setCallBack_uploadImg(callBack_uploadImg);

        initNutritionFacts();

        // Init On Click Buttons
        create_recipe_BTN_add.setOnClickListener(this);
        create_recipe_BTN_save.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        create_recipe_BTN_edit.setOnClickListener(this);


        //Open the option to scroll in a text view
        create_recipe_EDT_recipe.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (create_recipe_EDT_recipe.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void initNutritionFacts() {
        facts.add(new NutritionFacts(NutritionFactsNames.ORGANIC , String.valueOf(R.drawable.ic_organic_food)));
        facts.add(new NutritionFacts(NutritionFactsNames.CARBOHYDRATES , String.valueOf(R.drawable.ic_carbohydrates)));
        facts.add(new NutritionFacts(NutritionFactsNames.NO_PEANUT , String.valueOf(R.drawable.ic_no_peanut)));
        facts.add(new NutritionFacts(NutritionFactsNames.DAIRY , String.valueOf(R.drawable.ic_dairy)));
        facts.add(new NutritionFacts(NutritionFactsNames.NO_EGG , String.valueOf(R.drawable.ic_no_eggs)));
    }

    private void findViews() {
        create_recipe_BTN_add = findViewById(R.id.create_recipe_BTN_add);
        create_recipe_BTN_save = findViewById(R.id.create_recipe_BTN_save);
        create_recipe_LAY_ingredients_list = findViewById(R.id.create_recipe_LAY_ingredients_list);
        create_recipe_EDT_recipe = findViewById(R.id.create_recipe_EDT_recipe);
        create_recipe_EDT_name = findViewById(R.id.create_recipe_EDT_name);
        create_recipe_EDT_sub_title = findViewById(R.id.create_recipe_EDT_sub_title);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        nutrition_IMG_nut1 = findViewById(R.id.nutrition_IMG_nut1);
        nutrition_IMG_nut2 = findViewById(R.id.nutrition_IMG_nut2);
        nutrition_IMG_nut3 = findViewById(R.id.nutrition_IMG_nut3);
        nutrition_IMG_nut4 = findViewById(R.id.nutrition_IMG_nut4);
        nutrition_IMG_nut5 = findViewById(R.id.nutrition_IMG_nut5);
        create_recipe_IMG_dish = findViewById(R.id.create_recipe_IMG_dish);
        create_recipe_BTN_edit = findViewById(R.id.create_recipe_BTN_edit);
    }

    /*
        On click  of all the Buttons in this form
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_recipe_BTN_add:
                addView();
                break;
            case R.id.create_recipe_BTN_save:
                saveNewRecipe();
                break;
            case R.id.button1:
                nutrition_IMG_nut1.setVisibility(View.GONE);
                button1.setVisibility(View.GONE);
                facts.remove(0);
                break;
            case R.id.button2:
                nutrition_IMG_nut2.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                facts.remove(1);
                break;
            case R.id.button3:
                nutrition_IMG_nut3.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                facts.remove(2);
                break;
            case R.id.button4:
                nutrition_IMG_nut4.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);
                facts.remove(3);
                break;
            case R.id.button5:
                nutrition_IMG_nut5.setVisibility(View.GONE);
                button5.setVisibility(View.GONE);
                facts.remove(4);
                break;
                case R.id.create_recipe_BTN_edit:
                imagePick();
                break;
        }
    }

    //Image Recipe Picker From Gallery
    private void imagePick() {
        ImagePicker.with(CreateRecipeActivity.this)
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .crop(1f, 1f)
                .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    /**
     * This function Handel the Date Picker Result
     * The image will be store in the Firebase Storage.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri resultUri = data.getData();
        //Change the view to the new image
        create_recipe_IMG_dish.setImageURI(resultUri);
        fireStorage.uploadImgToStorage(resultUri,dataManager.getCurrentUser().getUid(), Keys.KEY_PROFILE_PICTURES,this);

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

    /*
        Save the recipe into the firebase
     */
    private void saveNewRecipe() {

        Recipe tempRecipe = new Recipe();

        //Check the Ingredient data
        if (!checkIngredient()) {
            Toast.makeText(this, "all Details are require / incorrectly insert", Toast.LENGTH_SHORT).show();
            return;
        }
        //Add the ingredients to the temp recipe
        else
            tempRecipe.setIngredients(ingredients);

// TODO: 29/06/2022 Add validator
        //Check the recipe data
        if (create_recipe_EDT_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Recipe Name can't be empty ", Toast.LENGTH_SHORT).show();
            return;
        }else
            tempRecipe.setName(create_recipe_EDT_name.getText().toString());

        //get the description (can be Empty)
        tempRecipe.setDescription(create_recipe_EDT_sub_title.getText().toString());

        //Handel Image Picker & upload image to the Storage  and save the url
        if (urlImg != null)
            tempRecipe.setImg(urlImg);


        // TODO: 27/06/2022 Connect to data base data = save the data


        // TODO: 29/06/2022 Move to the next Intent(page)
    }

    private boolean checkIngredient() {
        //move over the ingredients into the linear layout and add them to the list ingredients
        for (int i = 0; i < create_recipe_LAY_ingredients_list.getChildCount(); i++) {

            //get the ingredient Card on the position i
            View ingredientCard = create_recipe_LAY_ingredients_list.getChildAt(i);

            TextInputEditText ingredientName = ingredientCard.findViewById(R.id.ingredients_card_EDT_name);
            RadioGroup radioGroup = ingredientCard.findViewById(R.id.ingredients_card_BTN_radioGroup);
            MaterialTextView amount = ingredientCard.findViewById(R.id.ingredients_card_LBL_amount);

            //temp ingredient
            Ingredient temp = new Ingredient();

            // TODO: 29/06/2022 Add validator class
            //check validation of Name
            if (!ingredientName.getText().toString().isEmpty())
                temp.setNameIngredient(ingredientName.getText().toString());
            else
                return false;

            //check validation of amount
            if (Double.parseDouble(amount.getText().toString()) != 0.0)
                temp.setAmount(Double.parseDouble(amount.getText().toString()));
            else
                return false;

            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radio_button_2:
                    temp.setUnitOfMeasure(UnitOfMeasure.TEASPOON);
                    break;
                case R.id.radio_button_3:
                    temp.setUnitOfMeasure(UnitOfMeasure.TABLESPOON);
                    break;
                case R.id.radio_button_4:
                    temp.setUnitOfMeasure(UnitOfMeasure.ONCE);
                    break;
                default: //also for radio button1
                    temp.setUnitOfMeasure(UnitOfMeasure.CUP);
            }

            //Add to ingredients list
            ingredients.add(temp);
        }

        if (ingredients.size() == 0) {
            Toast.makeText(this, "Add Ingredient!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void addView() {

        final View ingredientCard = getLayoutInflater().inflate(R.layout.ingredients_add_card, null, false);
        // Attributes In Card Ingredient Add
        TextInputEditText ingredientName = ingredientCard.findViewById(R.id.ingredients_card_EDT_name);
        RadioGroup radioGroup = ingredientCard.findViewById(R.id.ingredients_card_BTN_radioGroup);
        MaterialTextView amount = ingredientCard.findViewById(R.id.ingredients_card_LBL_amount);
        ImageButton deleteBtn = ingredientCard.findViewById(R.id.ingredients_card_BTN_delete);
        MaterialButton plusBtn = ingredientCard.findViewById(R.id.ingredients_card_BTN_plus);
        MaterialButton minusBtn = ingredientCard.findViewById(R.id.ingredients_card_BTN_minus);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(ingredientCard);
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// TODO: 27/06/2022 add all the options
                double min = Double.parseDouble(amount.getText().toString());
                if (min > 0.0) {
                    amount.setText("" + (min - 0.25));
                }
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// TODO: 27/06/2022 add all the options
                double max = Double.parseDouble(amount.getText().toString());
                if (max < 99.9) {
                    amount.setText("" + (max + 0.25));
                }
            }
        });

        create_recipe_LAY_ingredients_list.addView(ingredientCard);
    }

    private void removeView(View view) {
        create_recipe_LAY_ingredients_list.removeView(view);
    }


    FireStorage.CallBack_UploadImg callBack_uploadImg=new FireStorage.CallBack_UploadImg() {
        @Override
        public void urlReady(String url, Activity activity) {
            urlImg=url;
        }
    };
}