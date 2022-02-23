package com.example.bestie.API;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestie.R;
import com.example.bestie.database.Utent_Table;
import com.example.bestie.login.LogInActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class buttareActivity extends AppCompatActivity {

    API_Methods_Interface apiMethodsInterface = null;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttare);


        Retrofit retrofit = ((API_Connection_Bestie)getApplication()).getRetrofit();
        API_Methods_Interface apiMethodsInterface = retrofit.create(API_Methods_Interface.class);
        textView = findViewById(R.id.buttare);



        Call<String> call = apiMethodsInterface.checkLogin("mike","1239");//GET http://api.myservice.com/users/antolezzi
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                String ut = response.body();

                textView.setText(ut);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}