package com.example.bestie.pet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.bestie.R;

public class NewPetActivity extends Activity {

    String[] peso = new String[201];

    public void caricaPeso(){
        for (int i=0; i<200; i++){
            peso[i]= Integer.toString(i);
        }
        peso[200]= "200+";
    }

    //String[] specie= new String[]{"Seleziona la specie","Cane", "Gatto", "Roditore", "Pesce", "Rettile", "Volatile"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);
        caricaPeso();

        Spinner specieACTV = (Spinner) findViewById(R.id.specieET);
        Spinner spinnerPeso = (Spinner) findViewById(R.id.weight);


        ArrayAdapter pesoAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, peso);
        pesoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeso.setAdapter(pesoAdapter);


        ArrayAdapter<CharSequence> specieAdapter = ArrayAdapter.createFromResource(this, R.array.species, android.R.layout.simple_dropdown_item_1line);
        specieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter adapterOK = new NothingSelectedSpinnerAdapter(specieAdapter, R.layout.species_spinner_nothing_selected, this);
        specieACTV.setAdapter(adapterOK);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}