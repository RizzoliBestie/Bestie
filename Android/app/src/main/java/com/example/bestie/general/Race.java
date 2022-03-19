package com.example.bestie.general;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Race {

    @SerializedName("id_race")
    @Expose
    int id_race;
    @SerializedName("id_specie")
    @Expose
    int id_specie;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("information")
    @Expose
    String information;
    @SerializedName("size")
    @Expose
    String size;
    @SerializedName("fur_type")
    @Expose
    String fur_type;

    public Race() {
        this.id_race = -1;
        this.id_specie = -1;
        this.name = "";
        this.information = "";
        this.size = "";
        this.fur_type = "";
    }

    public int getId_race() {
        return id_race;
    }
    public void setId_race(int id_race) {
        this.id_race = id_race;
    }
    public int getId_specie() {
        return id_specie;
    }
    public void setId_specie(int id_specie) {
        this.id_specie = id_specie;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getFur_type() {
        return fur_type;
    }
    public void setFur_type(String fur_type) {
        this.fur_type = fur_type;
    }

}
