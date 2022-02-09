package com.example.bestie.animal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bestie.R;
import com.example.bestie.curiosity.CuriosityActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnimalArrayAdapter extends ArrayAdapter<Animal> {
    Context ctx = null;
    int res = 0;
    ArrayList<Animal> dati = new ArrayList<>();
    private Filter filter;

    static class ViewHolder {
        ImageView animalImageView;
        TextView nameTextView;
        TextView raceTextView;
        TextView specieTextView;
    }

    public AnimalArrayAdapter(@NonNull Context context, int resource, @NonNull List<Animal> objects) {
        super(context, resource, objects);
        ctx = context;
        res = resource;
        for (int i = 0; i < objects.size(); i++){
            dati.add(objects.get(i));
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //super.getView(position, convertView, parent);
        Animal p = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(res, parent, false);

            ViewHolder vh = new ViewHolder();

            vh.animalImageView = convertView.findViewById(R.id.animalImageView);
            vh.nameTextView = convertView.findViewById(R.id.nameTextView);
            vh.raceTextView = convertView.findViewById(R.id.raceTextView);
            vh.specieTextView = convertView.findViewById(R.id.speciesTextView);

            if(p.image_url != null) {
                String urlImgS = p.image_url;
                new AnimalDownloadImage(vh.animalImageView).execute(urlImgS);
            } else {
                vh.animalImageView.setImageResource(R.drawable.volpe); //Fa solo l'immagine della volpe
            }

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();


        vh.nameTextView.setText(p.name);
        vh.raceTextView.setText(p.race);
        vh.specieTextView.setText(p.specie);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new AppFilter(dati);
        return filter;
    }

    private class AppFilter extends Filter {

        private ArrayList<Animal> sourceObjects;

        public AppFilter(List<Animal> objects) {
            sourceObjects = new ArrayList<Animal>();
            synchronized (this) {
                sourceObjects.addAll(objects);
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence chars) {
            String filterSeq = chars.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (filterSeq != null && filterSeq.length() > 0) {
                ArrayList<Animal> filter = new ArrayList<Animal>();

                for (Animal object : sourceObjects) {
                    // the filtering itself:
                    if (object.name.toLowerCase().contains(filterSeq))
                        filter.add(object);
                }
                result.count = filter.size();
                result.values = filter;
            } else {
                // add all objects
                synchronized (this) {
                    result.values = sourceObjects;
                    result.count = sourceObjects.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // NOTE: this function is *always* called from the UI thread.
            ArrayList<Animal> filtered = (ArrayList<Animal>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = filtered.size(); i < l; i++)
                add((Animal) filtered.get(i));
            notifyDataSetInvalidated();
        }
    }
}
