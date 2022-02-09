package com.example.bestie.animal;

public class Animal {
    //Classe base per gli animali
    public String name;
    public String race;
    public String specie;
    public String image_url; //da mettere dopo

    //Senza Immagine
    public Animal(String n, String r, String s){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.image_url = null;
    }

    //Con Immagine
    public Animal(String n, String r, String s, String url){
        this.name = n;
        this.race = r;
        this.specie = s;
        this.image_url = url;
    }
}
