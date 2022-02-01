package com.example.bestie.curiosity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestie.R;
import com.example.bestie.animal.Animal;
import com.example.bestie.animal.AnimalArrayAdapter;

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

        Animal[] animals = new Animal[] {
                new Animal("Volpe", "Vulpes Vulpes", "Fennec"),
                new Animal("Volpe", "Vulpes Vulpes", "Volpe Rossa"),
                new Animal("Volpe", "Vulpes Vulpes", "Volpe Americana")
        };

        //Crea l'arrayadapter, fa riferimento al row animal con la lista
        AnimalArrayAdapter pap = new AnimalArrayAdapter(this, R.layout.row_animal, animals);
        animalWikiListView.setAdapter(pap);

        animalWikiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Animal p = pap.getItem(pos);
                Toast.makeText(CuriosityActivity.this, p.name + " " + p.race + " " + p.specie, Toast.LENGTH_SHORT).show();
                /*
                Intent intentSA = new Intent(MainActivity.this, SecondActivity.class);
                intentSA.putExtra("titolo", p.titolo);
                startActivity(intentSA); */
            }
        });
        //Setup della TextView che indica la sezione
        String [] SectionString = new String[] {"PETS", "FARM", "WILD"};

        //Setup della listView
        //String [] AWString = new String[] {"Cane", "Gatto", "Criceto", "Pappagallo"};
        //ArrayAdapter AWAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, AWString);
        //animalWikiListView.setAdapter(AWAdapter);

    }
}