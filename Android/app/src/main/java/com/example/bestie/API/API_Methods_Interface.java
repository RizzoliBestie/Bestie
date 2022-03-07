package com.example.bestie.API;

import androidx.versionedparcelable.NonParcelField;

import com.example.bestie.database.Utent_Table;
import com.example.bestie.general.Race;
import com.example.bestie.general.Specie;
import com.example.bestie.general.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API_Methods_Interface {

    //Controlla che le credenziali del login siano corrette
    @GET("/login/{username}/{password}")
    Call<Boolean> checkLogin(@Path("username") String username,  @Path("password") String password);

    //Controlla che username ed email siano unici
    @GET("/signin/{username}/{email}")
    Call<Boolean> checkSignin(@Path("username") String username, @Path("email") String email);

    //Registrazione dell'utente
    @POST("/signin/{username}/{email}/{password}/{phone_number}")
    Call<Boolean> register(@Path("username")String username,@Path("email") String email,@Path("password") String password,@Path("phone_number") String phone_number);

    //Ritorna tutti gli utenti presenti nel DB
    @GET("/list/users")
    Call<List<User>> getAllUsers();

    //Ritorna tutte le razze presenti nel DB
    @GET("/list/races")
    Call<List<Race>> getAllRaces();

    //Ritorna tutte le specie presenti nel DB
    @GET("/list/species")
    Call<List<Specie>> getAllSpecies();
}
