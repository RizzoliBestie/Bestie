package com.example.bestie.API;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bestie.R;
import com.example.bestie.database.Utent_Table;
import com.example.bestie.login.LogInActivity;

import java.util.List;

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

        Call<List<Utent_Table>> call = apiMethodsInterface.buttare();//GET http://api.myservice.com/users/antolezzi
        call.enqueue(new Callback<List<Utent_Table>>() {
            @Override
            public void onResponse(Call<List<Utent_Table>> call, Response<List<Utent_Table>> response) {
                int statusCode = response.code();
                List<Utent_Table> ut = response.body();

                textView.setText(ut.get(0).username);
            }

            @Override
            public void onFailure(Call<List<Utent_Table>> call, Throwable t) {
            }
        });

    }
}