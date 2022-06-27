package com.example.grandmassecrets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.grandmassecrets.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayoutCompat create_recipe_LAY_ingredients_list;
    private Button create_recipe_BTN_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        findViews();

        create_recipe_BTN_add.setOnClickListener(this);

        // TODO: 27/06/2022 Connect database data

    }


    private void findViews() {
        create_recipe_BTN_add = findViewById(R.id.create_recipe_BTN_add);
        create_recipe_LAY_ingredients_list = findViewById(R.id.create_recipe_LAY_ingredients_list);
    }

    @Override
    public void onClick(View view) {
        addView();
    }

    private void addView() {
// TODO: 27/06/2022 Connect to data base data
       final View ingredientCard = getLayoutInflater().inflate(R.layout.ingredients_add_card,null,false);

       double counter =0.0;

        // Attributes In Card Ingredient Add
        TextInputEditText ingredientName = (TextInputEditText)ingredientCard.findViewById(R.id.ingredients_card_EDT_name);
        RadioGroup radioGroup = (RadioGroup)ingredientCard.findViewById(R.id.ingredients_card_BTN_radioGroup);
        ImageButton deleteBtn = (ImageButton)ingredientCard.findViewById(R.id.ingredients_card_BTN_delete);
        MaterialButton plusBtn = (MaterialButton)ingredientCard.findViewById(R.id.ingredients_card_BTN_plus);
        MaterialButton minusBtn = (MaterialButton)ingredientCard.findViewById(R.id.ingredients_card_BTN_minus);
        MaterialTextView count = (MaterialTextView)ingredientCard.findViewById(R.id.ingredients_card_LBL_amount);

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
                double min = Double.parseDouble(count.getText().toString());
                if (min > 0.0){
                    count.setText(""+(min-0.25));
                }
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// TODO: 27/06/2022 add all the options
                double max = Double.parseDouble(count.getText().toString());
                if (max < 99.9){
                    count.setText(""+(max+0.25));
                }
            }
        });

        create_recipe_LAY_ingredients_list.addView(ingredientCard);
    }


    private void removeView(View view) {
        create_recipe_LAY_ingredients_list.removeView(view);
    }


}