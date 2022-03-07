package com.example.bestie.curiosity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bestie.OnSwipeTouchListener;
import com.example.bestie.R;
import com.example.bestie.animal.AnimalArrayAdapter;
import com.example.bestie.animal.AnimalSection;
import com.example.bestie.animal.AnimalSectionSpinnerAdapter;
import com.example.bestie.animal.AnimalWiki;

import java.util.ArrayList;
import java.util.List;

public class CuriosityFragment extends Fragment {

    RelativeLayout curiosityFragmentRelativeLayout = null;
    ListView animalWikiListView = null;
    ImageView sectionMenuImageView = null;
    TextView sectionTextView = null;
    EditText searchEditText = null;
    Spinner sectionMenuSpinner = null;
    View topBackground = null;

    GradientDrawable backgroundGradient = null;
    StateListDrawable backgroundSpinner = null;

    Activity act = null;

    AnimalSectionSpinnerAdapter spinnerAdapter;

    int sectionIndex = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_curiosity, container, false);

        curiosityFragmentRelativeLayout = v.findViewById(R.id.curiosityFragmentRelativeLayout);
        animalWikiListView = v.findViewById(R.id.animalWikiListView);
        sectionMenuImageView = v.findViewById(R.id.sectionMenuImageView);
        sectionTextView = v.findViewById(R.id.sectionTextView);
        searchEditText = v.findViewById(R.id.searchEditText);
        sectionMenuSpinner = v.findViewById(R.id.sectionMenuSpinner);
        topBackground = v.findViewById(R.id.top_background);

        backgroundGradient = (GradientDrawable) searchEditText.getBackground();

        backgroundSpinner = (StateListDrawable) sectionMenuSpinner.getBackground();
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) backgroundSpinner.getConstantState();
        Drawable[] children = drawableContainerState.getChildren();
        LayerDrawable backgroundSpinnerItem = (LayerDrawable) children[0];
        GradientDrawable backgroundSpinnerDrawable = (GradientDrawable) backgroundSpinnerItem.getDrawable(0);

        //Setup della TextView che indica la sezione
        String [] SectionString = new String[] {"PETS", "FARM", "WILD"};

        //final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(act, R.array.curiosity_sections, android.R.layout.simple_spinner_item);
        //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //sectionMenuSpinner.setAdapter(spinnerAdapter);

        spinnerAdapter = new AnimalSectionSpinnerAdapter(getContext(), R.layout.row_section, R.id.sectionNameTextView, getSpinnerData());
        sectionMenuSpinner.setAdapter(spinnerAdapter);

        //Input degli animali qui (dal metodo)
        List<AnimalWiki> animals = getAnimalData();

        //Divisione della lista degli animali in sezioni
        List<AnimalWiki> animalsWild = new ArrayList<AnimalWiki>();
        for(int wild=0; wild<animals.size(); wild++){
            if(animals.get(wild).section.getSectionName().equals("WILD")){
                animalsWild.add(animals.get(wild));
            }
        }

        List<AnimalWiki> animalsFarm = new ArrayList<AnimalWiki>();
        for(int farm=0; farm<animals.size(); farm++){
            if(animals.get(farm).section.getSectionName().equals("FARM")){
                animalsFarm.add(animals.get(farm));
            }
        }

        List<AnimalWiki> animalsPets = new ArrayList<AnimalWiki>();
        for(int pets=0; pets<animals.size(); pets++){
            if(animals.get(pets).section.getSectionName().equals("PETS")){
                animalsPets.add(animals.get(pets));
            }
        }
        //Crea l'arrayadapter, fa riferimento a diverse categorie di animali
        AnimalArrayAdapter animalAdapterWild = new AnimalArrayAdapter(act, R.layout.row_animal, animalsWild);
        AnimalArrayAdapter animalAdapterFarm = new AnimalArrayAdapter(act, R.layout.row_animal, animalsFarm);
        AnimalArrayAdapter animalAdapterPets = new AnimalArrayAdapter(act, R.layout.row_animal, animalsPets);
        AnimalArrayAdapter animalAdapter = new AnimalArrayAdapter(act, R.layout.row_animal, animals);


        sectionMenuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AnimalSection animalSect = (AnimalSection) spinnerAdapter.getItem(i);
                String section = animalSect.getSectionName();

                //Log.v("CuriosityFragment", section);
                //Toast.makeText(getApplicationContext(), spinnerAdapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
                sectionTextView.setText(section);

                //Aggiorna l'indice sezione
                sectionIndex = sectionMenuSpinner.getSelectedItemPosition();

                //Imposta quale categoria di animali deve essere visualizzata

                //String index = sectionMenuSpinner.getSelectedItem().toString();
                switch (section){
                    case "WILD":
                        animalWikiListView.setAdapter(animalAdapterWild);
                        topBackground.setBackgroundResource(R.color.settore_wild);
                        curiosityFragmentRelativeLayout.setBackgroundResource(R.color.settore_wild_lista);
                        sectionTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_wild_testo_principale));
                        backgroundGradient.setStroke(6, ContextCompat.getColor(getContext(), R.color.settore_wild_jolly));
                        backgroundSpinnerDrawable.setColor(ContextCompat.getColor(getContext(), R.color.settore_wild_jolly));
                        break;
                    case "FARM":
                        animalWikiListView.setAdapter(animalAdapterFarm);
                        topBackground.setBackgroundResource(R.color.settore_farm);
                        curiosityFragmentRelativeLayout.setBackgroundResource(R.color.settore_farm_lista);
                        sectionTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_farm_testo_principale));
                        backgroundGradient.setStroke(6, ContextCompat.getColor(getContext(), R.color.settore_farm_jolly));
                        backgroundSpinnerDrawable.setColor(ContextCompat.getColor(getContext(), R.color.settore_farm_jolly));
                        break;
                    case "PETS":
                        animalWikiListView.setAdapter(animalAdapterPets);
                        topBackground.setBackgroundResource(R.color.settore_pets);
                        curiosityFragmentRelativeLayout.setBackgroundResource(R.color.settore_pets_lista);
                        sectionTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.settore_pets_testo_principale));
                        backgroundGradient.setStroke(6, ContextCompat.getColor(getContext(), R.color.settore_pets_jolly));
                        backgroundSpinnerDrawable.setColor(ContextCompat.getColor(getContext(), R.color.settore_pets_jolly));
                        break;
                    default:
                        animalWikiListView.setAdapter(animalAdapter);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //AnimalArrayAdapter animalSearchAdapter = new AnimalArrayAdapter(this, R.layout.row_animal, animalsSearchList);
        //animalWikiListView.setAdapter(animalSearchAdapter);

        //Abilita filtro per i contenuti della ListView
        animalWikiListView.setTextFilterEnabled(true);

        animalWikiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AnimalArrayAdapter animalRefAdapter = (AnimalArrayAdapter) animalWikiListView.getAdapter();
                AnimalWiki p = animalRefAdapter.getItem(pos);
                //Toast.makeText(CuriosityActivity.this, p.name + ", " + p.race + ", " + p.specie, Toast.LENGTH_SHORT).show();

                Intent infoIntent = new Intent(act, AnimalInfoActivity.class);
                infoIntent.putExtra("name", p.name);
                infoIntent.putExtra("race", p.race);
                infoIntent.putExtra("specie", p.specie);
                infoIntent.putExtra("image_url", p.image_url);
                infoIntent.putExtra("description", p.description);
                infoIntent.putExtra("section", p.section.getSectionName());
                startActivity(infoIntent);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AnimalArrayAdapter animalRefAdapter = (AnimalArrayAdapter) animalWikiListView.getAdapter();
                //Al cambiamento dell'editText aggiorna la lista
                animalRefAdapter.getFilter().filter(charSequence.toString());
                //Ogni cambiamento aggiorna la lista attraverso questi metodi(in questo caso aggiorna soprattutto le immagini)
                animalRefAdapter.clear();
                //animalAdapter.notifyDataSetInvalidated();
                animalRefAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //Click della tendina (Hamburger Menu)
        /*sectionMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CuriosityActivity.this, "Tendina premuta", Toast.LENGTH_SHORT).show();
            }
        }); */


        topBackground.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                if(sectionIndex > 0){
                    sectionIndex--;
                    sectionMenuSpinner.setSelection(sectionIndex);
                }
                Log.v("CuriosityFragment", "Right " + sectionIndex);
            }
            public void onSwipeLeft() {
                if(sectionIndex < sectionMenuSpinner.getAdapter().getCount() - 1){
                    sectionIndex++;
                    sectionMenuSpinner.setSelection(sectionIndex);
                }
                Log.v("CuriosityFragment", "Left " + sectionIndex);
            }
            public void onSwipeBottom() {

            }
        });


        return v;
    }

    //Input sezioni nello spinner
    public static List<AnimalSection> getSpinnerData(){
        List<AnimalSection> sectionList = new ArrayList<>();
        AnimalSection Wild = new AnimalSection();
        Wild.setSectionName("WILD");
        Wild.setIcon(R.drawable.ic_baseline_pets_24);
        sectionList.add(Wild);
        AnimalSection Farm = new AnimalSection();
        Farm.setSectionName("FARM");
        Farm.setIcon(R.drawable.ic_baseline_pets_24);
        sectionList.add(Farm);
        AnimalSection Pets = new AnimalSection();
        Pets.setSectionName("PETS");
        Pets.setIcon(R.drawable.ic_baseline_pets_24);
        sectionList.add(Pets);

        return sectionList;
    }

    //Input animali
    public List<AnimalWiki> getAnimalData(){
        List<AnimalWiki> animals = new ArrayList<AnimalWiki>();
        animals.add(new AnimalWiki("Volpe", "Fennec", "Vulpes Zerda", "WILD", "https://www.parmadaily.it/wp-content/uploads/2016/09/fennec.jpg"));
        animals.add(new AnimalWiki("Nittereute", "Cane Procione", "Nyctereutes procyonoides", "WILD", "https://upload.wikimedia.org/wikipedia/commons/8/85/Der_Marderhund%2C_Tanuki_oder_Enok_%28Nyctereutes_procyonoides%29%2C_bitte_nicht_zu_verwechseln_mit_einem_Waschb%C3%A4r%2C_hier_im_Wisentgehege_in_Springe_%28Kleiner_Deister%29.jpg"));
        animals.add(new AnimalWiki("Volpe", "Volpe Rossa", "Vulpes Vulpes", "WILD","https://www.giornaletrentino.it/image/contentid/policy:1.2991162:1631209711/image%20(3).jpg?f=3x2&w=299&$p$f$w=c5a262c"));
        animals.add(new AnimalWiki("Urocioni", "Volpe Grigia", "Urocyon", "WILD","https://static.kodami.it/wp-content/uploads/sites/31/2021/05/iStock-1264712034-638x425.jpg"));
        animals.add(new AnimalWiki("Volpe", "Volpe Americana", "Vulpes Velox", "WILD","https://upload.wikimedia.org/wikipedia/commons/2/2a/Vulpes_velox.jpg"));
        animals.add(new AnimalWiki("Otocione", "Otycion", "Otycion megalotis", "WILD","https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Bandit_%2835877900754%29.jpg/220px-Bandit_%2835877900754%29.jpg"));
        animals.add(new AnimalWiki("Gallina", "Gallus", "Gallus gallus domesticus", "FARM","https://www.tuttosullegalline.it/newsite/wp-content/uploads/2019/01/gallina-Brucie-4.jpg"));
        animals.add(new AnimalWiki("Toro", "Mucca", "Bos Taurus", "FARM", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo3nFYjIbXjGoZQ5umhVFLdBLEEAHDP85yAw&usqp=CAU"));
        animals.add(new AnimalWiki("Pecora", "Ovis", "Ovis aries", "FARM", "https://www.cibo360.it/images/alimentazione/cibi/pecora.jpg"));
        animals.add(new AnimalWiki("Cane", "Canis lupus familiaris", "Siberian Husky", "PETS","https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Le%C3%AFko_au_bois_de_la_Cambre.jpg/330px-Le%C3%AFko_au_bois_de_la_Cambre.jpg"));
        animals.add(new AnimalWiki("Cane", "Canis lupus familiaris", "Pastore Tedesco", "PETS","http://www.difossombrone.it/images/anatomia/difettitesta.jpg"));
        animals.add(new AnimalWiki("Gatto", "Felis catus", "Persiano", "PETS","https://upload.wikimedia.org/wikipedia/it/thumb/3/3e/Prisca.jpg/390px-Prisca.jpg"));

        return animals;
    }
}