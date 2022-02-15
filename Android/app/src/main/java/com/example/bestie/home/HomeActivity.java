package com.example.bestie.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestie.R;
import com.example.bestie.settings.SettingsActivity;

import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView page_select = findViewById(R.id.page_select);
        Button menuButton = findViewById(R.id.menu_button);
        Button addPetButton = findViewById(R.id.addPetButton);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View drawer = inflater.inflate(R.layout.drawer_menu, null);
        TextView archivio = drawer.findViewById(R.id.archivio);
        TextView settings = drawer.findViewById(R.id.settings);
        TextView logout = drawer.findViewById(R.id.logout);

        String[] vociMenu = new String[]{"Home", "Map", "Curiosità"};
        ListAdapter menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, vociMenu);
        page_select.setAdapter(menuAdapter);

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Aggiunto", Toast.LENGTH_SHORT).show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(openSettings);
            }
        });
    }
}