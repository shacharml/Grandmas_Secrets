package com.example.grandmassecrets.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grandmassecrets.Constants.NutritionFactsNames;
import com.example.grandmassecrets.Objects.Ingredient;
import com.example.grandmassecrets.Objects.NutritionFacts;
import com.example.grandmassecrets.Objects.Recipe;
import com.example.grandmassecrets.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public interface RecipeListener {
                void recipeClicked(Recipe recipe, int position);
        }

    private Activity activity;
//    private HashMap<String,Recipe> recipes = new HashMap<>();
    private ArrayList<Recipe> recipes = new ArrayList<>(); //Fore arrayList
    private RecipeListener listener;

    //Constructor
    public RecipeAdapter(Activity activity, ArrayList<Recipe> Recipes) {
        this.activity = activity;
        this.recipes = Recipes;
    }


//    public RecipeAdapter(Activity activity, HashMap<String, Recipe> recipes) {
//        this.activity = activity;
//        this.recipes = recipes;
//    }

    public RecipeAdapter setListener(RecipeListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_card, parent, false);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        final RecipeHolder holder = (RecipeHolder) viewHolder;
        //get the recipe in the specific position
        Recipe recipe = getRecipe(position);
        //Update the ui
        holder.recipe_item_LBL_title.setText(recipe.getName());
        holder.recipe_item_LBL_sub_title.setText(recipe.getDescription());

        // Get the image of the recipe dish from Data Storage WIth Glide
            // TODO: 29/06/2022 Check if glide work with thr uri/l image
        Glide.with(activity).load(recipe.getImg()).into(holder.recipe_item_IMG_img);

        ArrayList<NutritionFacts> nutFactsRecipe = recipe.getNutritionFacts().values().stream().collect(Collectors.toCollection(ArrayList::new));
        //get the Images of the nutrition facts (not from data Storage
        for (NutritionFacts facts : nutFactsRecipe) { //for arrayList
             switch (facts.getNameNutrition()) {
                 case ORGANIC:
                        holder.nutrition_IMG_nut1.setImageResource(R.drawable.ic_organic_food);
                        holder.nutrition_IMG_nut1.setVisibility(View.VISIBLE);
                        break;
                    case CARBOHYDRATES:
                        holder.nutrition_IMG_nut2.setImageResource(R.drawable.ic_carbohydrates);
                        holder.nutrition_IMG_nut2.setVisibility(View.VISIBLE);
                        break;
                    case DAIRY:
                        holder.nutrition_IMG_nut3.setImageResource(R.drawable.ic_dairy);
                        holder.nutrition_IMG_nut3.setVisibility(View.VISIBLE);
                        break;
                    case NO_EGG:
                        holder.nutrition_IMG_nut4.setImageResource(R.drawable.ic_no_eggs);
                        holder.nutrition_IMG_nut4.setVisibility(View.VISIBLE);
                        break;
                    case NO_PEANUT:
                        holder.nutrition_IMG_nut5.setImageResource(R.drawable.ic_no_peanut);
                        holder.nutrition_IMG_nut5.setVisibility(View.VISIBLE);
                        break;
                }



//        for (Map.Entry<String, NutritionFacts> entry : recipe.getNutritionFacts().entrySet() ) {
//            switch (entry.getKey()) {
//                case "ORGANIC":
//                    holder.nutrition_IMG_nut1.setImageResource(R.drawable.ic_organic_food);
//                    holder.nutrition_IMG_nut1.setVisibility(View.VISIBLE);
//                    break;
//                case "CARBOHYDRATES":
//                    holder.nutrition_IMG_nut2.setImageResource(R.drawable.ic_carbohydrates);
//                    holder.nutrition_IMG_nut2.setVisibility(View.VISIBLE);
//                    break;
//                case "DAIRY":
//                    holder.nutrition_IMG_nut3.setImageResource(R.drawable.ic_dairy);
//                    holder.nutrition_IMG_nut3.setVisibility(View.VISIBLE);
//                    break;
//                case "NO_EGG":
//                    holder.nutrition_IMG_nut4.setImageResource(R.drawable.ic_no_eggs);
//                    holder.nutrition_IMG_nut4.setVisibility(View.VISIBLE);
//                    break;
//                case "NO_PEANUT":
//                    holder.nutrition_IMG_nut5.setImageResource(R.drawable.ic_no_peanut);
//                    holder.nutrition_IMG_nut5.setVisibility(View.VISIBLE);
//                    break;
//            }
        }

//                Check if don't work otherwise
//                int resourceId = activity.getResources().getIdentifier(recipe.getImage(), "drawable", activity.getPackageName());
//                holder.Recipe_IMG_image.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    // Get the recipe in the specific position
    public Recipe getRecipe(int position) {
        return recipes.get(position);
    }

    // External class - HOLDER
    class RecipeHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView recipe_item_IMG_img;
        private MaterialTextView recipe_item_LBL_title;
        private MaterialTextView recipe_item_LBL_sub_title;
        private AppCompatImageView nutrition_IMG_nut1;
        private AppCompatImageView nutrition_IMG_nut2;
        private AppCompatImageView nutrition_IMG_nut3;
        private AppCompatImageView nutrition_IMG_nut4;
        private AppCompatImageView nutrition_IMG_nut5;


        public RecipeHolder(View itemView) {
            super(itemView);

            recipe_item_IMG_img = itemView.findViewById(R.id.recipe_item_IMG_img);
            recipe_item_LBL_title = itemView.findViewById(R.id.recipe_item_LBL_title);
            recipe_item_LBL_sub_title = itemView.findViewById(R.id.recipe_item_LBL_sub_title);
            nutrition_IMG_nut1 = itemView.findViewById(R.id.nutrition_IMG_nut1);
            nutrition_IMG_nut2 = itemView.findViewById(R.id.nutrition_IMG_nut2);
            nutrition_IMG_nut3 = itemView.findViewById(R.id.nutrition_IMG_nut3);
            nutrition_IMG_nut4 = itemView.findViewById(R.id.nutrition_IMG_nut4);
            nutrition_IMG_nut5 = itemView.findViewById(R.id.nutrition_IMG_nut5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.recipeClicked(getRecipe(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}