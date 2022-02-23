package com.example.bestie.animal;

public class Animal {
    //Classe base per gli animali
    public String name;
    public String race;
    public String specie;
    public String section;
    public String image_url;
    public String description;

    //Classe da base (da utilizzare per l'estensione in Pet)
    public Animal(String n, String r, String s){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.section = null;
        this.image_url = null;
        this.description = null;
    }

    //Senza Immagine o descrizione
    public Animal(String n, String r, String s, String sec){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.section = sec;
        this.image_url = null;
        this.description = null;
    }

    //Con Immagine
    public Animal(String n, String r, String s, String sec, String url){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.section = sec;
        this.image_url = url;
        this.description = null;
    }

    //Con Immagine e descrizione
    public Animal(String n, String r, String s, String sec, String url, String desc){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.section = sec;
        this.image_url = url;
        this.description = desc;
    }

    @Override
    public String toString() {
        return name + ' ' + race + ' ' + specie + ' ' + section + ' ' + description;
    }
}
