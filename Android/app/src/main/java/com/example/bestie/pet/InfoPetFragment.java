package com.example.bestie.pet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bestie.R;

import java.util.ArrayList;
import java.util.Date;

public class InfoPetFragment extends Fragment {
    ArrayList<InfoPetListItem> infoArrayList= new ArrayList<>();
    Activity act=null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_pet, container, false);

        ListView infoPetLV = (ListView) view.findViewById(R.id.infoPetLV);
        CalendarView calendarView = view.findViewById(R.id.info_pet_date);
        loadArray(infoArrayList);
        PetListAdapter adapter = new PetListAdapter(act, R.layout.info_pet_list_item, R.id.info_pet_title, infoArrayList);
        infoPetLV.setAdapter(adapter);

        calendarView.setDate(new Date().getTime());

        return view;
    }





    //METODO PER CARICARE ARRAY
    ArrayList<InfoPetListItem> loadArray(ArrayList<InfoPetListItem> arrayList){
        InfoPetListItem specie = new InfoPetListItem("Specie", null);
        InfoPetListItem razza = new InfoPetListItem("Razza", null);
        InfoPetListItem peso = new InfoPetListItem("Peso", null);
        InfoPetListItem pelo = new InfoPetListItem("Pelo", null);
        InfoPetListItem sterile = new InfoPetListItem("Sterilizzazione", null);
        InfoPetListItem dataDiNascita = new InfoPetListItem("Data di nascita", null);

        arrayList.add(specie);
        arrayList.add(razza);
        arrayList.add(peso);
        arrayList.add(pelo);
        arrayList.add(sterile);
        arrayList.add(dataDiNascita);

        return arrayList;
    }
}
