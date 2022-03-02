package com.example.bestie.animal;

import java.io.Serializable;

public class AnimalSection implements Serializable {
    public String section_name;
    public int icon; //solo interno come drawable
    //Esempio: R.drawable.icon

    /*public AnimalSection(String section_name, int icon){
        this.section_name = section_name;
        this.icon = icon;
    } */ //NON forzare l'uso del costruttore in questo caso

    public String getSectionName() {
        return section_name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setSectionName(String section_name) {
        this.section_name = section_name;
    }

    @Override
    public String toString() {
        return getSectionName();
    }
}
