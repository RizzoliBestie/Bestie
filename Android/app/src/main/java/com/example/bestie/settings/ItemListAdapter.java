package com.example.bestie.settings;

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

public class ItemListAdapter extends ArrayAdapter<Item> {

    Context context = null;
    int resource = 0;
    ArrayList<Item> items = new ArrayList<>();

    static class ViewHolder {
        TextView textTextView;
        TextView summaryTextView;
    }

    public ItemListAdapter(@NonNull Context context, int resource, @NonNull Item[] items) {
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

            vh.textTextView = convertView.findViewById(R.id.text_id);
            vh.summaryTextView = convertView.findViewById(R.id.summary_id);

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();

        Item items = getItem(position);

        vh.textTextView.setText(items.getText());
        vh.summaryTextView.setText(items.getSummary());

        return convertView;
    }
}
