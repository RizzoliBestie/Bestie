package com.example.bestie;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ImageBox {
    private Context context = null;
    static private ArrayList<ImageView> imageLIst = new ArrayList<>();

    public ImageBox(Context context, @NonNull Image[] images){
        this.context = context;
        for(int k = 0; k < images.length; k++){
            ImageView newImage = new ImageView(context);
            newImage.setTransitionName(images[k].getUrl());
            imageLIst.add(newImage);
            (new ImageDownloader(context, images[k].getUrl(), newImage)).execute();
        }
    }

    @Nullable
    static public ImageView getImage(String id){
        for(ImageView image : imageLIst)
            if(image.getTransitionName().equals(id))
                return image;
        return null;
    }
}
