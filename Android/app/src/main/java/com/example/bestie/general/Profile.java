package com.example.bestie.general;

import com.example.bestie.pet.Pet;

import java.util.ArrayList;

public class Profile {
    private int id_user;
    private String username;
    private String email;
    private String password;
    private String phone_number;
    private ArrayList<Pet> pet = new ArrayList<Pet>();

    public Profile(int id_user, String user, String mail, String pass, String phone_number){
        this.id_user=id_user;
        this.username = user;
        this.email = mail;
        this.password = pass;
        this.phone_number = phone_number;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public ArrayList<Pet> getPet() {
        return pet;
    }

    public void setPet(ArrayList<Pet> pet) {
        this.pet = pet;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
