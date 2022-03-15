package com.example.bestie.map;

import com.example.bestie.general.Profile;
import com.example.bestie.general.User;

public class MapLocation {
    public User user;
    public double latitude;
    public double longitude;
    public String name; //Nome della location
    public String type; //Tipo di location, per esempio Veterinario o Negozio di animali
    public String city; //Città della location

    //Costruttore generale
    public MapLocation(double latitude, double longitude) {
        this.user = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = null;
        this.type = null;
        this.city = null;
    }

    //Costruttore per indicare la posizione di una struttura come Veterinario o Negozio di animali
    public MapLocation(String name, String type, String city, double latitude, double longitude) {
        this.user = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.type = type;
        this.city = city;
    }

    //Costruttore per indicare la posizione di un utente
    public MapLocation(User user, double latitude, double longitude) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = null;
        this.type = null;
        this.city = null;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
