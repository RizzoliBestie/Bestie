package com.example.bestie.animal;

public class AnimalWiki extends Animal{
    private AnimalSection section = new AnimalSection();
    private String image_url;

    //Senza Immagine o descrizione
    public AnimalWiki(String name, Race race, String specie, String sec){
        super(name, race, specie);
        this.section.setSectionName(sec);
        this.image_url = null;
    }

    //Con Immagine
    public AnimalWiki(String name, Race race, String specie, String sec, String url){
        super(name, race, specie);
        this.section.setSectionName(sec);
        this.image_url = url;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setSection(String section) { this.section.setSectionName(section); }

    public String getImageUrl() {
        return image_url;
    }

    public String getSection() {
        return section.getSectionName();
    }

    @Override
    public String toString() {
        return getName() + ' ' + getRace() + ' ' + getSpecie() + ' ' + section.getSectionName();
    }
}
