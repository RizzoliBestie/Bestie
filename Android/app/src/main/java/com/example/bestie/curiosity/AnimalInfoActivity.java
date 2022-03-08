package com.example.bestie.curiosity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bestie.R;
import com.example.bestie.animal.AnimalDownloadImage;
import com.mikhaellopez.circularimageview.CircularImageView;

public class AnimalInfoActivity extends AppCompatActivity {

    RelativeLayout animalInfoRelativeLayout = null;
    TextView nameTextView = null;
    ImageView animalInfoImageView = null;
    CircularImageView circular_animalInfoImageView = null;
    TextView descriptionTextView = null;

    TextView speciesHeaderTextView = null;
    TextView speciesTextView = null;

    TextView raceHeaderTextView = null;
    TextView raceTextView = null;

    TextView sizeHeaderTextView = null;
    TextView sizeTextView = null;

    TextView furTypeHeaderTextView = null;
    TextView furTypeTextView = null;

    String image_url;
    String section_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);

        animalInfoRelativeLayout = findViewById(R.id.animalInfoRelativeLayout);
        nameTextView = findViewById(R.id.nameTextView);
        animalInfoImageView = findViewById(R.id.animalInfoImageView);
        circular_animalInfoImageView = findViewById(R.id.animalInfoImageView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        speciesHeaderTextView = findViewById(R.id.speciesHeaderTextView);
        speciesTextView = findViewById(R.id.speciesTextView);

        raceHeaderTextView = findViewById(R.id.raceHeaderTextView);
        raceTextView = findViewById(R.id.raceTextView);

        sizeHeaderTextView = findViewById(R.id.sizeHeaderTextView);
        sizeTextView = findViewById(R.id.sizeTextView);

        furTypeHeaderTextView = findViewById(R.id.furTypeHeaderTextView);
        furTypeTextView = findViewById(R.id.furTypeTextView);

        section_name = getIntent().getExtras().getString("section");
        switch (section_name) {
            case "WILD":
                animalInfoRelativeLayout.setBackgroundResource(R.color.settore_wild_lista);
                nameTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_principale));
                descriptionTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_principale));
                speciesHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_secondario));
                speciesTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_principale));
                raceHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_secondario));
                raceTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_principale));
                sizeHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_secondario));
                sizeTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_principale));
                furTypeHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_secondario));
                furTypeTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_testo_principale));
                circular_animalInfoImageView.setBorderColor(ContextCompat.getColor(getBaseContext(), R.color.settore_wild_jolly));
                break;
            case "FARM":
                animalInfoRelativeLayout.setBackgroundResource(R.color.settore_farm_lista);
                nameTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_principale));
                descriptionTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_principale));
                speciesHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_secondario));
                speciesTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_principale));
                raceHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_secondario));
                raceTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_principale));
                sizeHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_secondario));
                sizeTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_principale));
                furTypeHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_secondario));
                furTypeTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_testo_principale));
                circular_animalInfoImageView.setBorderColor(ContextCompat.getColor(getBaseContext(), R.color.settore_farm_jolly));
                break;
            case "PETS":
                animalInfoRelativeLayout.setBackgroundResource(R.color.settore_pets_lista);
                nameTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_principale));
                descriptionTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_principale));
                speciesHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_secondario));
                speciesTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_principale));
                raceHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_secondario));
                raceTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_principale));
                sizeHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_secondario));
                sizeTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_principale));
                furTypeHeaderTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_secondario));
                furTypeTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_testo_principale));
                circular_animalInfoImageView.setBorderColor(ContextCompat.getColor(getBaseContext(), R.color.settore_pets_jolly));
                break;
        }

        image_url = getIntent().getExtras().getString("image_url");
        if(image_url != null) {
            (new AnimalDownloadImage(image_url, AnimalInfoActivity.this.animalInfoImageView)).execute();
        } else {
            animalInfoImageView.setImageResource(R.drawable.volpe); //Fa solo l'immagine della volpe
        }

        nameTextView.setText(getIntent().getExtras().getString("name"));
        descriptionTextView.setText(getIntent().getExtras().getString("information"));
        if(descriptionTextView.getText().equals("")){
            descriptionTextView.setText("Nessuna descrizione disponibile");
        }
        speciesTextView.setText(getIntent().getExtras().getString("specie"));
        if(speciesTextView.getText().equals("")){
            speciesTextView.setText("NA");
        }
        raceTextView.setText(getIntent().getExtras().getString("race"));
        if(raceTextView.getText().equals("")){
            raceTextView.setText("NA");
        }
        sizeTextView.setText(getIntent().getExtras().getString("size"));
        if(sizeTextView.getText().equals("")){
            sizeTextView.setText("NA");
        }
        furTypeTextView.setText(getIntent().getExtras().getString("fur_type"));
        if(furTypeTextView.getText().equals("")){
            furTypeTextView.setText("NA");
        }


    }
}