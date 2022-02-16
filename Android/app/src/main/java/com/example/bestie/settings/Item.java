package com.example.bestie.settings;

public class Item {

    private boolean isPassword = false;

    public Item(String text, String summary){
        setText(text);
        setSummary(summary);
        if(text.equals("Password")){
            isPassword = true;
        }
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public String getSummary() {
        String covered = "";
        if(isPassword){
            for(int i = 0; i < summary.length(); i++){
                covered += '*';
            }
            return covered;
        }
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String summary;
}
