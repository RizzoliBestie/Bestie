package com.example.bestie.signin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bestie.R;
import com.example.bestie.login.LogInActivity;
import com.example.bestie.settings.SettingsActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        Button confirm_button = findViewById(R.id.confirm_button);

        EditText username =  findViewById(R.id.username);
        EditText email =  findViewById(R.id.email);
        EditText psw =  findViewById(R.id.psw);

        CharSequence tUsername = username.getText();
        CharSequence tEmail = email.getText();
        CharSequence tPsw = psw.getText();

        SharedPreferences pref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor edt = pref.edit();

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tUsername.length()==0||tEmail.length()==0||tPsw.length()==0)
                    Toast.makeText(SignInActivity.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                else if(tUsername.toString().contains(" ")||tEmail.toString().contains(" ")||tPsw.toString().contains(" "))
                    Toast.makeText(SignInActivity.this, "Remove spaces from the fields", Toast.LENGTH_LONG).show();
                else if(tUsername.toString().length() > 12)
                    Toast.makeText(SignInActivity.this, "Incorrect field: Username\nToo long", Toast.LENGTH_LONG).show();
                else if(tEmail.toString().length() > 100)
                    Toast.makeText(SignInActivity.this, "Incorrect field: E-mail\nToo long", Toast.LENGTH_LONG).show();
                else if(tPsw.toString().length() > 12)
                    Toast.makeText(SignInActivity.this, "Incorrect field: Password\nToo long", Toast.LENGTH_LONG).show();
                else if(tPsw.toString().length() < 5)
                    Toast.makeText(SignInActivity.this, "Incorrect field: Password\nToo short", Toast.LENGTH_LONG).show();
                else {
                    edt.putString("username_key", tUsername.toString());
                    edt.putString("email_key", tEmail.toString());
                    edt.putString("password_key", tPsw.toString());
                    edt.apply();

                    Toast.makeText(SignInActivity.this, "You're signed in now", Toast.LENGTH_LONG).show();

                    Intent moveToSettings = new Intent(SignInActivity.this, LogInActivity.class);
                    startActivity(moveToSettings);
                }
            }
        });
    }

}