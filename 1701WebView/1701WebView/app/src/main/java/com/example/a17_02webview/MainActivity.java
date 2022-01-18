package com.example.a17_02webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = findViewById(R.id.search_bar);
        Button button = findViewById(R.id.confirm_button);
        TextView testo = findViewById(R.id.testo);
        CharSequence url = text.getText();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("keyUrl", url);
                testo.setText(url);
                startActivity(intent);
            }
        });
    }
}