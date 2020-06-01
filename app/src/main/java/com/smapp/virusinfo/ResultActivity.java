package com.smapp.virusinfo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle argument = getIntent().getExtras();

        assert argument != null;
        double result = argument.getDouble("res");
        String message = argument.getString("message");

        if (result < 10) {
            getTheme().applyStyle(R.style.Greenresult, true);
        }
        else if (result < 20) {
            getTheme().applyStyle(R.style.Yellowresult, true);
        }
        else
            getTheme().applyStyle(R.style.Redresult, true);

        setContentView(R.layout.activity_result);

        FrameLayout scrollView = findViewById(R.id.scroll1);
        TextView textView = findViewById(R.id.textView13);
        TextView textView1 = findViewById(R.id.textView14);
        textView1.setText("");

        if (result < 10) {
            scrollView.setBackgroundColor(getColor(R.color.green));
            textView.setText(getText(R.string.okay));
        }
        else if (result < 20) {
            scrollView.setBackgroundColor(getColor(R.color.yel));
            textView.setText(getText(R.string.norm));
            textView1.setText(message);
        }
        else {
            scrollView.setBackgroundColor(getColor(R.color.red));
            textView.setText(getText(R.string.bad));
            textView1.setText(message);
        }

    }
}
