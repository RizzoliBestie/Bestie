package com.example.bestie.animal;

public class Race {
    private String name;
    private String information;
    private String size;
    private String fur_type;
    //idea: weight?

    //Solo nome della razza
    public Race(String name){
        this.name = name;
        this.information = "";
        this.size = "";
    }

    //Informazioni generali
    public Race(String name, String information){
        this.name = name;
        this.information = information;
        this.size = "";
    }

    //Tutto senza informazioni generali
    public Race(String name, String size, String fur_type){
        this.name = name;
        this.size = size;
        this.fur_type=fur_type;
    }

    //Tutte le informazioni
    public Race(String name, String information, String size, String fur_type){
        this.name = name;
        this.information = information;
        this.size = size;
        this.fur_type = fur_type;
    }

    public String getName() { return name; }

    public String getInformation() { return information; }

    public String getSize() { return size; }

    public String getFur_type() {
        return fur_type;
    }

    public void setName(String name) { this.name = name; }

    public void setInformation(String information) { this.information = information; }

    public void setSize(String size) { this.size = size; }

    public void setFur_type(String fur_type) { this.fur_type = fur_type; }

    @Override
    public String toString() { return name + ' ' + information + ' ' + size; }
}
