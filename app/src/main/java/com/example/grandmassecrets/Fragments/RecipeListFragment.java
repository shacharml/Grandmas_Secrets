package com.example.grandmassecrets.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.bumptech.glide.request.RequestOptions;
import com.example.grandmassecrets.Activities.CreateGroupActivity;
import com.example.grandmassecrets.Activities.CreateRecipeActivity;
import com.example.grandmassecrets.Activities.RecipeActivity;
import com.example.grandmassecrets.Activities.ShareContactsActivity;
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
        main_TOB_up.setTitle("Group Recipes");
        main_TOB_up.getMenu().getItem(0).setVisible(true).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getContext(), ShareContactsActivity.class);
                startActivity(intent);
                return false;
            }
        });

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

        RecipeAdapter adapter = new RecipeAdapter(options,this.getContext());
        listRecycler.setAdapter(adapter);
        adapter.startListening();
    }
}