package com.example.bestie.animal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bestie.R;

import java.util.List;

public class AnimalSectionSpinnerAdapter extends ArrayAdapter<AnimalSection> {
    private Context ctx = null;
    private List<AnimalSection> animalSectionList;

    public AnimalSectionSpinnerAdapter(@NonNull Context context, int resource, int textviewId, @NonNull List<AnimalSection> objects) {
        super(context, resource, textviewId,  objects);
        this.ctx = context;
        this.animalSectionList = objects;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(ctx)
                .inflate(R.layout.row_section, viewGroup, false);

        TextView txtAnimalSection = rootView.findViewById(R.id.sectionNameTextView);
        ImageView imageAnimalSection = rootView.findViewById(R.id.animalSectionSpinnerImageview);

        txtAnimalSection.setText(animalSectionList.get(position).getSectionName());
        imageAnimalSection.setImageResource(animalSectionList.get(position).getIcon());

        return rootView;
    }
/*

    public String toString(int position) {
        return animalSectionList.get(position).section_name;
    }*/
}
