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

        TextView sign_up = findViewById(R.id.sign_up);
        EditText username =  findViewById(R.id.username);
        EditText email =  findViewById(R.id.email);

        CharSequence tUsername = username.getText();
        CharSequence tEmail = email.getText();

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
                if (tUsername.length()==0||tEmail.length()==0)
                    Toast.makeText(LogInActivity.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(LogInActivity.this, "You're logged in now", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}