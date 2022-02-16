package com.example.bestie.API;

import com.example.bestie.database.Utent_Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API_Methods_Interface {

    @GET("/login")
    Call<List<Utent_Table>> buttare();

    @POST("/delete")
    Call<Integer> deleteUser(@Body int id);

}
