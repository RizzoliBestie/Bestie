package com.example.bestie.pet;

import java.util.Date;

public class Pet {
    private int id_pet;
    private int id_user;
    private int id_race;
    private String name;
    private double weight;
    private boolean gender;
    private Date birthDate;
    private Date lastMeal;
    private Date lastWalk;
    private boolean sterilized;
    private String fur_type;
   // private String uri_image;

    public Pet(int id_pet, int id_user, int id_race, String name, double weight, boolean gender, Date birthDate, Date lastMeal, Date lastWalk, boolean sterilized, String fur_type) {
        this.id_pet = id_pet;
        this.id_user = id_user;
        this.id_race = id_race;
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.birthDate = birthDate;
        this.lastMeal = lastMeal;
        this.lastWalk = lastWalk;
        this.sterilized = sterilized;
        this.fur_type = fur_type;
        //this.uri_image= uri_image;
    }

    

    public boolean isGender() {
        return gender;
    }

    public boolean isSterilized() {
        return sterilized;
    }

   /* public String getUri_image() {
        return uri_image;
    }*/
    public boolean getGender() {
        return gender;
    }

    public int getId_race() {
        return id_race;
    }

    public long getId_pet() {
        return id_pet;
    }

    public long getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getLastMeal() {
        return lastMeal;
    }

    public Date getLastWalk() {
        return lastWalk;
    }

    public boolean getSterilized() {
        return sterilized;
    }

    public String getFur_type() {
        return fur_type;
    }
}
