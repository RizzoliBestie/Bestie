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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestie.R;
import com.example.bestie.animal.Animal;
import com.example.bestie.animal.AnimalArrayAdapter;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CuriosityActivity extends AppCompatActivity {

    ListView animalWikiListView = null;
    ImageView sectionMenuImageView = null;
    TextView sectionTextView = null;
    EditText searchEditText = null;
    Spinner sectionMenuSpinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curiosity);

        //Trova i componenti
        animalWikiListView = findViewById(R.id.animalWikiListView);
        sectionMenuImageView = findViewById(R.id.sectionMenuImageView);
        sectionTextView = findViewById(R.id.sectionTextView);
        searchEditText = findViewById(R.id.searchEditText);
        sectionMenuSpinner = findViewById(R.id.sectionMenuSpinner);

        //Setup della TextView che indica la sezione
        String [] SectionString = new String[] {"PETS", "FARM", "WILD"};

        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.curiosity_sections, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionMenuSpinner.setAdapter(spinnerAdapter);



        //Input degli animali qui
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Volpe", "Fennec", "Vulpes Zerda", "WILD", "https://www.parmadaily.it/wp-content/uploads/2016/09/fennec.jpg"));
        animals.add(new Animal("Nittereute", "Cane Procione", "Nyctereutes procyonoides", "WILD", "https://upload.wikimedia.org/wikipedia/commons/8/85/Der_Marderhund%2C_Tanuki_oder_Enok_%28Nyctereutes_procyonoides%29%2C_bitte_nicht_zu_verwechseln_mit_einem_Waschb%C3%A4r%2C_hier_im_Wisentgehege_in_Springe_%28Kleiner_Deister%29.jpg"));
        animals.add(new Animal("Volpe", "Volpe Rossa", "Vulpes Vulpes", "WILD","https://www.giornaletrentino.it/image/contentid/policy:1.2991162:1631209711/image%20(3).jpg?f=3x2&w=299&$p$f$w=c5a262c"));
        animals.add(new Animal("Urocioni", "Volpe Grigia", "Urocyon", "WILD","https://static.kodami.it/wp-content/uploads/sites/31/2021/05/iStock-1264712034-638x425.jpg"));
        animals.add(new Animal("Volpe", "Volpe Americana", "Vulpes Velox", "WILD","https://upload.wikimedia.org/wikipedia/commons/2/2a/Vulpes_velox.jpg"));
        animals.add(new Animal("Otocione", "Otycion", "Otycion megalotis", "WILD","https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Bandit_%2835877900754%29.jpg/220px-Bandit_%2835877900754%29.jpg"));
        animals.add(new Animal("Gallina", "Gallus", "Gallus gallus domesticus", "FARM","https://www.tuttosullegalline.it/newsite/wp-content/uploads/2019/01/gallina-Brucie-4.jpg"));
        animals.add(new Animal("Toro", "Mucca", "Bos Taurus", "FARM", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo3nFYjIbXjGoZQ5umhVFLdBLEEAHDP85yAw&usqp=CAU"));
        animals.add(new Animal("Pecora", "Ovis", "Ovis aries", "FARM", "https://www.cibo360.it/images/alimentazione/cibi/pecora.jpg"));
        animals.add(new Animal("Cane", "Canis lupus familiaris", "Siberian Husky", "PETS","https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Le%C3%AFko_au_bois_de_la_Cambre.jpg/330px-Le%C3%AFko_au_bois_de_la_Cambre.jpg"));
        animals.add(new Animal("Cane", "Canis lupus familiaris", "Pastore Tedesco", "PETS","http://www.difossombrone.it/images/anatomia/difettitesta.jpg"));
        animals.add(new Animal("Gatto", "Felis catus", "Persiano", "PETS","https://upload.wikimedia.org/wikipedia/it/thumb/3/3e/Prisca.jpg/390px-Prisca.jpg"));

        List<Animal> animalsWild = new ArrayList<Animal>();
        for(int wild=0; wild<animals.size(); wild++){
            if(animals.get(wild).section == "WILD"){
                animalsWild.add(animals.get(wild));
            }
        }

        List<Animal> animalsFarm = new ArrayList<Animal>();
        for(int farm=0; farm<animals.size(); farm++){
            if(animals.get(farm).section == "FARM"){
                animalsFarm.add(animals.get(farm));
            }
        }

        List<Animal> animalsPets = new ArrayList<Animal>();
        for(int pets=0; pets<animals.size(); pets++){
            if(animals.get(pets).section == "PETS"){
                animalsPets.add(animals.get(pets));
            }
        }
        //Crea l'arrayadapter, fa riferimento a diverse categorie di animali
        AnimalArrayAdapter animalAdapterWild = new AnimalArrayAdapter(this, R.layout.row_animal, animalsWild);
        AnimalArrayAdapter animalAdapterFarm = new AnimalArrayAdapter(this, R.layout.row_animal, animalsFarm);
        AnimalArrayAdapter animalAdapterPets = new AnimalArrayAdapter(this, R.layout.row_animal, animalsPets);
        AnimalArrayAdapter animalAdapter = new AnimalArrayAdapter(this, R.layout.row_animal, animals);


        sectionMenuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), spinnerAdapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
                sectionTextView.setText(sectionMenuSpinner.getSelectedItem().toString());

                //Imposta quale categoria di animali deve essere visualizzata
                String index = sectionMenuSpinner.getSelectedItem().toString();
                if(index.equals("WILD")){
                    animalWikiListView.setAdapter(animalAdapterWild);
                } else if(index.equals("FARM")) {
                    animalWikiListView.setAdapter(animalAdapterFarm);
                } else if(index.equals("PETS")) {
                    animalWikiListView.setAdapter(animalAdapterPets);
                } else {
                    animalWikiListView.setAdapter(animalAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //AnimalArrayAdapter animalSearchAdapter = new AnimalArrayAdapter(this, R.layout.row_animal, animalsSearchList);
        //animalWikiListView.setAdapter(animalSearchAdapter);

        //Abilita filtro per i contenuti della ListView
        animalWikiListView.setTextFilterEnabled(true);

        animalWikiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AnimalArrayAdapter animalRefAdapter = (AnimalArrayAdapter) animalWikiListView.getAdapter();
                Animal p = animalRefAdapter.getItem(pos);
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
                AnimalArrayAdapter animalRefAdapter = (AnimalArrayAdapter) animalWikiListView.getAdapter();
                //Al cambiamento dell'editText aggiorna la lista
                animalRefAdapter.getFilter().filter(charSequence.toString());
                //Ogni cambiamento aggiorna la lista attraverso questi metodi(in questo caso aggiorna soprattutto le immagini)
                animalRefAdapter.clear();
                //animalAdapter.notifyDataSetInvalidated();
                animalRefAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //Click della tendina (Hamburger Menu)
        /*sectionMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CuriosityActivity.this, "Tendina premuta", Toast.LENGTH_SHORT).show();
            }
        }); */



    }
}