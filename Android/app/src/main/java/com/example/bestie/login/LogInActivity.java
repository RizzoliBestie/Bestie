package com.example.bestie.login;

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
import android.widget.TextView;

import com.example.bestie.API.API_Connection_Bestie;
import com.example.bestie.API.API_Methods_Interface;
import com.example.bestie.MainActivity;
import com.example.bestie.R;
import com.example.bestie.general.Profile;
import com.example.bestie.settings.SettingsActivity;
import com.example.bestie.signin.SignInActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LogInActivity extends AppCompatActivity {

    API_Methods_Interface api = null;
    TextView sign_up, badLogin;
    EditText username, password;
    Button confirm_button;
    String tUsername="", tPassword="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        confirm_button = (Button)findViewById(R.id.confirm_button);

        SharedPreferences pref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor edt = pref.edit();
        edt.putBoolean("notifications",pref.getBoolean("notifications",true));
        edt.apply();

        sign_up = (TextView) findViewById(R.id.sign_up);
        username =  (EditText) findViewById(R.id.username);
        password =  (EditText)findViewById(R.id.password);

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

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tPassword = password.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

                Retrofit retrofit = ((API_Connection_Bestie)getApplication()).getRetrofit();
                API_Methods_Interface api = retrofit.create(API_Methods_Interface.class);

                if(!(tUsername.equals("") || tPassword.equals(""))) {
                    Call<Profile> checkLoginCall = api.checkLogin(tUsername, tPassword);//qui avviene la chiamata alla funzione con retrofit (springoot)
                    checkLoginCall.enqueue(new Callback<Profile>() {
                        @Override
                        public void onResponse(Call<Profile> call, Response<Profile> response) {
                            if (response.code()!=500) {//response.body ritorna il valore di ritorno della funziona chiamata

                                //Salvo username id e password nelle shared preferences per utilizzarli dopo
                                Profile profile = response.body();
                                int id_user = profile.getId_user();
                                String username = profile.getUsername();
                                String password = profile.getPassword();
                                SharedPreferences pref = LogInActivity.this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("id_user", id_user);
                                editor.putString("username", username);
                                editor.putString("password", password);
                                editor.apply();

                                Intent mainActivityIntent = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(mainActivityIntent);

                            } else {
                                Toast.makeText(LogInActivity.this, "Incorrect fields", Toast.LENGTH_LONG).show();
                                badLogin.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<Profile> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }else{
                    Toast.makeText(LogInActivity.this, "Mannaggia", Toast.LENGTH_SHORT).show();
                }
                // Per ora controlla solo se i dati corrispondono a quelli memorizzati sul db interno
                /*if (!tUsername.toString().equals(pref.getString("username_key",null)) || !tPassword.toString().equals(pref.getString("password_key",null)))
                    Toast.makeText(LogInActivity.this, "Incorrect fields", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(LogInActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                    Intent moveToSettings = new Intent(LogInActivity.this, SettingsActivity.class);
                    startActivity(moveToSettings);
                }*/
            }
        });


    }

}