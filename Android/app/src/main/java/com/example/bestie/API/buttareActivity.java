package com.example.bestie.API;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bestie.R;
import com.example.bestie.database.Utent_Table;
import com.example.bestie.login.LogInActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class buttareActivity extends AppCompatActivity {

    API_Methods_Interface apiMethodsInterface = null;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttare);

        textView = findViewById(R.id.buttare);

        Call<Utent_Table> call = apiMethodsInterface.buttare();//GET http://api.myservice.com/users/antolezzi
        call.enqueue(new Callback<Utent_Table>() {
            @Override
            public void onResponse(Call<Utent_Table> call, Response<Utent_Table> response) {
                int statusCode = response.code();
                Utent_Table ut = response.body();

                textView.setText(ut.getId());
            }

            @Override
            public void onFailure(Call<Utent_Table> call, Throwable t) {
            }
        });

    }
}