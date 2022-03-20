package com.example.bestie.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestie.API.API_Connection_Bestie;
import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.R;
import com.example.bestie.pet.Pet;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    Activity act = null;
    List<Pet> pets = new LinkedList<>();
    List<PetCard> petCards = new LinkedList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        CircularImageView homePetImage = v.findViewById(R.id.homeImage);
        TextView homePetText = v.findViewById(R.id.testoPet);


        Retrofit retrofit = ((API_Connection_Bestie) act.getApplication()).getRetrofit();
        API_Methods_Interface api = retrofit.create(API_Methods_Interface.class);

        Toast.makeText(act, "Starting", Toast.LENGTH_SHORT).show();

        Call<List<Pet>> getPets = api.getAllPets();
        getPets.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(act, "Response Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(act, "Success", Toast.LENGTH_SHORT).show();
                pets = response.body();
                for (int i = 0; i < pets.size(); i++) {
                    Pet pet = pets.get(i);
                    String title = pet.getName();
                    petCards.add(new PetCard(title, R.drawable.doggo));
                }
                RecyclerView recyclerView = v.findViewById(R.id.home_pets_container);
                recyclerView.setLayoutManager(new LinearLayoutManager(act));
                CardAdapter adapter = new CardAdapter(petCards);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                if(t instanceof IOException)
                    Toast.makeText(act, "Connection error", Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(act, "Conversion Error!!", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });



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
