package com.example.bestie.animal;

public class AnimalWiki extends Animal{
    private AnimalSection section = new AnimalSection();
    private String image_url;
    private String description;

    //Senza Immagine o descrizione
    public AnimalWiki(String name, Race race, String specie, String sec){
        super(name, race, specie);
        this.section.setSectionName(sec);
        this.image_url = null;
        this.description = null;
    }

    //Con Immagine
    public AnimalWiki(String name, Race race, String specie, String sec, String url){
        super(name, race, specie);
        this.section.setSectionName(sec);
        this.image_url = url;
        this.description = null;
    }

    //Con Immagine e descrizione
    public AnimalWiki(String name, Race race, String specie, String sec, String url, String desc){
        super(name, race, specie);
        this.section.setSectionName(sec);
        this.image_url = url;
        this.description = desc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setSection(String section) { this.section.setSectionName(section); }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getSection() {
        return section.getSectionName();
    }

    @Override
    public String toString() {
        return getName() + ' ' + getRace() + ' ' + getSpecie() + ' ' + section.getSectionName() + ' ' + description;
    }
}
