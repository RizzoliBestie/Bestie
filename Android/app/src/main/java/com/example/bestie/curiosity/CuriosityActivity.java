package com.example.bestie.curiosity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestie.R;
import com.example.bestie.animal.Animal;
import com.example.bestie.animal.AnimalArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CuriosityActivity extends AppCompatActivity {

    ListView animalWikiListView = null;
    ImageView sectionMenuImageView = null;
    TextView sectionTextView = null;
    EditText searchEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curiosity);

        //Trova i componenti
        animalWikiListView = findViewById(R.id.animalWikiListView);
        sectionMenuImageView = findViewById(R.id.sectionMenuImageView);
        sectionTextView = findViewById(R.id.sectionTextView);
        searchEditText = findViewById(R.id.searchEditText);

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Volpe", "Fennec", "Vulpes Zerda", "https://www.parmadaily.it/wp-content/uploads/2016/09/fennec.jpg"));
        animals.add(new Animal("Nittereute", "Cane Procione", "Nyctereutes procyonoides", "https://upload.wikimedia.org/wikipedia/commons/8/85/Der_Marderhund%2C_Tanuki_oder_Enok_%28Nyctereutes_procyonoides%29%2C_bitte_nicht_zu_verwechseln_mit_einem_Waschb%C3%A4r%2C_hier_im_Wisentgehege_in_Springe_%28Kleiner_Deister%29.jpg"));
        animals.add(new Animal("Volpe", "Volpe Rossa", "Vulpes Vulpes", "https://www.giornaletrentino.it/image/contentid/policy:1.2991162:1631209711/image%20(3).jpg?f=3x2&w=299&$p$f$w=c5a262c"));
        animals.add(new Animal("Urocioni", "Volpe Grigia", "Urocyon", "https://static.kodami.it/wp-content/uploads/sites/31/2021/05/iStock-1264712034-638x425.jpg"));
        animals.add(new Animal("Volpe", "Volpe Americana", "Vulpes Velox", "https://upload.wikimedia.org/wikipedia/commons/2/2a/Vulpes_velox.jpg"));
        animals.add(new Animal("Otocione", "Otycion", "Otycion megalotis", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Bandit_%2835877900754%29.jpg/220px-Bandit_%2835877900754%29.jpg"));

        //Copia utilizzata per la ricerca
        ArrayList<Animal> animalsSearchList = (ArrayList<Animal>) animals;

        //Crea l'arrayadapter, fa riferimento al row animal con la lista
        AnimalArrayAdapter animalAdapter = new AnimalArrayAdapter(this, R.layout.row_animal, animals);
        animalWikiListView.setAdapter(animalAdapter);
        AnimalArrayAdapter animalSearchAdapter = new AnimalArrayAdapter(this, R.layout.row_animal, animalsSearchList);
        //animalWikiListView.setAdapter(animalSearchAdapter);

        //Abilita filtro per i contenuti della ListView
        animalWikiListView.setTextFilterEnabled(true);

        animalWikiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Animal p = animalAdapter.getItem(pos);
                Toast.makeText(CuriosityActivity.this, p.name + ", " + p.race + ", " + p.specie, Toast.LENGTH_SHORT).show();
                /*
                Intent intentSA = new Intent(MainActivity.this, SecondActivity.class);
                intentSA.putExtra("titolo", p.titolo);
                startActivity(intentSA); */
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Al cambiamento dell'editText aggiorna la lista
                animalAdapter.getFilter().filter(charSequence.toString());


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Setup della TextView che indica la sezione
        String [] SectionString = new String[] {"PETS", "FARM", "WILD"};

        //Setup della listView
        //String [] AWString = new String[] {"Cane", "Gatto", "Criceto", "Pappagallo"};
        //ArrayAdapter AWAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, AWString);
        //animalWikiListView.setAdapter(AWAdapter);

        //Click della tendina (Hamburger Menu)
        sectionMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CuriosityActivity.this, "Tendina premuta", Toast.LENGTH_SHORT).show();
            }
        });



    }
}