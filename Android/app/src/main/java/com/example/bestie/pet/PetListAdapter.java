package com.example.bestie.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bestie.R;

import java.util.ArrayList;
import java.util.List;

public class PetListAdapter extends ArrayAdapter<InfoPetListItem> {

    Context context;
    int resource;

    public PetListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<InfoPetListItem> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String subtitle = getItem(position).getSubtitle();

        InfoPetListItem infoPet = new InfoPetListItem(title, subtitle);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView=inflater.inflate(resource,parent,false);

        TextView titleTV = convertView.findViewById(R.id.info_pet_title);
        TextView subtitleTV = convertView.findViewById(R.id.info_pet_subtitle);

        titleTV.setText(title);
        subtitleTV.setText(subtitle);

        return convertView;
    }
}
