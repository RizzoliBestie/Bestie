package com.example.bestie.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bestie.R;

import java.util.ArrayList;

public class PetActivity extends AppCompatActivity {

    ArrayList<InfoPetListItem> infoArrayList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        ListView infoPetLV = (ListView) findViewById(R.id.infoPetLV);
        Toolbar toolbar = findViewById(R.id.pet_toolbar);
        setSupportActionBar(toolbar);

        loadArray(infoArrayList);
        PetListAdapter adapter = new PetListAdapter(this, R.layout.info_pet_list_item, R.id.info_pet_title, infoArrayList);
        infoPetLV.setAdapter(adapter);
    }

        ArrayList<InfoPetListItem> loadArray(ArrayList<InfoPetListItem> arrayList){
        InfoPetListItem specie = new InfoPetListItem("Specie", null);
        InfoPetListItem razza = new InfoPetListItem("Razza", null);
        InfoPetListItem peso = new InfoPetListItem("Peso", null);
        InfoPetListItem pelo = new InfoPetListItem("Pelo", null);
        InfoPetListItem sterile = new InfoPetListItem("Sterilizzazione", null);

        arrayList.add(specie);
        arrayList.add(razza);
        arrayList.add(peso);
        arrayList.add(pelo);
        arrayList.add(sterile);
        
        return arrayList;
    }
}