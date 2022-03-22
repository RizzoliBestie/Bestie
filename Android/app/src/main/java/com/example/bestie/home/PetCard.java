package com.example.bestie.home;

import android.net.Uri;
import android.widget.EditText;

import com.example.bestie.R;
import com.example.bestie.pet.Pet;

import java.util.Map;

public class PetCard {
    String title;
    int imgId;
    Uri uri;
    long id_pet;
    Pet pet;

    //C'è UN SACCO DI CODICE INUTILE MA è TARDI PER MODIFICARE
    public PetCard(Pet pet, int imgId){
        this.title= pet.getName();
        this.imgId= imgId;
        this.id_pet= pet.getId_pet();
        this.pet=pet;
    }

    public PetCard(String title, Uri uri){
        this.title=title;
        this.uri=uri;
    }
}
