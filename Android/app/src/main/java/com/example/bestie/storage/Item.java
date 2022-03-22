package com.example.bestie.storage;

public class Item {
    private String image_url = null,file_name = null,description = null;

    public Item(String file_name,String description){
        setFile_name(file_name);
        setDescription(description);
        setImage_url(findUrl());
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String url) {
        this.image_url = url;
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

    private String findUrl(){
        if(getFile_name().contains(".pdf"))
            return "https://cdn-icons-png.flaticon.com/512/337/337946.png";
        if(getFile_name().contains(".doc") || getFile_name().contains(".docx"))
            return "https://icon-library.com/images/doc-icon-png/doc-icon-png-6.jpg";
        if(getFile_name().contains(".xls") || getFile_name().contains(".xlsx"))
            return "https://www.clipartmax.com/png/full/241-2414609_filename-extension-icon-xls-microsoft-excel-binary-excel-file-type.png";
        if(getFile_name().contains(".txt"))
            return "https://cdn-icons-png.flaticon.com/512/337/337956.png";
        if(getFile_name().contains(".png"))
            return "https://icons.iconarchive.com/icons/graphicloads/filetype/256/png-icon.png";
        if(getFile_name().contains(".jpg") || getFile_name().contains(".jpeg"))
            return "https://icons.iconarchive.com/icons/pelfusion/flat-file-type/256/jpg-icon.png";
        if(getFile_name().contains(".gif"))
            return "https://cdn-icons-png.flaticon.com/512/337/337936.png";
        if(getFile_name().contains(".img"))
            return "https://cdn.iconscout.com/icon/free/png-256/image-file-2014989-1700537.png";
        if(getFile_name().contains(".zip"))
            return "https://icons.iconarchive.com/icons/pelfusion/flat-file-type/256/zip-icon.png";
        return "https://cdn.icon-icons.com/icons2/2753/PNG/512/ext_file_generic_filetype_icon_176256.png";
    }
}
