package com.example.bestie.storage;

public class Item {
    private String image_url = null,file_name = null,description = null;

    public Item(String file_name,String description){
        setFile_name(file_name);
        setDescription(description);
        setImage_url(findImageUrl());
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String findImageUrl(){
        if(getFile_name().contains(".pdf"))
            return "https://cdn-icons-png.flaticon.com/512/337/337946.png";
        if(getFile_name().contains(".doc") || getFile_name().contains(".docx"))
            return "https://icon-library.com/images/doc-icon-png/doc-icon-png-6.jpg";
        if(getFile_name().contains(".txt"))
            return "https://cdn-icons-png.flaticon.com/512/337/337956.png";
        if(getFile_name().contains(".png"))
            return "https://blog.idrsolutions.com/wp-content/uploads/2017/03/PNG.png";
        if(getFile_name().contains(".jpg") || getFile_name().contains(".jpeg"))
            return "https://icons.iconarchive.com/icons/pelfusion/flat-file-type/256/jpg-icon.png";
        if(getFile_name().contains(".gif"))
            return "https://cdn-icons-png.flaticon.com/512/337/337936.png";
        if(getFile_name().contains(".img"))
            return "https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-1024.png";
        return "https://cdn.icon-icons.com/icons2/2753/PNG/512/ext_file_generic_filetype_icon_176256.png";
    }
}
