package com.example.bestie.general;

import com.example.bestie.pet.Pet;

import java.util.ArrayList;

public class Profile {
    public String username;
    private String email;
    private String password;
    private String address;
    public ArrayList<Pet> pet = new ArrayList<Pet>();

    public Profile(String user, String mail, String pass, String address){
        this.username = user;
        this.email = mail;
        this.password = pass;
        this.address = address;
    }
}
