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

import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.OnSwipeTouchListener;
import com.example.bestie.R;
import com.example.bestie.animal.AnimalArrayAdapter;
import com.example.bestie.animal.AnimalSection;
import com.example.bestie.animal.AnimalSectionSpinnerAdapter;
import com.example.bestie.animal.AnimalWiki;
import com.example.bestie.animal.Race;

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
    //API_Methods_Interface api = null;

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
            if(animals.get(wild).getSection().equals("WILD")){
                animalsWild.add(animals.get(wild));
            }
        }

        List<AnimalWiki> animalsFarm = new ArrayList<AnimalWiki>();
        for(int farm=0; farm<animals.size(); farm++){
            if(animals.get(farm).getSection().equals("FARM")){
                animalsFarm.add(animals.get(farm));
            }
        }

        List<AnimalWiki> animalsPets = new ArrayList<AnimalWiki>();
        for(int pets=0; pets<animals.size(); pets++){
            if(animals.get(pets).getSection().equals("PETS")){
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
                infoIntent.putExtra("name", p.getName());
                infoIntent.putExtra("race", p.getRace().getName());
                infoIntent.putExtra("information", p.getRace().getInformation());
                infoIntent.putExtra("size", p.getRace().getSize());
                infoIntent.putExtra("fur_type", p.getRace().getFur_type());
                infoIntent.putExtra("specie", p.getSpecie());
                infoIntent.putExtra("image_url", p.getImageUrl());
                infoIntent.putExtra("section", p.getSection());
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
        //Input manuale senza database
        List<AnimalWiki> animals = new ArrayList<AnimalWiki>();
        animals.add(new AnimalWiki("Volpe", new Race("Fennec", "Il Fennec è il più piccolo canide del mondo: il suo peso si aggira intorno a 1 kg. In media è alto 20 cm e lungo circa 30 cm, la coda è lunga 25 cm.\n" +
                "\n" + "Le lunghe orecchie, che possono raggiungere 15 cm, servono per disperdere il calore e gli offrono un ottimo udito. Il pelo respinge la luce del sole di giorno grazie al colore e conserva calore durante la notte; inoltre gli permette di mimetizzarsi perfettamente nel deserto. Le zampe nella parte inferiore sono coperte da un pelo molto spesso che le protegge dal calore della sabbia.","20/30 cm", "Color Sabbia"), "Vulpes Zerda", "WILD", "https://www.parmadaily.it/wp-content/uploads/2016/09/fennec.jpg"));
        animals.add(new AnimalWiki("Nittereute", new Race("Cane Procione", "Il nittereute, detto anche cane procione o cane viverrino, è una volpe indigena dell'Asia orientale, in particolare le valli fluviali e i margini delle foreste nelle regioni dell'Amur e dell'Ussuri della Siberia orientale, il Giappone, la Manciuria, e l'Indocina.\n" +
                "\n" +
                "Il nittereute è principalmente un animale notturno e opportunista, occupando una nicchia ecologica simile a quello del tasso e della volpe rossa, cibandosi di qualsiasi fonte di cibo disponibile, sebbene possa diventare un cacciatore specializzato di anfibi nelle zone umide.\n" +
                "\n" +
                "Viene classificato dalla IUCN tra le specie a rischio minimo, dato che è comune nel suo areale indigeno asiatico e ampiamente diffuso in Europa dopo introduzioni artificiali. Esemplari della sottospecie siberiana N. p. ussuriensis sono stati segnalati in Italia nord-orientale sin dalla seconda metà degli anni ottanta, sebbene in bassi numeri. Viene generalmente considerato un animale nocivo in Europa, poiché minaccia le popolazioni di uccelli terricoli e anfibi, e porta varie malattie pericolose come la rabbia.", "50/68 cm", "Color Grigio"), "Nyctereutes procyonoides", "WILD", "https://upload.wikimedia.org/wikipedia/commons/8/85/Der_Marderhund%2C_Tanuki_oder_Enok_%28Nyctereutes_procyonoides%29%2C_bitte_nicht_zu_verwechseln_mit_einem_Waschb%C3%A4r%2C_hier_im_Wisentgehege_in_Springe_%28Kleiner_Deister%29.jpg"));
        animals.add(new AnimalWiki("Volpe", new Race("Volpe Rossa", "La volpe rossa, o semplicemente volpe (Vulpes vulpes Linnaeus, 1758), è la più grande delle volpi propriamente dette e il carnivoro con l'areale più vasto, essendo presente in tutto l'emisfero boreale, dal circolo polare artico al Nord Africa, il Nord America e l'Eurasia. Il suo areale si è espanso insieme a quello umano, essendo stata introdotta in Australia, dove viene considerata nociva per i marsupiali e gli uccelli indigeni. A causa dei suoi danni ecologici in quest'ultimo continente, la specie è considerata una tra le peggiori specie invasive.\n" +
                "\n" +
                "La volpe rossa vive solitamente in coppia o in piccoli gruppi rivolti a una coppia riproduttiva e la sua prole o da un maschio con varie femmine imparentate. I cuccioli cresciuti tendono a rimanere con i genitori per assisterli nella cura di nuovi piccoli. Si ciba prevalentemente di piccoli roditori, ma caccia anche conigli, uccelli terricoli, rettili, invertebrati e giovani ungulati. Ogni tanto si nutre anche di frutta e vegetali. Sebbene tenda a uccidere i predatori più piccoli, incluse altre specie di volpe, è vulnerabile agli attacchi di predatori più grossi come lupi, coyote, sciacalli e vari felini di grossa (come i leopardi) o media taglia (come le linci).\n" +
                "\n" +
                "La specie ha una lunga storia d'associazione con gli umani, essendo stata cacciata attivamente come animale nocivo o da pelliccia per molti secoli, ed è protagonista di tante fiabe e leggende. Dato il suo vasto areale e la popolazione numerosa, è fra gli animali più importanti nel commercio delle pellicce.", "75/140 cm", "Colori Rosso/Bianco/Nero"), "Vulpes Vulpes", "WILD","https://www.giornaletrentino.it/image/contentid/policy:1.2991162:1631209711/image%20(3).jpg?f=3x2&w=299&$p$f$w=c5a262c"));
        animals.add(new AnimalWiki("Urocioni", new Race("Volpe Grigia"), "Urocyon", "WILD","https://static.kodami.it/wp-content/uploads/sites/31/2021/05/iStock-1264712034-638x425.jpg"));
        animals.add(new AnimalWiki("Volpe", new Race("Volpe Americana"), "Vulpes Velox", "WILD","https://upload.wikimedia.org/wikipedia/commons/2/2a/Vulpes_velox.jpg"));
        animals.add(new AnimalWiki("Otocione", new Race("Otycion"), "Otycion megalotis", "WILD","https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Bandit_%2835877900754%29.jpg/220px-Bandit_%2835877900754%29.jpg"));
        animals.add(new AnimalWiki("Gallina", new Race("Gallus", "Il pollo (Gallus gallus domesticus o Gallus sinae) (Linnaeus, 1758) è un uccello domestico derivante da varie specie selvatiche di origini indiane. La sua presenza è documentata dal 4000 a.C. nella piana dell'Indo, da cui (attraverso la Persia) è giunto in Grecia e quindi in Europa.\n" +
                "\n" +
                "Darwin attribuì la paternità solo al Gallus gallus bankiva per varie motivazioni, tra cui la somiglianza del colore del piumaggio con quello di alcune razze domestiche, la variabilità delle sottospecie di Gallus gallus a seconda del luogo di diffusione e la fecondità delle uova derivanti dall'accoppiamento con i polli domestici.\n" +
                "\n" +
                "Questa posizione è notevolmente mutata nel corso del ventesimo secolo a seguito di esperienze di ibridazione effettuate con le altre specie selvatiche. Oggi si può affermare che alla creazione del pollo domestico hanno contribuito varie specie. I polli sono sempre stati allevati per moltissimi scopi: carne, uova, piume, compagnia, gare di combattimento tra galli, motivazioni religiose, sportive od ornamentali.", "41–46 cm", "Piume"), "Gallus gallus domesticus", "FARM","https://www.tuttosullegalline.it/newsite/wp-content/uploads/2019/01/gallina-Brucie-4.jpg"));
        animals.add(new AnimalWiki("Toro", new Race("Mucca"), "Bos Taurus", "FARM", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo3nFYjIbXjGoZQ5umhVFLdBLEEAHDP85yAw&usqp=CAU"));
        animals.add(new AnimalWiki("Pecora", new Race("Ovis"), "Ovis aries", "FARM", "https://www.cibo360.it/images/alimentazione/cibi/pecora.jpg"));
        animals.add(new AnimalWiki("Cane", new Race("Siberian Husky"), "Canis lupus familiaris", "PETS","https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Le%C3%AFko_au_bois_de_la_Cambre.jpg/330px-Le%C3%AFko_au_bois_de_la_Cambre.jpg"));
        animals.add(new AnimalWiki("Cane", new Race("Pastore Tedesco", "Il pastore tedesco è un cane di taglia grande, dalla struttura leggermente allungata, di buona robustezza e muscolatura. Il carattere è equilibrato, dai nervi saldi, sicuro di sé, di indole tranquilla con i familiari e assolutamente buona salvo provocazioni. È docile, ma altresì caratterizzato da buona combattività, tempra e coraggio, doti che lo rendono facilmente addestrabile a tutti gli impieghi, dalla guardia alla difesa, dalla pastorizia all'accompagnamento.\n" +
                "\n" +
                "La testa vista dall'alto è cuneiforme, la fronte leggermente convessa, presenta un solco mediano appena accennato. Le rime labiali devono essere preferibilmente tese e di colore scuro. La forma degli occhi è a mandorla, il colore varia preferibilmente dallo scuro sino al marrone. Le orecchie sono di forma triangolare, erette, rivolte in avanti. La canna nasale è dritta col tartufo di colore nero. La mascella e la mandibola sono robuste, dotate di buona muscolatura. La formula dentaria conta 42 denti e la chiusura degli incisivi deve essere a forbice. Il collo è forte e robusto, la linea dorsale che discende dal garrese sino alla groppa, deve essere solida e regolare, senza presentare alterazioni che possano disturbare il suo decorso.", "60/65 cm", "Colore marrone/nero"), "Canis lupus familiaris", "PETS","http://www.difossombrone.it/images/anatomia/difettitesta.jpg"));
        animals.add(new AnimalWiki("Gatto", new Race("Persiano", "Il gatto persiano è un gatto domestico, originario dell'Asia Minore. I primi esemplari furono portati in Europa nel 1626 da Pietro Della Valle. Alla razza fu dato il nome di gatto d'angora o, stranamente, di gatto francese. Quando poi, successivamente, dall'Iran venne importata una varietà di gatti più piccoli e tarchiati e col pelo lungo, venne creata la razza persiana.\n" +
                "\n" +
                "Quando nel 1871 fu organizzata la prima grande esposizione felina, dall'inglese Harrison Weir, il Persiano ricevette più riconoscimenti e da allora iniziarono i primi progetti di selezione.\n" +
                "\n" +
                "Questo tipo di gatto (ora esclusivamente d'appartamento) era molto apprezzato nell'età vittoriana: si sa che la Regina Vittoria ne possedeva bellissimi esemplari di colore blu.\n" +
                "\n" +
                "Molti sono gli allevamenti di questa specie. Il lungo e fluente pelo è il suo fascino e la sua bellezza, la colorazione del manto consta di circa duecento combinazioni di colori: fra le più diffuse, quelle a colori solidi (bianco, crema, nero, blu, cioccolato, lilla e rosso), e, per il tipo tortie, tonalità a squama di tartaruga di colore nero o blu-crema, ma anche cioccolato o lilla.\n" +
                "\n" +
                "La Fédération Internationale Féline (FIFE) ha diviso in 3 gruppi il gatto persiano, scegliendo per ogni gruppo una ripartizione di colore: il persiano bicolore con due terzi di colore solido e un terzo di bianco; il persiano arlecchino ha cinque sesti di mantello bianco mentre il colore copre un sesto della superficie; il persiano van con chiazze di colore solo sulla testa e sulla coda.", "30/35 cm", "Lungo, diversi colori"), "Felis catus", "PETS","https://upload.wikimedia.org/wikipedia/it/thumb/3/3e/Prisca.jpg/390px-Prisca.jpg"));

        //Input database test
        //Race apiRaces = api.getAllRaces(); //RESTITUISCE UN ARRAYLIST DI RACE NON L'OGGETTO!!!
        //Animal apiSpecies = api.getAllSpecies();
        /**/
        //animals.add()
        return animals;
    }
}