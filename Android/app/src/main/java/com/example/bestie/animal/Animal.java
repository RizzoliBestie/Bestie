package com.example.bestie.animal;

public class Animal {
    //Classe base per gli animali
    private String name;
    private Race race;
    private String specie; //anche come nome scientifico


    //Classe da base (da utilizzare per l'estensione in Pet)
    public Animal(Race r, String s){
        this.race = r;
        this.specie = s;
    }

    public Animal(String name,Race r, String s){
        this.name=name;
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
