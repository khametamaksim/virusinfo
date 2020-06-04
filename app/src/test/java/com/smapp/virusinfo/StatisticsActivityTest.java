package com.smapp.virusinfo;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StatisticsActivityTest {

    @Test(timeout = 1500)
    public void pageStart() {
        StatisticsActivity s = new StatisticsActivity();
        assertNotNull(s);

        StatisticsActivity.GetNumber gn = s.new GetNumber();
        assertNotNull(gn);
    }

    @Test
    public void getNumberTest(){
        final StatisticsActivity s = new StatisticsActivity();
        assertNotNull(s);

        StatisticsActivity.GetNumber gn = s.new GetNumber();
        assertNotNull(gn);

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
                else {
                    totalDeaths[0] = s.getString(R.string.notfound);
                    totalDeaths[1] = s.getString(R.string.notfound);
                    totalDeaths[2] = s.getString(R.string.notfound);
                }

                return null;
            }

            @Override
            public void onPreExecute(){
                s.totalText.setText(R.string.load);
                s.deathsText.setText(R.string.load);
                s.recoveredText.setText(R.string.load);
            }

            @Override
            public void onPostExecute(Void result) {
                super.onPostExecute(result);

                s.totalText.setText(totalDeaths[0]);
                s.deathsText.setText(totalDeaths[1]);
                s.recoveredText.setText(totalDeaths[2]);
            }
        }

        GetNumber g = new GetNumber();
        assertNotNull(g);

        assertNotSame(gn, g);
    }

    @Test
    public void totalCasesTest(){
        StatisticsActivity s = new StatisticsActivity();
        assertNotNull(s);

        StatisticsActivity.GetNumber gn = s.new GetNumber();
        assertNotNull(gn);

        gn.doInBackground();

        assertEquals("6,567,260", gn.totalDeaths[0]);
    }
}