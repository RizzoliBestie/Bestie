package com.example.bestie.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bestie.R;
//import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class ContentSlider extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_layout);
  /*      new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();*/
    }
}
//push per il mio amico mike <3