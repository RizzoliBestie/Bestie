package com.example.bestie.storage;

public class Item {
    private String icon_id = null,file_name = null,description = null;

    public Item(String file_name,String description){
        setFile_name(file_name);
        setDescription(description);
        setIcon_id(findIconId());
    }

    public String getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(String image_url) {
        this.icon_id = image_url;
    }

    public String getFile_name() {
        if(file_name.length() > 19)
            return file_name.replaceAll(file_name.substring(16),"...");
        return file_name;
    }

    public String getCompleteFile_name(){
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getDescription() {
        if(description.length() > 19)
            return description.replaceAll(description.substring(16),"...");
        return  description;
    }

    public String getCompleteDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String findIconId(){
        if(getFile_name().contains(".pdf"))
            return "pdf";
        if(getFile_name().contains(".doc") || getFile_name().contains(".docx"))
            return "doc";
        if(getFile_name().contains(".xls") || getFile_name().contains(".xlsx"))
            return "xls";
        if(getFile_name().contains(".txt"))
            return "txt";
        if(getFile_name().contains(".png"))
            return "png";
        if(getFile_name().contains(".jpg") || getFile_name().contains(".jpeg"))
            return "jpg";
        if(getFile_name().contains(".gif"))
            return "gif";
        if(getFile_name().contains(".img"))
            return "img";
        if(getFile_name().contains(".zip"))
            return "zip";
        return "generic";
    }
}
