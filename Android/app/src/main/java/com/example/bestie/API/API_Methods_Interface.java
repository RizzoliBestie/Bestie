package com.example.bestie.API;

import com.example.bestie.database.Utent_Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Methods_Interface {

    @GET("/prova/users")
    Call<List<Utent_Table>> buttare();

}
