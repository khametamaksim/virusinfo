package com.example.virusinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StatisticsActivity extends AppCompatActivity {

    TextView totalText;
    TextView deathsText;
    TextView recoveredText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        button = (Button)findViewById(R.id.button7);
        totalText = (TextView)findViewById(R.id.textView7);
        deathsText = (TextView)findViewById(R.id.textView11);
        recoveredText = (TextView)findViewById(R.id.textView9);

        startStatistics();
    }

    private void startStatistics(){
        GetNumber gn = new GetNumber();
        gn.execute();
    }

    public void countriesTable(View view) { //onClick
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }

    class GetNumber extends AsyncTask<Void, Void, Void> {

        String[] totalDeaths;
        Elements deathsEl;

        @Override
        protected Void doInBackground(Void... params) {
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (doc!=null) {
                deathsEl = doc.getElementsByClass("maincounter-number"); //gets numbers of total cases, dead and recovered
                totalDeaths = deathsEl.text().split(" ");
            }
            else
                totalDeaths[0] = "Error";

            return null;
        }

        @Override
        public void onPreExecute(){
            totalText.setText("Loading...");
            deathsText.setText("Loading...");
            recoveredText.setText("Loading...");
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

            totalText.setText(totalDeaths[0]);
            deathsText.setText(totalDeaths[1]);
            recoveredText.setText(totalDeaths[2]);
        }
    }

}
