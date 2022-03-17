package com.example.bestie.pet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bestie.R;
import com.example.bestie.home.CardAdapter;
import com.example.bestie.home.HomeFragment;
import com.example.bestie.home.PetCard;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NewPetActivity extends AppCompatActivity {

    int SELECT_IMAGE_CODE = 1;
    CircularImageView addImage = null;
    Spinner specieACTV;
    AutoCompleteTextView racesACTV;
    SeekBar pesoBar;
    TextView pesoTV;
    DatePicker datePicker;
    Toolbar toolbar;
    ImageView addPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        specieACTV = findViewById(R.id.specieET);
        racesACTV = findViewById(R.id.razzaET);
        addImage = findViewById(R.id.newPetImage);
        pesoBar = findViewById(R.id.weight);
        pesoTV = findViewById(R.id.weightKgTV);
        datePicker = findViewById(R.id.birthdate);
        toolbar = findViewById(R.id.new_pet_toolbar);
        addPet = findViewById(R.id.confirm_button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //SETTO DATA MASSIMA PER DATEPICKER
        datePicker.setMaxDate(new Date().getTime());

        //FUNZIONAMENTO SEEKBAR PESO
        pesoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int kg = pesoBar.getProgress();
                CharSequence kgText = Integer.toString(kg);
                pesoTV.setText(kgText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //CARICO SPINNER PER LA SPECIE CON VALORE DI DEFAULT
        ArrayAdapter<CharSequence> specieAdapter1 = ArrayAdapter.createFromResource(this, R.array.species, android.R.layout.simple_dropdown_item_1line);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.species_spinner_nothing_selected, this);
        specieACTV.setAdapter(specieAdapter2);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewPetActivity.this, "Aggiunto", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            Uri uri = data.getData();
            addImage.setImageURI(uri);
        }
    }

    void pickImage(){
        Intent imageIntent = new Intent();
        imageIntent.setType("image/*");
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imageIntent, "title"), SELECT_IMAGE_CODE);
    }

}