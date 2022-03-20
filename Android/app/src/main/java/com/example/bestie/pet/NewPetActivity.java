package com.example.bestie.pet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.bestie.R;
import com.example.bestie.general.Race;
import com.example.bestie.general.Specie;
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
    Spinner raceSpinner;
    SeekBar pesoBar;
    TextView pesoTV;
    DatePicker datePicker;
    Toolbar toolbar;
    ImageView addPet;
    EditText nameET;
    RadioGroup sexRadioGroup;
    RadioButton selectedSexButton;
    RadioGroup sterilizedRadioGroup;
    RadioButton sterilizedSelected;
    RadioGroup furTypeRadioGroup;
    RadioButton selectedFurButton;
    int id_user;
    int id_pet = 1;
    List<Specie> specieList = new LinkedList<>();
    List<Race> raceList=new LinkedList<>();
    Race selectedRace;
    double weight;
    String name;
    String pelo;
    boolean sterilized;
    boolean sex;
    Date birthdate;
    List<Pet> ownersPets = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        Retrofit retrofit = ((API_Connection_Bestie) getApplication()).getRetrofit();
        API_Methods_Interface api = retrofit.create(API_Methods_Interface.class);

        SharedPreferences preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        id_user = preferences.getInt("id_user", 0);

        nameET = findViewById(R.id.nameET);
        specieSpinner = findViewById(R.id.specieET);
        raceSpinner = findViewById(R.id.razzaET);
        addImage = findViewById(R.id.newPetImage);
        pesoBar = findViewById(R.id.weight);
        pesoTV = findViewById(R.id.weightKgTV);
        datePicker = findViewById(R.id.birthdate);
        toolbar = findViewById(R.id.new_pet_toolbar);
        addPet = findViewById(R.id.confirm_button);
        sexRadioGroup = findViewById(R.id.sex);
        sterilizedRadioGroup = findViewById(R.id.sterilizzazione_pet);
        furTypeRadioGroup = findViewById(R.id.pelo_group);

        //SETTO DATA MASSIMA PER DATEPICKER
        datePicker.setMaxDate(new Date().getTime());

        //SETTO TOOLBAR
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //AGGIUNGI IMMAGINE
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        //ACQUISISCE NOME
        nameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                name=nameET.getText().toString();
            }
        });

        //CARICA SPECIE NELLO SPINNER
        Call<List<Specie>> getAllSpecies = api.getAllSpecies();
        getAllSpecies.enqueue(new Callback<List<Specie>>() {
            @Override
            public void onResponse(Call<List<Specie>> call, Response<List<Specie>> response) {
                specieList = response.body();
                Toast.makeText(NewPetActivity.this, "Specie successo", Toast.LENGTH_SHORT).show();
                caricaSpecie(specieList);
            }

            @Override
            public void onFailure(Call<List<Specie>> call, Throwable t) {
                Toast.makeText(NewPetActivity.this, "Specie fallito", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        //IN BASE ALLA SPEIE SELEZIONATA CARICA LO SPINNER DI RAZZE
        specieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Specie selectedSpecie=null;
                int id_specie;
                if (!(specieSpinner.getSelectedItem() ==null)) {
                    for (int j=0; j<specieList.size(); j++){
                        String nome = specieList.get(j).getCommon_name();
                        if (nome.equals(specieSpinner.getSelectedItem()))
                            selectedSpecie=specieList.get(j);
                    }
                    id_specie = selectedSpecie.getId_specie();

                    Call<List<Race>> getRaceBySpecie = api.getRaceBySpecie(id_specie);
                    getRaceBySpecie.enqueue(new Callback<List<Race>>() {
                        @Override
                        public void onResponse(Call<List<Race>> call, Response<List<Race>> response) {
                            raceList = response.body();
                            caricaRazze(raceList);
                            Toast.makeText(NewPetActivity.this, "Race response ok", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<List<Race>> call, Throwable t) {
                            Toast.makeText(NewPetActivity.this, "Race response fail", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //ACQUISISCE RAZZA SELEZIONATA
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(!(raceSpinner.getSelectedItem()==null)){
                    for(int j=0; j<raceList.size(); j++){
                        String nome=raceList.get(j).getName();
                        if(nome.equals(raceSpinner.getSelectedItem())){
                            selectedRace =raceList.get(j);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //FUNZIONAMENTO SEEKBAR PESO E ACQUISIZIONE VALORE
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
                weight=seekBar.getProgress();
            }
        });

        //SETTO LISTENER SUL RADIOGROUP DEL PELO E AQUISISCO VALORE
        furTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedFurButton =findViewById(furTypeRadioGroup.getCheckedRadioButtonId());
                pelo = selectedFurButton.getText().toString();
            }
        });

        //SETTO LISTENER SULLA VARIABILE STERILIZED E ACQUISISCO VALORE
        sterilizedRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                sterilizedSelected =findViewById(sterilizedRadioGroup.getCheckedRadioButtonId());
                sterilized=getBooleanSterilizedFromText(sterilizedSelected.getText().toString());
            }
        });

        birthdate=getDateFromDatePicker(datePicker);

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedSexButton =findViewById(sexRadioGroup.getCheckedRadioButtonId());
                sex=getBooleanSexFromText(selectedSexButton.getText().toString());
            }
        });

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name!=null && pelo!=null){
                    int id_race = selectedRace.getId_race();
                    Pet pet = new Pet(id_pet, id_user, id_race, name, weight, sex, birthdate, null, null, sterilized, pelo);
                    ownersPets.add(pet);
                    id_pet++;
                    Toast.makeText(NewPetActivity.this, name, Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(NewPetActivity.this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            }
        });

    }

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Uri uri = data.getData();
            addImage.setImageURI(uri);
        }
    }

    void pickImage() {
        Intent imageIntent = new Intent();
        imageIntent.setType("image/*");
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imageIntent, "title"), SELECT_IMAGE_CODE);
    }

    boolean getBooleanSexFromText(String sex) {
        if (sex.equals("M"))
            return true;
        else return false;
    }

    boolean getBooleanSterilizedFromText(String sterilized) {
        if (sterilized.equals("Sì"))
            return true;
        else return false;
    }

    protected Pet createNewPet(Race race) {
        String name = nameET.getText().toString();
        int id_race = race.getId_race();
        double weight = pesoBar.getProgress();
        boolean sterilized = getBooleanSterilizedFromText(sterilizedSelected.getText().toString());
        boolean sex = getBooleanSexFromText(selectedSexButton.getText().toString());
        String furType = selectedFurButton.getText().toString();
        Date birthdate = getDateFromDatePicker(datePicker);

        Pet pet = new Pet(id_pet, id_user, id_race, name, weight, sex, birthdate, null, null, sterilized, furType);
        return pet;
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public void caricaSpecie(List<Specie> specieList) {
        //CARICO SPINNER PER LA SPECIE CON VALORE DI DEFAULT
        String[] nomiSpecie = new String[specieList.size()];
        for (int i = 0; i < specieList.size(); i++) {
            nomiSpecie[i] = specieList.get(i).getCommon_name();
        }
        ArrayAdapter<CharSequence> specieAdapter1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_dropdown_item_1line, nomiSpecie);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.species_spinner_nothing_selected, this);
        specieSpinner.setAdapter(specieAdapter2);
    }

    public void caricaRazze(List<Race> raceList) {
        String[] nomiRazze = new String[raceList.size()];
        for (int i = 0; i < raceList.size(); i++) {
            nomiRazze[i] = raceList.get(i).getName();
        }
        ArrayAdapter<CharSequence> specieAdapter1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_dropdown_item_1line, nomiRazze);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.species_spinner_nothing_selected, this);
        raceSpinner.setAdapter(specieAdapter2);
    }
}