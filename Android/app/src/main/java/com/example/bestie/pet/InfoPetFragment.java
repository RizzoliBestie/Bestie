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
import java.util.List;

public class InfoPetFragment extends Fragment {
    ArrayList<InfoPetListItem> infoArrayList= new ArrayList<>();
    ArrayList<InfoPetListItem> infoArrayList2= new ArrayList<>();
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
        ListView infoPetLV2 = (ListView) view.findViewById(R.id.infoPetLV2);
        CalendarView calendarView = view.findViewById(R.id.info_pet_date);

        loadArray(infoArrayList);
        PetListAdapter adapter = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList);
        infoPetLV.setAdapter(adapter);

        loadArray2(infoArrayList2);
        PetListAdapter adapter2 = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList2);
        infoPetLV2.setAdapter(adapter2);

        calendarView.setDate(new Date().getTime());

        return view;
    }





    //METODO PER CARICARE ARRAY PRINCIPALE
    ArrayList<InfoPetListItem> loadArray(ArrayList<InfoPetListItem> arrayList){
        InfoPetListItem specie = new InfoPetListItem("Specie:", null);
        InfoPetListItem razza = new InfoPetListItem("Razza:", null);
        InfoPetListItem peso = new InfoPetListItem("Peso:", null);
        InfoPetListItem pelo = new InfoPetListItem("Pelo:", null);
        InfoPetListItem sterile = new InfoPetListItem("Sterilizzazione:", null);
        InfoPetListItem dataDiNascita = new InfoPetListItem("Data di nascita:", null);

        arrayList.add(specie);
        arrayList.add(razza);
        arrayList.add(peso);
        arrayList.add(pelo);
        arrayList.add(sterile);
        arrayList.add(dataDiNascita);

        return arrayList;
    }

    //METODO PER CARICARE ARRAY SECONDARIO,
    // lo uso escluivamente per una questione grafica in modo che sembrerà una list view continua anche se interrotta da una calendar view
    ArrayList<InfoPetListItem> loadArray2(ArrayList<InfoPetListItem> arrayList) {

        InfoPetListItem hr = new InfoPetListItem(null, null);
        InfoPetListItem sesso = new InfoPetListItem("Sesso:", null);
        arrayList.add(hr);
        arrayList.add(sesso);
        return arrayList;
    }
}
