package com.example.bestie.pet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.number.NumberRangeFormatter;
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

import com.example.bestie.API.API_Connection_Bestie;
import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.MainActivity;
import com.example.bestie.R;
import com.example.bestie.general.Race;
import com.example.bestie.general.Profile;
import com.example.bestie.general.Specie;
import com.example.bestie.home.CardAdapter;
import com.example.bestie.home.HomeFragment;
import com.example.bestie.home.PetCard;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    int id_user;
    int id_pet=0;
    String raceText=null;
    List<Specie> specieList = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        SharedPreferences preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        id_user = preferences.getInt("id_user",0);

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

        Retrofit retrofit = ((API_Connection_Bestie) getApplication()).getRetrofit();
        API_Methods_Interface api = retrofit.create(API_Methods_Interface.class);

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


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        Call<List<Specie>> getSpecies = api.getAllSpecies();
        getSpecies.enqueue(new Callback<List<Specie>>() {
            @Override
            public void onResponse(Call<List<Specie>> call, Response<List<Specie>> response) {
                specieList =response.body();
                Toast.makeText(NewPetActivity.this, "Specie successo", Toast.LENGTH_SHORT).show();
                caricaSpecie(specieList);
            }

            @Override
            public void onFailure(Call<List<Specie>> call, Throwable t) {
                Toast.makeText(NewPetActivity.this, "Specie fallito", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

/*
        Call<Race> getRaceByName = api.getRaceByName(raceText);
        getRaceByName.enqueue(new Callback<Race>() {
            @Override
            public void onResponse(Call<Race> call, Response<Race> response) {
                Race race =response.body();
                addPetListener(race);
                Toast.makeText(NewPetActivity.this, "Race response ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Race> call, Throwable t) {
                Toast.makeText(NewPetActivity.this, "Race response fail", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });*/

    }

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


    protected void addPetListener(Race race){
        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                raceText=races.getSelectedItem().toString();
                id_pet++;
                Pet pet = createNewPet(race);
                String prova = pet.getName();
                Toast.makeText(NewPetActivity.this, "Aggiunto", Toast.LENGTH_SHORT).show();
                Toast.makeText(NewPetActivity.this, prova, Toast.LENGTH_SHORT).show();
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

    protected Pet createNewPet(Race race){
        String name=nameET.getText().toString();
        int id_race = race.getId_race();
        String specie = specieSpinner.getSelectedItem().toString();
        double weight = pesoBar.getProgress();
        boolean sex = getBooleanSexFromText(selectedSex.getText().toString());
        boolean sterilized = getBooleanSterilizedFromText(sterilizzato.getText().toString());
        String furType = selectedFur.getText().toString();
        Date birthdate = getDateFromDatePicker(datePicker);

        Pet pet = new Pet(id_pet, id_user, id_race, name,weight, sex, birthdate, null, null, sterilized, furType);
        return pet;
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public void caricaSpecie(List<Specie> specieList){
        //CARICO SPINNER PER LA SPECIE CON VALORE DI DEFAULT
        String[] nomiSpecie = new String[specieList.size()];
        for(int i=0; i<specieList.size(); i++){
            nomiSpecie[i]=specieList.get(i).getCommon_name();
        }
        ArrayAdapter<CharSequence> specieAdapter1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_dropdown_item_1line, nomiSpecie);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.species_spinner_nothing_selected, this);
        specieSpinner.setAdapter(specieAdapter2);
    }
}