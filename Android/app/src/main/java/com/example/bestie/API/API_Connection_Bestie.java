package com.example.bestie.API;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Connection_Bestie extends Application {

    public static final String BASE_URL = "http://192.168.198.200:8080/";
    private Retrofit retrofit = null;

    public API_Connection_Bestie () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return this.retrofit;
    }

}
