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

    public PetCard(String title, int imgId){
        this.title=title;
        this.imgId= imgId;
    }

    public PetCard(String title, Uri uri){
        this.title=title;
        this.uri=uri;
    }
}
