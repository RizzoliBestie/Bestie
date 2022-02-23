package com.example.bestie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.bestie.curiosity.CuriosityFragment;
import com.example.bestie.home.HomeFragment;
import com.example.bestie.map.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Button menuButton = findViewById(R.id.menu_button);
            Button addPetButton = findViewById(R.id.addPetButton);
            BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
            getSupportActionBar().hide();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected =null;
                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selected=new HomeFragment();
                            break;
                        case R.id.navigation_map:
                            selected=new MapFragment();
                            break;
                        case R.id.navigation_curiosity:
                            selected=new CuriosityFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
                    return true;
                }
            });

        }
}