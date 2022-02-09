package com.example.bestie.settings;

public class Item {

    public Item(String text, String summary){
        setText(text);
        setSummary(summary);
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String summary;
}
