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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.bestie.API.API_Connection_Bestie;
import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.R;
import com.example.bestie.general.Race;
import com.example.bestie.general.Specie;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    ListView infoPetLV;
    ListView infoPetLV2;
    CalendarView calendarView;
    ImageView male;
    ImageView female;
    PetListAdapter adapter;

    String petName;
    String[] specie;
    String[] razze;
    double weight;
    String furType;
    boolean sterilized;
    boolean isMale;
    int id_race;
    int id_specie;
    int id_pet;
    int id_user;
    Race firstSelectedRace = null;
    String selectedSpecieText = "";
    String specieText = "";
    String raceText = "";

    ImageView editPetButton;
    boolean mainCodeExecuted = false;
    API_Methods_Interface api;
    List<Race> selectedRacesBySpecie = new LinkedList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_pet, container, false);

        //ACQUISISCO VALORI PET E SALVO IN VARIABILI
        petName = getArguments().getString("name");
        weight = getArguments().getDouble("weight");
        furType = getArguments().getString("furType");
        sterilized = getArguments().getBoolean("sterilized");
        isMale = getArguments().getBoolean("isMale");
        id_race = getArguments().getInt("id_race");
        id_pet = getArguments().getInt("id_pet");
        id_user = getArguments().getInt("id_user");

        //SETTO BOTTONE DI MODIFICA
        PetActivity petActivity = (PetActivity) getActivity();
        editPetButton = petActivity.getEditPetButton();

        //ISTANZIO RETROFIT E API INTERFACE
        Retrofit retrofit = ((API_Connection_Bestie) act.getApplication()).getRetrofit();
        api = retrofit.create(API_Methods_Interface.class);

        //IN ASCOLTO SU EDIT BUTTON, SE PREMUTO RICHIESTA PUT
        editPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainCodeExecuted) {
                    Toast.makeText(act, "Modificato!", Toast.LENGTH_SHORT).show();
                    Pet pet = new Pet(id_pet, id_user, id_race, petName, weight, isMale, null, null, null, sterilized, furType);
                    Call<Boolean> updatePet = api.updatePet(pet);
                    updatePet.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(petActivity, furType, Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(petActivity, "Not succesful", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(petActivity, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        //RICHIEDO AL DATABASE LE RAZZE PER POI RICAVARNE ANCHE LE SPECIE
        Call<List<Race>> getAllRace = api.getAllRaces();
        getAllRace.enqueue(new Callback<List<Race>>() {
            @Override
            public void onResponse(Call<List<Race>> call, Response<List<Race>> response) {
                //SALVO LE RAZZE COME STRINGHE IN UN ARRAY
                List<Race> raceList = response.body();
                razze = new String[raceList.size()];
                for (int i = 0; i < raceList.size(); i++) {
                    razze[i] = raceList.get(i).getName();
                    //INDIVIDUO LA RAZZA DEL PET
                    if (id_race == raceList.get(i).getId_race()) {
                        firstSelectedRace = raceList.get(i);
                        id_specie = firstSelectedRace.getId_specie();
                        raceText = firstSelectedRace.getName();
                    }
                    //RICHIEDO TUTTE LE SPECIE
                    if (firstSelectedRace != null) {
                        callSpecies(api, view);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Race>> call, Throwable t) {
                Toast.makeText(act, "Caricamento razze fallito", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                          //FINE ON CREATE//                                                   //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //METODO DI RICHIESTA SPECIE
    void callSpecies(API_Methods_Interface api, View view) {
        Call<List<Specie>> getAllSpecie = api.getAllSpecies();
        getAllSpecie.enqueue(new Callback<List<Specie>>() {
            @Override
            public void onResponse(Call<List<Specie>> call, Response<List<Specie>> response) {
                //SALVO LE SPECIE COME STRINGHE IN UN ARRAY
                List<Specie> specieList = response.body();
                specie = new String[specieList.size()];
                for (int i = 0; i < specieList.size(); i++) {
                    specie[i] = specieList.get(i).getCommon_name();
                    //VERIFICO A QUALE SPECIE APPARTIENE LA RAZZA DE PET
                    if (id_specie == specieList.get(i).getId_specie()) {
                        specieText = specieList.get(i).getCommon_name();
                        //INIZIALIZZO LE VIEW CHE CONTERRANNO I DATI
                        inizialize(view);
                        //ESEGUO IL CODICE DEL MAIN
                        mainCode();
                        mainCodeExecuted = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Specie>> call, Throwable t) {
                Toast.makeText(act, "Caricamento specie fallito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //METODO DI INIZIALIZZAZIONE DELLE VIEW
    void inizialize(View view) {
        if (!mainCodeExecuted) {
            infoPetLV = (ListView) view.findViewById(R.id.infoPetLV);
            infoPetLV2 = (ListView) view.findViewById(R.id.infoPetLV2);
            calendarView = view.findViewById(R.id.info_pet_date);
            male = view.findViewById(R.id.info_pet_male);
            female = view.findViewById(R.id.info_pet_female);

            calendarView.setDate(new Date().getTime());
            //CARICO GLI ARRAY CONTENTI I DATI DEL PET
            loadArraies();
        }
    }

    //METODO PER CARICARE ARRAY PRINCIPALE
    ArrayList<InfoPetListItem> loadArray(ArrayList<InfoPetListItem> arrayList) {
        InfoPetListItem specie = new InfoPetListItem("Specie:", specieText);
        InfoPetListItem razza = new InfoPetListItem("Razza:", raceText);
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

    //METODO PER CARICARE ARRAY SECONDARIO
    // lo uso escluivamente per una questione grafica in modo che sembrerà una list view continua anche se interrotta da una calendar view
    ArrayList<InfoPetListItem> loadArray2(ArrayList<InfoPetListItem> arrayList) {

        InfoPetListItem hr = new InfoPetListItem(null, null);
        InfoPetListItem sesso = new InfoPetListItem("Sesso:", isMale);
        sesso.subtitle = "";
        arrayList.add(hr);
        arrayList.add(sesso);
        return arrayList;
    }

    //METODO PER CARICARE ENTRAMBI GLI ARRAY CONTEMPORANEAMENTE
    public void loadArraies() {
        loadArray(infoArrayList);
        adapter = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList);
        infoPetLV.setAdapter(adapter);

        loadArray2(infoArrayList2);
        PetListAdapter adapter2 = new PetListAdapter(act, R.layout.info_pet_list_item, infoArrayList2);
        infoPetLV2.setAdapter(adapter2);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                  //RACCHIUDO IL CODICE IN UNA FUNZIONE PER INSERIRLO NELLA CALL DI RETROFIT                     //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void mainCode() {
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
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


    //METODI ALERT DIALOG//
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
                alertDialogSterilized(type, item);
                break;
        }

    }

    //DIALOG ITEM RAZZA
    private void alertDialogRace(String title, InfoPetListItem item) {
        mainCallCode(title, item);
        for (int i = 0; i < selectedRacesBySpecie.size(); i++) {
            if (item.subtitle.equals(selectedRacesBySpecie.get(i).getName()))
                setId_race(selectedRacesBySpecie.get(i).getId_race());
        }

    }

    //DIALOG ITEM SPECIE
    private Spinner alertDialogSpecie(String title, InfoPetListItem item) {
        final Spinner spinnerField = new Spinner(this.getContext());
        ArrayAdapter<CharSequence> specieAdapter1 = new ArrayAdapter<>(act, R.layout.support_simple_spinner_dropdown_item, specie);
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
                                selectedSpecieText = spinnerField.getSelectedItem().toString();
                                item.setSubtitle(selectedSpecieText);
                                adapter.notifyDataSetChanged();
                                if (!selectedSpecieText.equals("")) {
                                    getStringRacesBySelectedSpecie(selectedSpecieText);
                                }
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
                                setWeight(new Double(editTextField.getText().toString()));
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
                                setFurType(name);
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

    private Spinner alertDialogSterilized(String title, InfoPetListItem item) {
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
                                if (name.equals("Sì"))
                                    setSterilized(true);
                                else
                                    setSterilized(false);
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

    void getStringRacesBySelectedSpecie(String specieString) {
        Call<Specie> getSpecieByName = api.getSpecieByName(specieString);
        getSpecieByName.enqueue(new Callback<Specie>() {
            @Override
            public void onResponse(Call<Specie> call, Response<Specie> response) {
                Specie selectedSpecie = response.body();
                int id_selectedSpecie = selectedSpecie.getId_specie();
                getListRacesBySpecie(id_selectedSpecie);
            }

            @Override
            public void onFailure(Call<Specie> call, Throwable t) {
                Toast.makeText(act, "Specie selezionata non trovata", Toast.LENGTH_SHORT).show();
            }
        });
    }

    String[] getListRacesBySpecie(int id_selectedSpecie) {
        Call<List<Race>> getRaceBySpecie = api.getRaceBySpecie(id_selectedSpecie);
        getRaceBySpecie.enqueue(new Callback<List<Race>>() {
            @Override
            public void onResponse(Call<List<Race>> call, Response<List<Race>> response) {
                selectedRacesBySpecie = response.body();
                setSelectedRacesBySpecie(selectedRacesBySpecie);
                String[] razze_prova = new String[selectedRacesBySpecie.size()];
                for (int i = 0; i < selectedRacesBySpecie.size(); i++) {
                    razze_prova[i] = selectedRacesBySpecie.get(i).getName();
                }
                setRazze(razze_prova);
            }

            @Override
            public void onFailure(Call<List<Race>> call, Throwable t) {

            }
        });
        return razze;
    }

    public Spinner mainCallCode(String title, InfoPetListItem item) {
        final Spinner spinnerField = new Spinner(this.getContext());
        ArrayAdapter<CharSequence> specieAdapter1 = new ArrayAdapter<>(act, R.layout.support_simple_spinner_dropdown_item, razze);
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

    public void setRazze(String[] razze) {
        this.razze = razze;
    }

    public void setSelectedRacesBySpecie(List<Race> selectedRacesBySpecie) {
        this.selectedRacesBySpecie = selectedRacesBySpecie;
    }

    public void setId_race(int id_race) {
        this.id_race = id_race;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setFurType(String furType) {
        this.furType = furType;
    }

    public void setSterilized(boolean sterilized) {
        this.sterilized = sterilized;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setId_specie(int id_specie) {
        this.id_specie = id_specie;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    //METODI GENERAL PURPOSE//
//------------------------------------------------------------------------------------------------//
    //SETTA UN IMMAGINE IN RISALTO
    void switchSize(ImageView bigger, ImageView smaller) {
        bigger.setScaleX((float) 1);
        bigger.setScaleY((float) 1);
        smaller.setScaleX((float) 0.5);
        smaller.setScaleY((float) 0.5);
    }
}
