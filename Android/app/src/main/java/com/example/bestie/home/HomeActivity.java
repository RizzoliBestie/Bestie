package com.example.bestie.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestie.R;
import com.example.bestie.settings.SettingsActivity;

import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button menuButton = findViewById(R.id.menu_button);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        /*View drawer = inflater.inflate(R.layout.drawer_menu, null);
        TextView archivio = drawer.findViewById(R.id.archivio);
        TextView settings = drawer.findViewById(R.id.settings);
        TextView logout = drawer.findViewById(R.id.logout);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(openSettings);
            }
        });*/
    }
}