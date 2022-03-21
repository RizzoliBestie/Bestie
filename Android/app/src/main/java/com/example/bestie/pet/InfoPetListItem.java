package com.example.bestie.pet;

import android.text.NoCopySpan;
import android.widget.TextView;

public class InfoPetListItem {
    String title;
    String subtitle;
    double value;
    boolean trueFalse;

    public InfoPetListItem(String title, String subtitle){
        this.title=title;
        this.subtitle=subtitle;
    }

    public InfoPetListItem(String title, double value){
        this.title=title;
        this.value=value;
        subtitle=Double.toString(value);
    }

    public InfoPetListItem(String title, boolean trueFalse){
        this.title=title;
        this.trueFalse=trueFalse;
        subtitle=turnBooleantoString(trueFalse);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String turnBooleantoString(boolean value){
        if(value)
            return "Sì";
        return "No";
    }

}
