package com.example.bestie.storage;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toolbar;

import com.example.bestie.R;

public class StorageActivity extends AppCompatActivity {

    GridView gridView = null;
    Toolbar toolbar = null;
    private int file_n = 12; // dimensionamento provvisorio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        gridView = findViewById(R.id.gridView);

        Item[] items = new Item[file_n];

        /*  cablaggio provvisorio */
        items[0] = new Item("Vaccinazione.pdf","3/12/21");
        items[1] = new Item("RefertoVisita.doc","12/01/22");
        items[2] = new Item("foto_con_fuffi.jpg","");
        items[3] = new Item("foto_vacanza.img","foto in vacanza con fuffi 2021");
        items[4] = new Item("lettera.txt","lettera per fuffi");
        items[5] = new Item("foto_fuffi.gif","passeggiata 2/02/22");
        items[6] = new Item("generic_file","un file generico che mi ha passato il vet");
        items[7] = new Item("immagine.png","foto fuffi che corre sul prato");
        items[8] = new Item("Visita.pdf","visita dal veterinario del 12/20");
        items[9] = new Item("file a caso.zip","file a caso");
        items[10] = new Item("foto spiaggia.jpg","mare 2020");
        items[11] = new Item("AppuntiVet.txt","");
        /*  cablaggio provvisorio */

        ItemGridAdapter itemGridAdapter = new ItemGridAdapter(this, R.layout.activity_storage_item, items);
        gridView.setAdapter(itemGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}