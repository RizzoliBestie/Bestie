package com.example.bestie.curiosity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bestie.R;

public class CuriosityActivity extends AppCompatActivity {

    ListView animalWikiListView = null;
    ImageView sectionMenuImageView = null;
    TextView sectionTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curiosity);

        //Trova i componenti
        animalWikiListView = findViewById(R.id.animalWikiListView);
        sectionMenuImageView = findViewById(R.id.sectionMenuImageView);
        sectionTextView = findViewById(R.id.sectionTextView);

        //Setup della TextView che indica la sezione
        String [] SectionString = new String[] {"PETS", "FARM", "WILD"};

        //Setup della listView
        String [] AWString = new String[] {"Cane", "Gatto", "Criceto", "Pappagallo"};
        ArrayAdapter AWAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, AWString);
        animalWikiListView.setAdapter(AWAdapter);

    }
}