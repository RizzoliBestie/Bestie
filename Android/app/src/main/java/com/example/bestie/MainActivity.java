package com.example.bestie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView page_select = findViewById(R.id.page_select);
        String[] vociMenu = new String[]{"Home", "Map", "Curiosità"};
        ListAdapter menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, vociMenu);
        page_select.setAdapter(menuAdapter);
        //ciao


    }
}