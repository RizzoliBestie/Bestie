package com.example.bestie.pet;

import com.example.bestie.animal.Animal;
import com.example.bestie.general.Profile;

import java.util.Date;

public class Pet extends Animal {
    Profile owner; //Proprietario
    public String nickname; //Nickname Pet
    public double weight; //Peso
    public boolean sex; //Maschio o Femmina //SUGGERIMENTO: Forse stringa invece di boolean?
    public Date birthDate; //Data di nascita
    public Date lastMeal; //Pasto recente
    public Date lastWalk; //Camminata recente


    //Costruttore per la creazione del Pet
    public Pet(String n, String r, String s, Profile owner, String nick, double weight, boolean sex) {
        //Tutti gli attributi dell'animale
        super(n, r, s);
        //Attributi proprio Pet
        this.owner = owner;
        this.nickname = nick;
        this.weight = weight;
        this.sex = sex;

        addPetToOwner(n,r,s,owner,nick,weight,sex);
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
        if(sex == false){
            return "Maschio";
        } else {
            return "Femmina";
        }
    }

    public void addPetToOwner(String n, String r, String s, Profile owner, String nick, double weight, boolean sex){
        //Il Pet viene aggiunto alla ArrayList dei Pet del proprietario
        Pet pet = new Pet(n,r,s,owner,nick,weight,sex);
        this.owner.pet.add(pet);
    }

    //Ritorna sex se richiesto in stringa
    public String getSextoString(){
        return sexToString(this.sex);
    }

    public void remind(){
        //DOPO
    }

    public void inform(){
        //DOPO
    }
}
