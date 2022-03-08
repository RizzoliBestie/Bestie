package com.example.bestie.animal;

public class Animal {
    //Classe base per gli animali
    private String name;
    private Race race;
    private String specie;


    //Classe da base (da utilizzare per l'estensione in Pet)
    public Animal(String n, Race r, String s){
        this.name = n;
        this.race = r;
        this.specie = s;
    }

    public String getName() {
        return name;
    }

    public Race getRace() { return race; }

    public String getSpecie() {
        return specie;
    }

    @Override
    public String toString() {
        return name + ' ' + race + ' ' + specie;
    }
}
