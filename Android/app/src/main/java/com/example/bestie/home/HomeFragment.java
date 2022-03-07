package com.example.bestie.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bestie.MainActivity;
import com.example.bestie.R;
import com.example.bestie.pet.PetActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

public class HomeFragment extends Fragment {

    Activity act = null;

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

        //METODO ONCLICK SU HOME IMAGE
        homePetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act, PetActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
