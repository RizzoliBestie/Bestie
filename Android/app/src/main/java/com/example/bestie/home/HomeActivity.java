package com.example.bestie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.bestie.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView page_select = findViewById(R.id.page_select);
        String[] vociMenu = new String[]{"Home", "Map", "Curiosità"};
        ListAdapter menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, vociMenu);
        page_select.setAdapter(menuAdapter);
    }
}