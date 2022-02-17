package com.example.bestie.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.example.bestie.R;
import com.example.bestie.settings.SettingsActivity;
import com.example.bestie.signin.SignInActivity;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button confirm_button = findViewById(R.id.confirm_button);

        SharedPreferences pref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor edt = pref.edit();
        edt.putBoolean("notifications",pref.getBoolean("notifications",true));
        edt.apply();

        TextView sign_up = findViewById(R.id.sign_up);
        EditText username =  findViewById(R.id.username);
        EditText password =  findViewById(R.id.password);

        CharSequence tUsername = username.getText();
        CharSequence tPassword = password.getText();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToSign = new Intent(LogInActivity.this, SignInActivity.class);
                startActivity(moveToSign);
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Per ora controlla solo se i dati corrispondono a quelli memorizzati sul db interno
                if (!tUsername.toString().equals(pref.getString("username_key",null)) || !tPassword.toString().equals(pref.getString("password_key",null)))
                    Toast.makeText(LogInActivity.this, "Incorrect fields", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(LogInActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                    Intent moveToSettings = new Intent(LogInActivity.this, SettingsActivity.class);
                    startActivity(moveToSettings);
                }
            }
        });


    }

}