package com.example.bestie.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.example.bestie.pet.NewPetActivity;
import com.example.bestie.pet.Pet;
import com.example.bestie.pet.PetActivity;
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
    CircularImageView homePetImage;
    TextView first_pet_add;
    CardAdapter adapter;
    int id_user;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        homePetImage=v.findViewById(R.id.homeImage);
        first_pet_add=v.findViewById(R.id.first_pet_add);

        SharedPreferences pref = act.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        id_user= pref.getInt("id_user", 0);

        Retrofit retrofit = ((API_Connection_Bestie) act.getApplication()).getRetrofit();
        API_Methods_Interface api = retrofit.create(API_Methods_Interface.class);

        Call<List<Pet>> getPets = api.getPetsByUserId(id_user);
        getPets.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(act, "Response Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(act, "Success", Toast.LENGTH_SHORT).show();
                pets = response.body();
                if (pets.size()==0){
                    Toast.makeText(act, "Benvenuto!", Toast.LENGTH_SHORT).show();
                    first_pet_add.setText("Clicca qui per creare il tuo primo pet!");
                    first_pet_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent addFirstPetIntent = new Intent(act, NewPetActivity.class);
                            startActivity(addFirstPetIntent);
                        }
                    });
                }
                else {
                    for (int i = 0; i < pets.size(); i++) {
                        Pet pet = pets.get(i);
                        String title = pet.getName();
                    /*String uriString=pet.getUri_image();
                    if(uriString!=null){
                    Uri uri = Uri.parse(uriString);*/
                        petCards.add(new PetCard(pet, R.drawable.doggo));
                    }
                    //else petCards.add(new PetCard(title, R.drawable.doggo));
                    //}
                    RecyclerView recyclerView = v.findViewById(R.id.home_pets_container);
                    recyclerView.setLayoutManager(new LinearLayoutManager(act));
                    adapter = new CardAdapter(petCards, act);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                if (t instanceof IOException)
                    Toast.makeText(act, "Connection error", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(act, "Conversion Error!!", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });
        return v;
    }
}
