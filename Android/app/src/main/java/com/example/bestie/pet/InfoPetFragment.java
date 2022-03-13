package com.example.bestie.pet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.bestie.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfoPetFragment extends Fragment {
    ArrayList<InfoPetListItem> infoArrayList = new ArrayList<>();
    ArrayList<InfoPetListItem> infoArrayList2 = new ArrayList<>();
    Activity act = null;
    boolean isMale;
    String maleOnUri = "@drawable/male_on";
    String maleUri = "@drawable/male";
    String femaleOnUri = "@drawable/female_on";
    String femaleUri = "@drawable/female";
    int imageResourceM;
    int imageResourceF;
    PetListAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_pet, container, false);

        isMale = false;

        ListView infoPetLV = (ListView) view.findViewById(R.id.infoPetLV);
        ListView infoPetLV2 = (ListView) view.findViewById(R.id.infoPetLV2);
        CalendarView calendarView = view.findViewById(R.id.info_pet_date);
        ImageView male = view.findViewById(R.id.info_pet_male);
        ImageView female = view.findViewById(R.id.info_pet_female);

        loadArray(infoArrayList);
        adapter = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList);
        infoPetLV.setAdapter(adapter);

        loadArray2(infoArrayList2);
        PetListAdapter adapter2 = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList2);
        infoPetLV2.setAdapter(adapter2);

        calendarView.setDate(new Date().getTime());

        //SETTO IMMAGINI SE MASCHIO O FEMMINA
        if (isMale) {
            imageResourceM = getResources().getIdentifier(maleOnUri, null, act.getPackageName());
            Drawable drawableM = getResources().getDrawable(imageResourceM, null);
            male.setImageDrawable(drawableM);

            imageResourceF = getResources().getIdentifier(femaleUri, null, act.getPackageName());
            Drawable drawableF = getResources().getDrawable(imageResourceF, null);
            female.setImageDrawable(drawableF);
            switchSize(male, female);
        } else {
            imageResourceM = getResources().getIdentifier(maleUri, null, act.getPackageName());
            Drawable drawableM = getResources().getDrawable(imageResourceM, null);
            male.setImageDrawable(drawableM);

            imageResourceF = getResources().getIdentifier(femaleOnUri, null, act.getPackageName());
            Drawable drawableF = getResources().getDrawable(imageResourceF, null);
            female.setImageDrawable(drawableF);
            switchSize(female, male);
        }

        //SETTO METODI ON CLICK PER CAMBIARE SESSO (???)
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMale) {
                    imageResourceM = getResources().getIdentifier(maleOnUri, null, act.getPackageName());
                    Drawable drawableM = getResources().getDrawable(imageResourceM, null);
                    male.setImageDrawable(drawableM);

                    imageResourceF = getResources().getIdentifier(femaleUri, null, act.getPackageName());
                    Drawable drawableF = getResources().getDrawable(imageResourceF, null);
                    female.setImageDrawable(drawableF);

                    isMale = true;
                    switchSize(male, female);
                }
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMale) {
                    imageResourceM = getResources().getIdentifier(maleUri, null, act.getPackageName());
                    Drawable drawableM = getResources().getDrawable(imageResourceM, null);
                    male.setImageDrawable(drawableM);

                    imageResourceF = getResources().getIdentifier(femaleOnUri, null, act.getPackageName());
                    Drawable drawableF = getResources().getDrawable(imageResourceF, null);
                    female.setImageDrawable(drawableF);

                    isMale = false;
                    switchSize(female, male);
                }
            }
        });

        infoPetLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InfoPetListItem item = (InfoPetListItem) adapterView.getItemAtPosition(i);
                String title = item.getTitle();
                alertDialog(title, item);
                // onClick(dialogInterface, );
            }
        });

        return view;
    }


    //METODO PER CARICARE ARRAY PRINCIPALE
    ArrayList<InfoPetListItem> loadArray(ArrayList<InfoPetListItem> arrayList) {
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

    private EditText alertDialog(String title, InfoPetListItem item) {
        final EditText editTextField = new EditText(this.getContext());
        AlertDialog dialog = new AlertDialog.Builder(act)
                .setTitle(title)
                .setView(editTextField)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String name = String.valueOf(editTextField.getText());
                                item.setSubtitle(name);
                                adapter.notifyDataSetChanged();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                })
                .setNegativeButton("ANNULLA", null)
                .create();
        dialog.show();

        return editTextField;
    }

    void switchSize(ImageView bigger, ImageView smaller) {
        bigger.setScaleX((float) 1);
        bigger.setScaleY((float) 1);
        smaller.setScaleX((float) 0.5);
        smaller.setScaleY((float) 0.5);
    }
}
