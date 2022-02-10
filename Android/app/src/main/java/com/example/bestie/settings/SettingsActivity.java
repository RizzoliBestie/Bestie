package com.example.bestie.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.bestie.R;

public class SettingsActivity extends AppCompatActivity {

    private int items_number = 3;
    TextView textView_1 = null;
    TextView textView_2 = null;
    ListView listView = null;
    Switch aSwitch = null;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textView_1 = findViewById(R.id.title_1);
        textView_2 = findViewById(R.id.title_2);
        listView = findViewById(R.id.listView);
        aSwitch = findViewById(R.id.aSwitch);
        button = findViewById(R.id.button);

        Item[] items = new Item[items_number];

        /* Cablaggio provvisorio dei dati che dovranno essere ottenuti dal db */
        items[0] = new Item("Username","...");
        items[1] = new Item("E-mail","...");
        items[2] = new Item("Change password","...");
        /* Cablaggio provvisorio dei dati che dovranno essere ottenuti dal db */

        ItemListAdapter itemListAdapter = new ItemListAdapter(this, R.layout.activity_settings_item, items);
        listView.setAdapter(itemListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

            }
        });

    }
}