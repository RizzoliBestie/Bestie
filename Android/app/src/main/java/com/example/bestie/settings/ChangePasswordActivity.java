package com.example.bestie.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.bestie.R;
import com.example.bestie.signin.SignInActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    Toolbar toolbar = null;
    TextView textView_1 = null;
    EditText editText_1 = null;
    TextView textView_2 = null;
    EditText editText_2 = null;
    TextView textView_3 = null;
    EditText editText_3 = null;
    Button button_1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toolbar = findViewById(R.id.changePwd_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences pref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();

        textView_1 = findViewById(R.id.textView_1);
        editText_1 = findViewById(R.id.editText_1);
        textView_2 = findViewById(R.id.textView_2);
        editText_2 = findViewById(R.id.editText_2);
        textView_3 = findViewById(R.id.textView_3);
        editText_3 = findViewById(R.id.editText_3);
        button_1 = findViewById(R.id.button_1);

       button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editText_1.getText().toString().equals(pref.getString("password_key",null)))
                    Toast.makeText(ChangePasswordActivity.this, "Error in field: Old password", Toast.LENGTH_LONG).show();
                else if(editText_2.getText().toString().length() == 0 || editText_3.getText().toString().length() == 0)
                    Toast.makeText(ChangePasswordActivity.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                else if(editText_2.getText().toString().contains(" ") || editText_3.getText().toString().contains(" "))
                    Toast.makeText(ChangePasswordActivity.this, "Remove spaces from the fields", Toast.LENGTH_LONG).show();
                else if(editText_2.getText().toString().length() > 12 || editText_2.getText().toString().length() < 5)
                    Toast.makeText(ChangePasswordActivity.this, "Invalid new password", Toast.LENGTH_LONG).show();
                else if(!editText_2.getText().toString().equals(editText_3.getText().toString()))
                    Toast.makeText(ChangePasswordActivity.this, "Difference exists between Confirm password and New password", Toast.LENGTH_LONG).show();
                else{
                    edt.putString("password_key",editText_2.getText().toString());
                    edt.apply();
                    Intent goBack = new Intent(ChangePasswordActivity.this,SettingsActivity.class);
                    startActivity(goBack);
                }
            }
       });
    }
}