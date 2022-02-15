package com.example.bestie.database;

public class Utent_Table {

    private int id;
    private String username;
    private String email;
    private String password;
    private String phone_number;

    public Utent_Table(){
    }

    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getPhone_number(){
        return this.phone_number;
    }

}
