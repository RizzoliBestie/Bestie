package com.example.bestie.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.Toast;

import com.example.bestie.MainActivity;
import com.example.bestie.R;

public class SettingsActivity extends AppCompatActivity {

    private int items_number = 3;
    TextView textView_1 = null;
    TextView textView_2 = null;
    ListView listView = null;
    Switch aSwitch = null;
    Button button = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textView_1 = findViewById(R.id.title_1);
        textView_2 = findViewById(R.id.title_2);
        listView = findViewById(R.id.listView);
        aSwitch = findViewById(R.id.aSwitch);
        button = findViewById(R.id.button);
        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences pref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor edt = pref.edit();

        aSwitch.setChecked(pref.getBoolean("notifications", true));

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.putBoolean("notifications", !pref.getBoolean("notifications", false));
                edt.apply();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        Item[] items = new Item[items_number];

        items[0] = new Item("Username", pref.getString("username_key", null));
        items[1] = new Item("E-mail", pref.getString("email_key", null));
        items[2] = new Item("Password", pref.getString("password_key", null));


        ItemListAdapter itemListAdapter = new ItemListAdapter(this, R.layout.activity_settings_item, items);
        listView.setAdapter(itemListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (pos == 2) {
                    Intent openCPA = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                    startActivity(openCPA);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);

                    alert.setTitle("Settings");
                    alert.setMessage(items[pos].getText());

                    final EditText input = new EditText(SettingsActivity.this);
                    if (pos == 0)
                        input.setText(pref.getString("username_key", null));
                    if (pos == 1)
                        input.setText(pref.getString("email_key", null));
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable value = input.getText();
                            if (pos == 0) {
                                if (validUsername(value.toString())) {
                                    edt.putString("username_key", value.toString());
                                    edt.apply();
                                }
                            }
                            if (pos == 1) {
                                if (validEmail(value.toString())) {
                                    edt.putString("email_key", value.toString());
                                    edt.apply();
                                }
                            }
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
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

    private boolean validUsername(String username) {
        if (username.contains(" ")) {
            Toast.makeText(SettingsActivity.this, "Remove spaces and try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (username.length() == 0) {
            Toast.makeText(SettingsActivity.this, "Username field empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (username.length() > 12) {
            Toast.makeText(SettingsActivity.this, "Invalid username: too long", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    private boolean validEmail(String email) {
        if (email.contains(" ")) {
            Toast.makeText(SettingsActivity.this, "Remove spaces and try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (email.length() == 0) {
            Toast.makeText(SettingsActivity.this, "E-mail field empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (email.length() > 100) {
            Toast.makeText(SettingsActivity.this, "Invalid E-mail: too long", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(this, MainActivity.class);
        startActivity(goBack);
    }
}