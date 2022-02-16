package com.example.bestie.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;

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

        SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor edt = pref.edit();

        /* Cablaggio provvisorio */
        edt.putString("username_key","username");
        edt.putString("email_key","e-mail");
        edt.putString("password_key","password");
        edt.apply();
        /* Cablaggio provvisorio */

        // aSwitch.setChecked(xxxx);

        Item[] items = new Item[items_number];

        items[0] = new Item("Username", pref.getString("username_key","-"));
        items[1] = new Item("E-mail", pref.getString("email_key","-"));
        items[2] = new Item("Password", pref.getString("password_key",null));


        ItemListAdapter itemListAdapter = new ItemListAdapter(this, R.layout.activity_settings_item, items);
        listView.setAdapter(itemListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 2){
                    Intent openCPA = new Intent(SettingsActivity.this,ChangePasswordActivity.class);
                    startActivity(openCPA);
                }
                else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);

                    alert.setTitle("Settings");
                    alert.setMessage(items[pos].getText());

                    final EditText input = new EditText(SettingsActivity.this);
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable value = input.getText();
                            // Do something with value!
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });

                    alert.show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);

                alert.setTitle("Delete the profile?");


                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Deleting profile code
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