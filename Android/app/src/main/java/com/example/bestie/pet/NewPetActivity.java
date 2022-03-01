package com.example.bestie.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.bestie.R;

public class NewPetActivity extends Activity {

    String peso[] = new String[202];

    public void caricaPeso(){
        peso[0]="Seleziona peso";
        for (int i=1; i<=200; i++){
            peso[i]= Integer.toString(i);
        }
        peso[201]= "200+";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        Spinner spinnerPeso = (Spinner) findViewById(R.id.weight);
        caricaPeso();

        ArrayAdapter pesoAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, peso);
        pesoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeso.setAdapter(pesoAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}