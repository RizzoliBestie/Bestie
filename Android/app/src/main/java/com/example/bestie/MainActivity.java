package com.example.bestie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bestie.curiosity.CuriosityFragment;
import com.example.bestie.home.HomeFragment;
import com.example.bestie.home.PetCard;
import com.example.bestie.login.LogInActivity;
import com.example.bestie.map.MapFragment;
import com.example.bestie.pet.NewPetActivity;
import com.example.bestie.settings.SettingsActivity;
import com.example.bestie.storage.StorageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //IMPORTO ELEMENTI DALL'XML
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        CircularImageView homePetImage = findViewById(R.id.homeImage);

        //SETTO TOOLBAR E FRAGMENT MANAGER
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        //SETTO IL FUNZIONAMENTO DELLA BOTTOM NAVIGATION
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

        //SETTO IL FUNZIONAMENTO DEL DRAWER MENù
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent selected = null;
                switch (item.getItemId()) {
                    case R.id.add_pet:
                        selected = new Intent(MainActivity.this, NewPetActivity.class);
                        break;
                    case R.id.archivio:
                        selected = new Intent(MainActivity.this, StorageActivity.class);
                        break;
                    case R.id.settings:
                        selected = new Intent(MainActivity.this, SettingsActivity.class);
                        break;
                    case R.id.logout:
                        selected = new Intent(MainActivity.this, LogInActivity.class);
                }
                startActivity(selected);
                return true;
            }
        });

        //SETTO IL BUTTON HAMBURGER MENù NELLA TOOLBAR
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    /* @Override
    public void onBackPressed() {
        if ()
        super.onBackPressed();
    }*/
    }
}



/* Inserimento di alcune immagini utili per l'app in un oggetto ImageBox */
        /*Image[] images = new Image[10];
        images[0] = new Image("https://cdn-icons-png.flaticon.com/512/337/337946.png","pdf");
        images[1] = new Image("https://icon-library.com/images/doc-icon-png/doc-icon-png-6.jpg","doc");
        images[2] = new Image("https://www.clipartmax.com/png/full/241-2414609_filename-extension-icon-xls-microsoft-excel-binary-excel-file-type.png","xls");
        images[3] = new Image("https://cdn-icons-png.flaticon.com/512/337/337956.png","txt");
        images[4] = new Image("https://icons.iconarchive.com/icons/graphicloads/filetype/256/png-icon.png","png");
        images[5] = new Image("https://icons.iconarchive.com/icons/pelfusion/flat-file-type/256/jpg-icon.png","jpg");
        images[6] = new Image("https://cdn-icons-png.flaticon.com/512/337/337936.png","gif");
        images[7] = new Image("https://cdn.iconscout.com/icon/free/png-256/image-file-2014989-1700537.png","img");
        images[8] = new Image("https://icons.iconarchive.com/icons/pelfusion/flat-file-type/256/zip-icon.png","zip");
        images[9] = new Image("https://cdn.icon-icons.com/icons2/2753/PNG/512/ext_file_generic_filetype_icon_176256.png","generic");
        ImageBox imageBox = new ImageBox(this,images);*/
/* Inserimento di alcune immagini utili per l'app in un oggetto ImageBox */
