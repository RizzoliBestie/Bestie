package com.example.bestie.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestie.R;
import com.example.bestie.pet.PetActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import kotlin.jvm.internal.Lambda;

public class CardAdapter extends RecyclerView.Adapter<CardVH> {

    List<PetCard> items;
    Intent petActivityIntent;
    Context ctx;

    public CardAdapter(List<PetCard> items, Context ctx) {
        this.items = items;
        this.ctx=ctx;
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
        //METODO ONCLICK SU HOME IMAGE
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPetActivityIntent( new Intent(ctx, PetActivity.class));
                ctx.startActivity(petActivityIntent);
            }
        });
        //holder.imageView.setImageURI(items.get(position).uri);
    }

    public void setPetActivityIntent(Intent petActivityIntent) {
        this.petActivityIntent = petActivityIntent;
    }

    public Intent getPetActivityIntent() {
        return petActivityIntent;
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
