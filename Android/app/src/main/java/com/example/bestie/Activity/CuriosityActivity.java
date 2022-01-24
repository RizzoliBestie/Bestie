package com.example.bestie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bestie.R;

public class CuriosityActivity extends AppCompatActivity {

    ListView animalWikiListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curiosity);

        //Trova la listView
        animalWikiListView = findViewById(R.id.animalWikiListView);

        //Setup della listView
        String [] AWString = new String[] {"Cane", "Gatto", "Criceto", "Pappagallo"};
        ArrayAdapter AWAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, AWString);
        animalWikiListView.setAdapter(AWAdapter);

    }
}