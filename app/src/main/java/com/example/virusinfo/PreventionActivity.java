package com.example.virusinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class PreventionActivity extends AppCompatActivity implements View.OnClickListener {

    //ScrollView scroll = (ScrollView) this.findViewById(R.id.scrollView);
    //ObjectAnimator objectAnimator = new ObjectAnimator();

    boolean firsttrue = false;
    boolean firstfalse = false;
    boolean secondtrue = false;
    boolean secondfalse = false;
    boolean thirdtrue = false;
    boolean thirdfalse = false;
    boolean fourthtrue = false;
    boolean fourthfalse = false;
    boolean fifthtrue = false;
    boolean fifthfalse = false;

    String str1, str2, str3, str4, str5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);

        Button button7 = findViewById(R.id.Button7);
        Button button9 = findViewById(R.id.Button9);
        Button button11 = findViewById(R.id.Button11);
        Button button13 = findViewById(R.id.Button13);
        Button button15 = findViewById(R.id.Button15);
        Button button8 = findViewById(R.id.Button8);
        Button button10 = findViewById(R.id.Button10);
        Button button12 = findViewById(R.id.Button12);
        Button button14 = findViewById(R.id.Button14);
        Button button16 = findViewById(R.id.Button16);

        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
        button16.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button7:
                findViewById(R.id.Button7).setBackgroundResource(R.drawable.press);
                findViewById(R.id.Button8).setBackgroundResource(R.drawable.normal);
                firsttrue = true;
                firstfalse = false;
                break;
            case R.id.Button9:
                findViewById(R.id.Button9).setBackgroundResource(R.drawable.press);
                findViewById(R.id.Button10).setBackgroundResource(R.drawable.normal);
                secondtrue = true;
                secondfalse = false;
                break;
            case R.id.Button11:
                findViewById(R.id.Button11).setBackgroundResource(R.drawable.press);
                findViewById(R.id.Button12).setBackgroundResource(R.drawable.normal);
                thirdtrue = true;
                thirdfalse = false;
                break;
            case R.id.Button13:
                findViewById(R.id.Button13).setBackgroundResource(R.drawable.press);
                findViewById(R.id.Button14).setBackgroundResource(R.drawable.normal);
                fourthtrue = true;
                fourthfalse = false;
                break;
            case R.id.Button15:
                findViewById(R.id.Button15).setBackgroundResource(R.drawable.press);
                findViewById(R.id.Button16).setBackgroundResource(R.drawable.normal);
                fifthtrue = true;
                fifthfalse = false;
                break;
            case R.id.Button8:
                findViewById(R.id.Button8).setBackgroundResource(R.drawable.pressred);
                findViewById(R.id.Button7).setBackgroundResource(R.drawable.normal);
                firstfalse = true;
                firsttrue = false;
                break;
            case R.id.Button10:
                findViewById(R.id.Button10).setBackgroundResource(R.drawable.pressred);
                findViewById(R.id.Button9).setBackgroundResource(R.drawable.normal);
                secondfalse = true;
                secondtrue = false;
                break;
            case R.id.Button12:
                findViewById(R.id.Button12).setBackgroundResource(R.drawable.pressred);
                findViewById(R.id.Button11).setBackgroundResource(R.drawable.normal);
                thirdfalse = true;
                thirdtrue = false;
                break;
            case R.id.Button14:
                findViewById(R.id.Button14).setBackgroundResource(R.drawable.pressred);
                findViewById(R.id.Button13).setBackgroundResource(R.drawable.normal);
                fourthfalse = true;
                fourthtrue = false;
                break;
            case R.id.Button16:
                findViewById(R.id.Button16).setBackgroundResource(R.drawable.pressred);
                findViewById(R.id.Button15).setBackgroundResource(R.drawable.normal);
                fifthfalse = true;
                fifthtrue = false;
                break;
        }

        if ((firsttrue || firstfalse) && (secondtrue || secondfalse) && (thirdtrue || thirdfalse) && (fourthtrue || fourthfalse) && (fifthtrue || fifthfalse)) {
            if (firstfalse) {
                str1 = getString((R.string.protect));
            }
            if (firsttrue) {
                str1 = "";
            }
            if (secondfalse) {
                str2 = getString((R.string.pickup));
            }
            if (secondtrue) {
                str2 = "";
            }
            if (thirdfalse) {
                str3 = getString((R.string.chance));
            }
            if (thirdtrue) {
                str3 = "";
            }
            if (fourthfalse) {
                str4 = getString((R.string.after));
            }
            if (fourthtrue) {
                str4 = "";
            }
            if (fifthfalse) {
                str5 = getString((R.string.airing));
            }
            if (fifthtrue) {
                str5 = "";
            }

            final TextView text = findViewById(R.id.textView5);
            text.setVisibility(View.VISIBLE);

            if (firsttrue && secondtrue && thirdtrue && fourthtrue && fifthtrue) {
                text.setBackgroundResource(R.color.green);
                text.setText(getString(R.string.right));
            } else {
                text.setBackgroundResource(R.color.red);
                text.setText(str1 + str2 + str3 + str4 + str5);
            }

            //scroll.smoothScrollTo(0, text.getBottom());
            scrollDown();

        }
    }
    private void scrollDown() {
        final ScrollView scroll = (ScrollView) this.findViewById(R.id.scrollView);
        scroll.post(new Runnable() {

            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
}