package com.example.bestie.animal;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.bestie.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class AnimalArrayAdapter extends ArrayAdapter<AnimalWiki> {
    Context ctx = null;
    int res = 0;
    ArrayList<AnimalWiki> dati = new ArrayList<>();
    private Filter filter;

    static class ViewHolder {
        ImageView animalImageView;
        CircularImageView circular_animalImageView; //Per gestire l'immagine circolare
        TextView nameTextView;
        TextView raceTextView;
        TextView specieTextView;

        public void setAnimalImageView(ImageView animalImageView) {
            this.animalImageView = animalImageView;
        }
    }

    public AnimalArrayAdapter(@NonNull Context context, int resource, @NonNull List<AnimalWiki> objects) {
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
        AnimalWiki p = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(res, parent, false);

            ViewHolder vh = new ViewHolder();
            vh.animalImageView = convertView.findViewById(R.id.animalImageView);
            vh.circular_animalImageView = convertView.findViewById(R.id.animalImageView);
            vh.nameTextView = convertView.findViewById(R.id.nameTextView);
            vh.raceTextView = convertView.findViewById(R.id.raceTextView);
            vh.specieTextView = convertView.findViewById(R.id.speciesTextView);

            if(p.getImageUrl() != null) {
                String urlImgS = p.getImageUrl();
                (new AnimalDownloadImage(ctx, p.getImageUrl(), vh.animalImageView)).execute();
            } else {
                vh.animalImageView.setImageResource(R.drawable.volpe); //Fa solo l'immagine della volpe
            }

            switch(p.getSection()){
                case "WILD":
                    vh.nameTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_wild_testo_principale));
                    vh.raceTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_wild_testo_secondario));
                    vh.specieTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_wild_testo_secondario));
                    vh.circular_animalImageView.setBorderColor(ContextCompat.getColor(getContext(), R.color.settore_wild_jolly));
                    break;
                case "FARM":
                    vh.nameTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_farm_testo_principale));
                    vh.raceTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_farm_testo_secondario));
                    vh.specieTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_farm_testo_secondario));
                    vh.circular_animalImageView.setBorderColor(ContextCompat.getColor(getContext(), R.color.settore_farm_jolly));
                    break;
                case "PETS":
                    vh.nameTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_pets_testo_principale));
                    vh.raceTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_pets_testo_secondario));
                    vh.specieTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_pets_testo_secondario));
                    vh.circular_animalImageView.setBorderColor(ContextCompat.getColor(getContext(), R.color.settore_pets_jolly));
                    break;
            }

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();

        vh.nameTextView.setText(p.getName());
        vh.raceTextView.setText(p.getRace().getName());
        vh.specieTextView.setText(p.getSpecie());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new AppFilter(dati);
        return filter;
    }



    private class AppFilter<T> extends Filter {

        private ArrayList<T> sourceObjects;

        public AppFilter(List<T> objects) {
            sourceObjects = new ArrayList<T>();
            synchronized (this) {
                sourceObjects.addAll(objects);
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence chars) {
            String filterSeq = chars.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (filterSeq != null && filterSeq.length() > 0) {
                ArrayList<T> filter = new ArrayList<T>();

                for (T object : sourceObjects) {
                    // the filtering itself:
                    String temp = object.toString().toLowerCase();
                    if (temp.contains(filterSeq)){
                        filter.add(object);
                    }
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
            ArrayList<T> filtered = (ArrayList<T>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = filtered.size(); i < l; i++){
                add((AnimalWiki)filtered.get(i));
            }
            notifyDataSetInvalidated();
        }
    }
}
