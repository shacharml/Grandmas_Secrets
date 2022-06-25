package com.example.grandmassecrets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.grandmassecrets.Fragments.ProfileFragment;
import com.example.grandmassecrets.Fragments.RecipesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ExampleActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exampel);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView = findViewById(R.id.example_nav_bottom);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new ProfileFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.menu_ITM_profile);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.menu_ITM_profile:
                        fragment = new RecipesFragment();
                        break;
                    case R.id.menu_ITM_profile1:
                        fragment = new ProfileFragment();
                        break;

                    case R.id.menu_ITM_group:
                        fragment = new RecipesFragment();
                        break;
                    case R.id.menu_ITM_group1:
                        fragment = new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });
    }
}