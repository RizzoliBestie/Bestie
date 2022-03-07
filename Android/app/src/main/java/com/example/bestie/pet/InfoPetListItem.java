package com.example.bestie.pet;

import android.text.NoCopySpan;
import android.widget.TextView;

public class InfoPetListItem {
    String title;
    String subtitle;

    public InfoPetListItem(String title, String subtitle){
        this.title=title;
        this.subtitle=subtitle;
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
}
