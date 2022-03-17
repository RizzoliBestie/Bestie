package com.example.bestie.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestie.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import kotlin.jvm.internal.Lambda;

public class CardAdapter extends RecyclerView.Adapter<CardVH> {

    List<PetCard> items;

    public CardAdapter(List<PetCard> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CardVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_home, parent, false);
        return new CardVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull CardVH holder, int position) {
        holder.textView.setText(items.get(position).title);
        holder.imageView.setImageResource(items.get(position).imgId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class CardVH extends RecyclerView.ViewHolder{

    TextView textView;
    CircularImageView imageView;
    private CardAdapter adapter;

    public CardVH(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.testoPet);
        imageView=itemView.findViewById(R.id.homeImage);
    }

    public CardVH linkAdapter(CardAdapter adapter){
        this.adapter=adapter;
        return this;
    }
}
