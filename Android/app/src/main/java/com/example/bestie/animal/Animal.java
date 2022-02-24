package com.example.bestie.animal;

public class Animal {
    //Classe base per gli animali
    public String name;
    public String race;
    public String specie;


    //Classe da base (da utilizzare per l'estensione in Pet)
    public Animal(String n, String r, String s){
        this.name = n;
        this.race = r;
        this.specie = s;
    }

    @Override
    public String toString() {
        return name + ' ' + race + ' ' + specie;
    }
}
