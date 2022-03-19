package com.example.bestie.pet;

import com.example.bestie.animal.Animal;
import com.example.bestie.animal.Race;
import com.example.bestie.general.Profile;

import java.util.Date;

public class Pet extends Animal {
    long id_pet;
    public Profile owner; //Proprietario
    public String nickname; //Nickname Pet
    public double weight; //Peso
    public boolean sex; //Maschio o Femmina //SUGGERIMENTO: Forse stringa invece di boolean?
    public Date birthDate; //Data di nascita
    public Date lastMeal; //Pasto recente
    public Date lastWalk; //Camminata recente
    public boolean sterilized;
    public String furType;


    //Costruttore per la creazione del Pet
    public Pet(long id_pet,Race race, String specie, Profile owner, String nick, double weight, boolean sex, boolean sterilized, String furType) {
        //Tutti gli attributi dell'animale
        super(race, specie);
        //Attributi proprio Pet
        this.id_pet=id_pet;
        this.owner = owner;
        this.nickname = nick;
        this.weight = weight;
        this.sex = sex;
        this.sterilized=sterilized;
        this.furType=furType;
    }

    //Aggiorna Pasto più recente
    public void setLastMeal(Date lastMeal) { this.lastMeal = lastMeal; }

    //Aggiorna Camminata più recente
    public void setLastWalk(Date lastWalk) { this.lastWalk = lastWalk; }

    //Aggiorna il peso
    public void setWeight(double weight) { this.weight = weight; }

    public String getNickname() { return nickname; }

    public Date getBirthDate() { return birthDate; }

    public double getWeight() { return weight; }

    //Converte il boolean in stringa
    private String sexToString(boolean sex) {
        if(sex){
            return "Maschio";
        } else {
            return "Femmina";
        }
    }

    public void addPetToOwner(Pet pet){
        this.owner.pet.add(pet);
    }

    //Ritorna sex se richiesto in stringa
    public String getSextoString(){
        return sexToString(this.sex);
    }

    public boolean isSterilized() {
        return sterilized;
    }

    public void setSterilized(boolean sterilized) {
        this.sterilized = sterilized;
    }

    public void remind(){
        //DOPO
    }

    public void inform(){
        //DOPO
    }
}
