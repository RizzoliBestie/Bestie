package com.example.bestie.API;

import com.example.bestie.database.Utent_Table;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Methods_Interface {

    @GET("/")
    Call<Utent_Table> buttare();

}
