package com.example.grandmassecrets.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grandmassecrets.Activities.CreateGroupActivity;
import com.example.grandmassecrets.Activities.CreateRecipeActivity;
import com.example.grandmassecrets.Activities.RecipeActivity;
import com.example.grandmassecrets.Adapters.RecipeAdapter;
import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Firebase.DataManager;
import com.example.grandmassecrets.Objects.NutritionFacts;
import com.example.grandmassecrets.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;


public class RecipeListFragment extends Fragment {

    private View view;
    private RecyclerView listRecycler;

    private DataManager dataManager = DataManager.getInstance();
    private DatabaseReference groupRecipesRef,recipeRef;
//    private RecipeAdapter adapter;

    private FloatingActionButton main_FAB_fab;
    private MaterialToolbar main_TOB_up;


    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recip_list, container, false);
        main_FAB_fab = getActivity().findViewById(R.id.main_FAB_fab);
        main_TOB_up = getActivity().findViewById(R.id.main_TOB_up);
        main_TOB_up.setTitle("All Recipes");
        /**
         * Add button clicked = Move to add new Group activity
         * this activity wont close
         */
        main_FAB_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Open Create new Group activity
                 */
                startActivity(new Intent(getActivity(), CreateRecipeActivity.class));
            }
        });

        listRecycler = view.findViewById(R.id.recipe_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        listRecycler.setLayoutManager(gridLayoutManager);

        groupRecipesRef = dataManager.groupsListReference().child(dataManager.getCurrentIdGroup()).child(Keys.KEY_GROUP_RECIPES_LIST);
        recipeRef = dataManager.recipessListReference();
        recipeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String rem = snapshot.getKey();
                groupRecipesRef.child(rem).removeValue();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(groupRecipesRef,String.class)
                .build();

        FirebaseRecyclerAdapter<String, RecipeHolder> adapter = new FirebaseRecyclerAdapter<String, RecipeHolder>(options){
            @Override
            protected void onBindViewHolder(@NonNull RecipeHolder holder, int position, @NonNull String model) {

                String recipeIDs = getRef(position).getKey();
                recipeRef.child(recipeIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()){
                            Log.d("ppp",137+"");

                            String name = snapshot.child(Keys.KEY_RECIPE_NAME).getValue(String.class);
                            String des = snapshot.child(Keys.KEY_RECIPE_DESCRIPTION).getValue(String.class);
                            String img = snapshot.child(Keys.KEY_RECIPE_IMG).getValue(String.class);
                            Log.d("ppp",name+des+img);
                            holder.recipe_item_LBL_title.setText(name);
                            holder.recipe_item_LBL_sub_title.setText(des);
                            Glide.with(getContext()).load(img).into(holder.recipe_item_IMG_img);

//                            GenericTypeIndicator<NutritionFacts> t = new GenericTypeIndicator<NutritionFacts>() {};
//                            ArrayList<NutritionFacts> nutFactsRecipe  = snapshot.child(Keys.KEY_RECIPE_FACTS_LIST).getValue()
//                                    = recipe.getNutritionFacts().values().stream().collect(Collectors.toCollection(ArrayList::new));
                            //get the Images of the nutrition facts (not from data Storage
                            HashMap<String, Objects> nutFactsRecipe = (HashMap<String, Objects>) snapshot.child(Keys.KEY_RECIPE_FACTS_LIST).getValue();
                            Collection<String> n = nutFactsRecipe.keySet();
                            ArrayList<String> names = new ArrayList<>(n);
                            for (String facts : names) { //for arrayList
                                switch (facts) {
                                    case "ORGANIC":
                                        holder.nutrition_IMG_nut1.setImageResource(R.drawable.ic_organic_food);
                                        holder.nutrition_IMG_nut1.setVisibility(View.VISIBLE);
                                        break;
                                    case "CARBOHYDRATES":
                                        holder.nutrition_IMG_nut2.setImageResource(R.drawable.ic_carbohydrates);
                                        holder.nutrition_IMG_nut2.setVisibility(View.VISIBLE);
                                        break;
                                    case "DAIRY":
                                        holder.nutrition_IMG_nut3.setImageResource(R.drawable.ic_dairy);
                                        holder.nutrition_IMG_nut3.setVisibility(View.VISIBLE);
                                        break;
                                    case "NO_EGG":
                                        holder.nutrition_IMG_nut4.setImageResource(R.drawable.ic_no_eggs);
                                        holder.nutrition_IMG_nut4.setVisibility(View.VISIBLE);
                                        break;
                                    case "NO_PEANUT":
                                        holder.nutrition_IMG_nut5.setImageResource(R.drawable.ic_no_peanut);
                                        holder.nutrition_IMG_nut5.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }



                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getContext(), RecipeActivity.class);
                                    dataManager.setCurrentIdRecipe(recipeIDs);
                                    startActivity(intent);

                                }
                            });

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_card,parent,false);
                RecipeHolder holder = new RecipeHolder(view);

                return holder;
            }
        };
        listRecycler.setAdapter(adapter);
        adapter.startListening();
    }

    // External class - HOLDER
    public class RecipeHolder extends RecyclerView.ViewHolder {

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

        }
    }


}