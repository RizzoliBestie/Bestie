package com.example.bestie.signin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bestie.API.API_Connection_Bestie;
import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.R;
import com.example.bestie.login.LogInActivity;
import com.example.bestie.settings.SettingsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {

    Button confirm_button;
    EditText username, email, psw;
    String tUsername, tPsw,tEmail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        confirm_button = findViewById(R.id.confirm_button);

        username =  findViewById(R.id.username);
        email =  findViewById(R.id.email);
        psw =  findViewById(R.id.psw);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tUsername = username.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tEmail = email.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tPsw = psw.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        confirm_button.setOnClickListener(new View.OnClickListener() {

            Retrofit retrofit = ((API_Connection_Bestie)getApplication()).getRetrofit();
            API_Methods_Interface api = retrofit.create(API_Methods_Interface.class);

            @Override
            public void onClick(View view) {
                if(tUsername.length()==0||tPsw.length()==0||tEmail.length()==0)
                    Toast.makeText(SignInActivity.this, "Compila tutti i campi", Toast.LENGTH_LONG).show();
                else if(tUsername.contains(" "))
                    Toast.makeText(SignInActivity.this,"Lo username non può contenere spazi", Toast.LENGTH_LONG).show();
                else if(tUsername.length()<=3)
                    Toast.makeText(SignInActivity.this, "Lo username deve avere una lunghezza minima di 4 caratteri", Toast.LENGTH_LONG).show();
                else if(tPsw.length()<=5)
                    Toast.makeText(SignInActivity.this, "La password deve avere una lunghezza minima di 6 caratteri", Toast.LENGTH_LONG).show();
                else if(tEmail.contains("@"))
                    Toast.makeText(SignInActivity.this, "Inserisci un indirizzo email valido", Toast.LENGTH_LONG).show();
                else{
                    Call<Boolean> checkSigninCall = api.checkSignin(tUsername, tEmail);
                    checkSigninCall.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.body()) {

                                Toast.makeText(SignInActivity.this, "Email o nome utente già utilizzati!",Toast.LENGTH_LONG).show();

                            } else {
                                Call<Boolean> registerCall = api.register(tUsername, tEmail, tPsw, null);
                                registerCall.enqueue(new Callback<Boolean>() {
                                    @Override
                                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                        if(response.body()){
                                            Toast.makeText(SignInActivity.this, "Registrazione avvenuta con successo!", Toast.LENGTH_LONG).show();

                                            Intent moveToSettings = new Intent(SignInActivity.this, LogInActivity.class);
                                            startActivity(moveToSettings);
                                        } else {
                                            Toast.makeText(SignInActivity.this, "Ops!! Qualcosa è andato storto!!", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Boolean> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }

            }
        });

        //SharedPreferences pref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //SharedPreferences.Editor edt = pref.edit();

        /*confirm_button.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }

}