package com.example.bestie.settings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bestie.R;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView textView_1 = null;
    EditText editText_1 = null;
    TextView textView_2 = null;
    EditText editText_2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        textView_1 = findViewById(R.id.textView_1);
        editText_1 = findViewById(R.id.editText_1);
        textView_2 = findViewById(R.id.textView_2);
        editText_2 = findViewById(R.id.editText_2);
    }
}