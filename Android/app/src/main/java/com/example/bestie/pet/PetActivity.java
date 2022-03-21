package com.example.bestie.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestie.API.API_Connection_Bestie;
import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.R;
import com.example.bestie.home.HomeFragment;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PetActivity extends AppCompatActivity {

    long id_pet;
    String name=null;
    String[] specie;
    String[] razze;
    double weight;
    String furType;
    boolean sterilized;
    boolean isMale;
    int id_race;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        TextView namePet = findViewById(R.id.petNameTV);

        id_pet = getIntent().getExtras().getLong("id_pet");
        if (id_pet==11) Toast.makeText(this, "id pet corretto", Toast.LENGTH_SHORT).show();
        Toolbar toolbar = findViewById(R.id.pet_toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = ((API_Connection_Bestie) getApplication()).getRetrofit();
        API_Methods_Interface api = retrofit.create(API_Methods_Interface.class);

        Call<Pet> getPetBbyId = api.getPetById(id_pet);
        getPetBbyId.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                Pet pet = response.body();
                name=pet.getName();
                weight=pet.getWeight();
                furType=pet.getFur_type();
                sterilized=pet.getSterilized();
                isMale=pet.getGender();
                id_race=pet.getId_race();

                sendDataToFragment();
                namePet.setText(name);
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

            }
        });




    }

    public void sendDataToFragment(){
        InfoPetFragment infoPetFragment = new InfoPetFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.info_pet_fragment_container, infoPetFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putDouble("weight", weight);
        bundle.putBoolean("sterilized", sterilized);
        bundle.putBoolean("isMale", isMale);
        bundle.putString("furType", furType);
        bundle.putInt("id_race", id_race);
        infoPetFragment.setArguments(bundle);
    }

}