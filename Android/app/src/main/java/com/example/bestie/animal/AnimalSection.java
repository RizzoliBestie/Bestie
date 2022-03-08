package com.example.bestie.animal;

import java.io.Serializable;

public class AnimalSection implements Serializable {
    private String section_name;
    private int icon; //solo interno come drawable
    //Esempio: R.drawable.icon

    //NON forzare l'uso del costruttore in questo caso

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
