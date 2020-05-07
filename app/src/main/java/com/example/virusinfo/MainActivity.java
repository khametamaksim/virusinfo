package com.example.virusinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Statistics(View view) {

        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void Calculation(View view) {

        Intent intent = new Intent(this, CalculationActivity.class);
        startActivity(intent);
    }

    public void Prevention(View view) {

        Intent intent = new Intent(this, PreventionActivity.class);
        startActivity(intent);
    }
}
