package com.example.bestie.animal;

public class AnimalWiki extends Animal{
    public String section;
    public String image_url;
    public String description;

    //Senza Immagine o descrizione
    public AnimalWiki(String name, String race, String specie, String sec){
        super(name, race, specie);
        this.section = sec;
        this.image_url = null;
        this.description = null;
    }

    //Con Immagine
    public AnimalWiki(String name, String race, String specie, String sec, String url){
        super(name, race, specie);
        this.section = sec;
        this.image_url = url;
        this.description = null;
    }

    //Con Immagine e descrizione
    public AnimalWiki(String name, String race, String specie, String sec, String url, String desc){
        super(name, race, specie);
        this.section = sec;
        this.image_url = url;
        this.description = desc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getSection() {
        return section;
    }

    @Override
    public String toString() {
        return name + ' ' + race + ' ' + specie + ' ' + section + ' ' + description;
    }
}
