package com.example.bestie.general;

import com.example.bestie.pet.Pet;

import java.util.ArrayList;

public class Profile {
    String username;
    String email;
    String password;
    String address;
    ArrayList<Pet> pet = new ArrayList<Pet>();

    public Profile(String user, String mail, String pass, String address){
        this.username = user;
        this.email = mail;
        this.password = pass;
        this.address = address;
    }
}
