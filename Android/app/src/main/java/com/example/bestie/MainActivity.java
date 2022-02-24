package com.example.bestie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.bestie.curiosity.CuriosityFragment;
import com.example.bestie.home.HomeFragment;
import com.example.bestie.map.MapFragment;
import com.example.bestie.pet.NewPetActivity;
import com.example.bestie.settings.SettingsActivity;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menuButton = findViewById(R.id.menu_button);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView=findViewById(R.id.nav_view);
        //DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selected = new HomeFragment();
                        break;
                    case R.id.navigation_map:
                        selected = new MapFragment();
                        break;
                    case R.id.navigation_curiosity:
                        selected = new CuriosityFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
                return true;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent selected = null;
                switch (item.getItemId()){
                    case R.id.add_pet:
                        selected=new Intent(MainActivity.this, NewPetActivity.class);
                        break;
                   /* case R.id.archivio:
                        selected=new Intent(MainActivity.this, StorageActivity.class);
                        break;*/
                    case R.id.settings:
                        selected=new Intent(MainActivity.this, SettingsActivity.class);
                        break;
                }
                startActivity(selected);
                return true;
            }
        });

    }

   /* @Override
    public void onBackPressed() {
        if ()
        super.onBackPressed();
    }*/
}