package com.example.bestie.animal;

public class Animal {
    //Classe base per gli animali
    public String name;
    public String race;
    public String specie;
    public String section;
    public String image_url;

    //Senza Immagine
    public Animal(String n, String r, String s, String sec){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.section = sec;
        this.image_url = null;
    }

    //Con Immagine
    public Animal(String n, String r, String s, String sec, String url){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.section = sec;
        this.image_url = url;
    }
}
