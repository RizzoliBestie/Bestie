package com.example.bestie.curiosity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bestie.R;
import com.example.bestie.animal.AnimalDownloadImage;

public class AnimalInfoActivity extends AppCompatActivity {

    TextView nameTextView = null;
    ImageView animalInfoImageView = null;

    String image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);

        nameTextView = findViewById(R.id.nameTextView);
        animalInfoImageView = findViewById(R.id.animalInfoImageView);

        image_url = getIntent().getExtras().getString("image_url");

        if(image_url != null) {
            (new AnimalDownloadImage(image_url, AnimalInfoActivity.this.animalInfoImageView)).execute();
        } else {
            animalInfoImageView.setImageResource(R.drawable.volpe); //Fa solo l'immagine della volpe
        }

        nameTextView.setText(getIntent().getExtras().getString("name"));


    }
}