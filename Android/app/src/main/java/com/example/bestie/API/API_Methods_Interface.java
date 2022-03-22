package com.example.bestie.API;

import com.example.bestie.general.Profile;
import com.example.bestie.general.Race;
import com.example.bestie.general.Specie;
import com.example.bestie.general.User;
import com.example.bestie.pet.Pet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Methods_Interface {

    //Controlla che le credenziali del login siano corrette
    @GET("/login/{username}/{password}")
    Call<Profile> checkLogin(@Path("username") String username, @Path("password") String password);

    //Controlla che username ed email siano unici
    @GET("/signin/{username}/{email}")
    Call<Boolean> checkSignin(@Path("username") String username, @Path("email") String email);

    //Registrazione dell'utente
    @POST("/signin/{username}/{email}/{password}/{phone_number}")
    Call<Boolean> register(@Path("username")String username,@Path("email") String email,@Path("password") String password,@Path("phone_number") String phone_number);

    //Ritorna tutte le razze presenti nel DB
    @GET("/list/races")
    Call<List<Race>> getAllRaces();

    //Ritorna tutte le specie presenti nel DB
    @GET("/list/species")
    Call<List<Specie>> getAllSpecies();

    @GET("/pets")
    Call<List<Pet>> getAllPets();

    //Aggiunge pet al DB
    @POST("/newPet/{pet}")
    Call<Boolean> addPet(@Body Pet pet);

    @GET("/races/{id_specie}")
    Call<List<Race>> getRaceBySpecie(@Path("id_specie") int id_specie);

    @GET("/pets/{id}")
    Call<Pet> getPetById(@Path("id") long id_pet);

    @GET("/specie/{common_name}")
    Call<Specie> getSpecieByName(@Path("common_name") String common_name);

    @PUT("/update/pet/{petObject}")
    Call<Boolean> updatePet(@Body Pet pet);

    @GET("/pets/user/{id_user}")
    Call<List<Pet>> getPetsByUserId(@Path("id_user") int id_user);

    //Ritorna tutti gli utenti presenti nel DB
    @GET("/list/users")
    Call<List<User>> getAllUsers();

    @GET("/race/{id_race}")
    Call<Race> getRaceById(@Path("id_race") int id_race);

    @GET("/specie/{id_specie}")
    Call<Specie> getSpecieById(@Path("id_specie") int id_specie);
}
