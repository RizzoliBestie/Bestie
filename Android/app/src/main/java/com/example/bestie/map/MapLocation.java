package com.example.bestie.map;

import com.example.bestie.general.Profile;
import com.example.bestie.general.User;

public class MapLocation {
    public User user;
    public double latitude;
    public double longitude;
    public String type; //Tipo di location, per esempio Veterinario o Negozio di animali

    //Costruttore generale
    public MapLocation(double latitude, double longitude) {
        this.user = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = null;
    }

    //Costruttore per indicare la posizione di una struttura come Veterinario o Negozio di animali
    public MapLocation(String type, double latitude, double longitude) {
        this.user = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    //Costruttore per indicare la posizione di un utente
    public MapLocation(User user, double latitude, double longitude) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = null;
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

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setType(String type) {
        this.type = type;
    }
}
