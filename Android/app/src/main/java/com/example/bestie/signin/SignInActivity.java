package com.example.bestie.signin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bestie.R;

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

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tUsername.length()==0||tEmail.length()==0||tPsw.length()==0)
                    Toast.makeText(SignInActivity.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SignInActivity.this, "You're signed up now", Toast.LENGTH_LONG).show();
            }
        });
    }

}