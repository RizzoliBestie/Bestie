package com.example.bestie.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.bestie.R;

import java.util.ArrayList;
import java.util.Date;

public class PetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        
        Toolbar toolbar = findViewById(R.id.pet_toolbar);
        setSupportActionBar(toolbar);


    }



}