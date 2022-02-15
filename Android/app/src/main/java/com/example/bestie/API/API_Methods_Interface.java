package com.example.bestie.API;

import com.example.bestie.database.Utent_Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

public interface API_Methods_Interface {

    @GET("/users")
    Call<List<Utent_Table>> buttare();

    @DELETE("/delete")
    Call<Utent_Table> deleteUser(int id);

}
