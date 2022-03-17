package com.example.bestie.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestie.MainActivity;
import com.example.bestie.R;
import com.example.bestie.pet.Pet;
import com.example.bestie.pet.PetActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeFragment extends Fragment {

    Activity act = null;
    List<PetCard> pets=new LinkedList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment_home, container, false);

        CircularImageView homePetImage = v.findViewById(R.id.homeImage);
        TextView homePetText = v.findViewById(R.id.testoPet);
        RecyclerView recyclerView=v.findViewById(R.id.home_pets_container);

        recyclerView.setLayoutManager(new LinearLayoutManager(act));
        CardAdapter adapter = new CardAdapter(pets);
        recyclerView.setAdapter(adapter);

        //METODO ONCLICK SU HOME IMAGE
/*        homePetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act, PetActivity.class);
                startActivity(intent);
            }
        });*/

        return v;
    }
}
