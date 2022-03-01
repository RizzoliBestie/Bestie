package com.example.bestie.pet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bestie.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class NewPetActivity extends Activity {

    String[] peso = new String[201];

    public void caricaPeso(){
        for (int i=0; i<200; i++){
            peso[i]= Integer.toString(i);
        }
        peso[200]= "200+";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);
        caricaPeso();

        Spinner specieACTV = (Spinner) findViewById(R.id.specieET);
        Spinner spinnerPeso = (Spinner) findViewById(R.id.weight);
        AutoCompleteTextView racesACTV = (AutoCompleteTextView)findViewById(R.id.razzaET);
        CircularImageView addImage = (CircularImageView) findViewById(R.id.newPetImage);

        //CARICO SPINNER PER IL PESO
        ArrayAdapter pesoAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, peso);
        pesoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeso.setAdapter(pesoAdapter);

        //CARICO SPINNER PER LA SPECIE CON VALORE DI DEFAULT
        ArrayAdapter<CharSequence> specieAdapter1 = ArrayAdapter.createFromResource(this, R.array.species, android.R.layout.simple_dropdown_item_1line);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.species_spinner_nothing_selected, this);
        specieACTV.setAdapter(specieAdapter2);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewPetActivity.this, "Aggiungi immagine", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}