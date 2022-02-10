package com.example.bestie.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.bestie.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView page_select = findViewById(R.id.page_select);
        Button menuButton = findViewById(R.id.menu_button);
        Button addPetButton = findViewById(R.id.addPetButton);

        String[] vociMenu = new String[]{"Home", "Map", "Curiosità"};
        ListAdapter menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, vociMenu);
        page_select.setAdapter(menuAdapter);

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Aggiunto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}