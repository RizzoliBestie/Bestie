package com.example.bestieprova2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //commento di prova
        Button confirm_button = findViewById(R.id.confirm_button);
        EditText username =  findViewById(R.id.username);
        EditText email =  findViewById(R.id.email);
        EditText psw =  findViewById(R.id.psw);
        CharSequence tUsername = username.getText();
        CharSequence tEmail = email.getText();
        CharSequence tPsw = psw.getText();

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (tUsername.length()==0||tEmail.length()==0||tPsw.length()==0)
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                else
                Toast.makeText(MainActivity.this, "You're signed up now", Toast.LENGTH_LONG).show();
            }
        });
    }

}