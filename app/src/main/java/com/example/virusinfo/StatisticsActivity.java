package com.example.virusinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }
}
