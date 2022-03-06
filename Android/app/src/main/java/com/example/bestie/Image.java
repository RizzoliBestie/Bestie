package com.example.bestie;

public class Image {
    private String url = null, id = null;

    public Image(String url, String id){
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
