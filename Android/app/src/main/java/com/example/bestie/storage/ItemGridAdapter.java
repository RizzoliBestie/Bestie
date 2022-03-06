package com.example.bestie.storage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bestie.R;

import java.util.ArrayList;

public class ItemGridAdapter extends ArrayAdapter<Item> {

    Context context = null;
    int resource = 0;
    ArrayList<Item> items = new ArrayList<>();

    static class ViewHolder {
        ImageView file_imageView;
        TextView file_nameTextView;
        TextView descriptionTextView;
    }

    public ItemGridAdapter(@NonNull Context context, int resource, @NonNull Item[] items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        for(int i = 0; i < items.length; i++)
            this.items.add(items[i]);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);

            ViewHolder vh = new ViewHolder();

            vh.file_imageView = convertView.findViewById(R.id.file_image);
            vh.file_nameTextView = convertView.findViewById(R.id.file_name);
            vh.descriptionTextView = convertView.findViewById(R.id.file_description);

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();

        Item item = getItem(position);

        vh.file_imageView = com.example.bestie.ImageBox.getImage(item.getIcon_id());
        vh.file_nameTextView.setText(item.getFile_name());
        vh.descriptionTextView.setText(item.getDescription());

        return convertView;
    }
}
