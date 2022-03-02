package com.example.bestie.storage;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.bestie.R;
import com.example.bestie.settings.ChangePasswordActivity;
import com.example.bestie.settings.SettingsActivity;
import com.google.android.material.button.MaterialButton;

public class StorageActivity extends AppCompatActivity {

    GridView gridView = null;
    Toolbar toolbar = null;
    ImageView addFileButton = null;
    private int file_n = 12; // dimensionamento provvisorio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        gridView = findViewById(R.id.gridView);
        addFileButton = findViewById(R.id.addFile_button);
        (new ItemImageDownload(this, "https://icon-library.com/images/plus-icon-png/plus-icon-png-15.jpg", addFileButton)).execute();
        toolbar = findViewById(R.id.storage_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StorageActivity.this, "add button clicked", Toast.LENGTH_SHORT).show();
            }
        });

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
                AlertDialog.Builder alert = new AlertDialog.Builder(StorageActivity.this);

                alert.setTitle(items[i].getCompleteFile_name());
                alert.setMessage(items[i].getCompleteDescription());

                alert.setPositiveButton("Open file", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Opening file code
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });
    }
}