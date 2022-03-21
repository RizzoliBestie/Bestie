package com.example.bestie.pet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.bestie.API.API_Connection_Bestie;
import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.R;
import com.example.bestie.general.Specie;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InfoPetFragment extends Fragment {
    ArrayList<InfoPetListItem> infoArrayList = new ArrayList<>();
    ArrayList<InfoPetListItem> infoArrayList2 = new ArrayList<>();
    Activity act = null;
    String maleOnUri = "@drawable/male_on";
    String maleUri = "@drawable/male";
    String femaleOnUri = "@drawable/female_on";
    String femaleUri = "@drawable/female";
    int imageResourceM;
    int imageResourceF;
    PetListAdapter adapter;

    String name=null;
    String[] specie;
    String[] razze;
    double weight;
    String furType;
    boolean sterilized;
    boolean isMale;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_pet, container, false);

    //______________INIZIALIZZAZIONE VARIABILI_____________________________________________


        ListView infoPetLV = (ListView) view.findViewById(R.id.infoPetLV);
        ListView infoPetLV2 = (ListView) view.findViewById(R.id.infoPetLV2);
        CalendarView calendarView = view.findViewById(R.id.info_pet_date);
        ImageView male = view.findViewById(R.id.info_pet_male);
        ImageView female = view.findViewById(R.id.info_pet_female);

        calendarView.setDate(new Date().getTime());

        name=getArguments().getString("name");
        weight=getArguments().getDouble("weight");
        furType=getArguments().getString("furType");
        sterilized=getArguments().getBoolean("sterilized");
        isMale=getArguments().getBoolean("isMale");

        loadArray(infoArrayList);
        adapter = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList);
        infoPetLV.setAdapter(adapter);

        loadArray2(infoArrayList2);
        PetListAdapter adapter2 = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList2);
        infoPetLV2.setAdapter(adapter2);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////



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
                setDialog(item);
            }
        });

        return view;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //METODO PER CARICARE ARRAY PRINCIPALE
    ArrayList<InfoPetListItem> loadArray(ArrayList<InfoPetListItem> arrayList) {
        InfoPetListItem specie = new InfoPetListItem("Specie:", null);
        InfoPetListItem razza = new InfoPetListItem("Razza:", null);
        InfoPetListItem peso = new InfoPetListItem("Peso (Kg):", weight);
        InfoPetListItem pelo = new InfoPetListItem("Pelo:", furType);
        InfoPetListItem sterile = new InfoPetListItem("Sterilizzazione:", sterilized);
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
        InfoPetListItem sesso = new InfoPetListItem("Sesso:", isMale);
        sesso.subtitle="";
        arrayList.add(hr);
        arrayList.add(sesso);
        return arrayList;
    }

    //SETTA UN IMMAGINE IN RISALTO
    void switchSize(ImageView bigger, ImageView smaller) {
        bigger.setScaleX((float) 1);
        bigger.setScaleY((float) 1);
        smaller.setScaleX((float) 0.5);
        smaller.setScaleY((float) 0.5);
    }

//------------------------------------------------------------------------------------------------//
    //METODO PER SETTARE IL TIPO DI DIALOG IN BASE ALL'ITEM SELEZIONATO
    void setDialog(InfoPetListItem item) {
        String type = item.getTitle();
        switch (type) {
            case "Specie:":
                alertDialogSpecie(type, item);
                break;
            case "Razza:":
                alertDialogRace(type, item);
                break;
            case "Peso (Kg):":
                alertDialogWeight(type, item);
                break;
            case "Pelo:":
                alertDialogPelo(type, item);
                break;
            case "Sterilizzazione:":
                alertDialogBalls(type, item);
                break;
        }

    }

    //DIALOG ITEM RAZZA
    private AutoCompleteTextView alertDialogRace(String title, InfoPetListItem item) {
        final AutoCompleteTextView autoCompleteTextField = new AutoCompleteTextView(this.getContext());
        AlertDialog dialog = new AlertDialog.Builder(act)
                .setTitle(title)
                .setView(autoCompleteTextField)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String name = String.valueOf(autoCompleteTextField.getText());
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

        return autoCompleteTextField;
    }

    //DIALOG ITEM SPECIE
    private Spinner alertDialogSpecie(String title, InfoPetListItem item) {
        final Spinner spinnerField = new Spinner(this.getContext());
        ArrayAdapter<CharSequence> specieAdapter1 = ArrayAdapter.createFromResource(act, R.array.species, android.R.layout.simple_dropdown_item_1line);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.species_spinner_nothing_selected, act);
        spinnerField.setAdapter(specieAdapter2);

        AlertDialog dialog = new AlertDialog.Builder(act)
                .setTitle(title)
                .setView(spinnerField)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String name = spinnerField.getSelectedItem().toString();
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

        return spinnerField;
    }

    private EditText alertDialogWeight(String title, InfoPetListItem item) {
        final EditText editTextField = new EditText(this.getContext());
        editTextField.setInputType(InputType.TYPE_CLASS_NUMBER);
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

    private Spinner alertDialogPelo(String title, InfoPetListItem item) {
        final Spinner spinnerField = new Spinner(this.getContext());
        ArrayAdapter<CharSequence> specieAdapter1 = ArrayAdapter.createFromResource(act, R.array.peloType, android.R.layout.simple_dropdown_item_1line);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.pelo_spinner_nothing_selected, act);
        spinnerField.setAdapter(specieAdapter2);

        AlertDialog dialog = new AlertDialog.Builder(act)
                .setTitle(title)
                .setView(spinnerField)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String name = spinnerField.getSelectedItem().toString();
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

        return spinnerField;
    }

    private Spinner alertDialogBalls(String title, InfoPetListItem item) {
        final Spinner spinnerField = new Spinner(this.getContext());
        ArrayAdapter<CharSequence> specieAdapter1 = ArrayAdapter.createFromResource(act, R.array.si_no, android.R.layout.simple_dropdown_item_1line);
        specieAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NothingSelectedSpinnerAdapter specieAdapter2 = new NothingSelectedSpinnerAdapter(specieAdapter1, R.layout.default_spinner_nothing_selected, act);
        spinnerField.setAdapter(specieAdapter2);

        AlertDialog dialog = new AlertDialog.Builder(act)
                .setTitle(title)
                .setView(spinnerField)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                String name = spinnerField.getSelectedItem().toString();
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

        return spinnerField;
    }

}
