package com.example.bestie.pet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bestie.MainActivity;
import com.example.bestie.R;
import com.example.bestie.animal.Race;
import com.example.bestie.general.Profile;
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
    Spinner specieSpinner;
    Spinner races;
    SeekBar pesoBar;
    TextView pesoTV;
    DatePicker datePicker;
    Toolbar toolbar;
    ImageView addPet;
    EditText nameET;
    Profile owner;
    RadioGroup gender;
    RadioButton selectedSex;
    RadioGroup sterilized;
    RadioButton sterilizzato;
    RadioGroup fur_type;
    RadioButton selectedFur;
    long id_pet=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        //RICHIAMO IL PPROFILO UTENTE CHE SERVIRà NEL COSTRUTTORE DEL PET
        Bundle bundle = getIntent().getExtras();
        owner = bundle.getParcelable("owner");

        nameET=findViewById(R.id.nameET);
        specieSpinner = findViewById(R.id.specieET);
        races = findViewById(R.id.razzaET);
        addImage = findViewById(R.id.newPetImage);
        pesoBar = findViewById(R.id.weight);
        pesoTV = findViewById(R.id.weightKgTV);
        datePicker = findViewById(R.id.birthdate);
        toolbar = findViewById(R.id.new_pet_toolbar);
        addPet = findViewById(R.id.confirm_button);
        gender = findViewById(R.id.sex);
        selectedSex = findViewById(gender.getCheckedRadioButtonId());
        sterilized=findViewById(R.id.sterilizzazione_pet);
        sterilizzato=findViewById(sterilized.getCheckedRadioButtonId());
        fur_type=findViewById(R.id.pelo_group);
        selectedFur=findViewById(fur_type.getCheckedRadioButtonId());

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
        specieSpinner.setAdapter(specieAdapter2);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_pet++;
                createNewPet();
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

    boolean getBooleanSexFromText(String sex){
        if (sex.equals("M"))
            return true;
        else return false;
    }

    boolean getBooleanSterilizedFromText(String sterilized){
        if (sterilized.equals("Sì"))
            return true;
        else return false;
    }

    Pet createNewPet(){
        String name=nameET.getText().toString();
        String raceText=races.getSelectedItem().toString();
        Race race = new Race(raceText);
        String specie = specieSpinner.getSelectedItem().toString();
        double weight = pesoBar.getProgress();
        boolean sex = getBooleanSexFromText(selectedSex.getText().toString());
        boolean sterilized = getBooleanSterilizedFromText(sterilizzato.getText().toString());
        String furType = selectedFur.getText().toString();

        Pet pet = new Pet(id_pet,race,specie,owner,name,weight,sex,sterilized,furType);
        pet.addPetToOwner(pet);
        return pet;
    }
}