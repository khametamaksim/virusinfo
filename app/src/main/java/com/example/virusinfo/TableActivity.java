package com.example.virusinfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        GetTable gt = new GetTable();
        gt.execute();

    }

    @SuppressLint("ResourceAsColor")
    private void fillTable(int size, ArrayList<String> webData){
        TableLayout ll = (TableLayout) findViewById(R.id.tablelayout);

        boolean check = true;

        for(int i = 0; i < size*7-7; i += 7){

            int k = i;

            if(i == 12*7 && check){

                TableRow row = new TableRow(this);

                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                TextView number = new TextView(this);
                TextView country = new TextView(this);
                TextView totalCases = new TextView(this);
                TextView totalDeaths = new TextView(this);
                TextView totalRecovered = new TextView(this);

                country.setText(webData.get(webData.size() - 6));
                country.setBackgroundColor(R.color.green);

                String c = webData.get(webData.size() - 5) + '\n' + webData.get(webData.size() - 4);
                totalCases.setText(c);
                totalCases.setBackgroundColor(R.color.green);

                String d = webData.get(webData.size() - 3) + '\n' + webData.get(webData.size() - 2);
                totalDeaths.setText(d);
                totalDeaths.setBackgroundColor(R.color.green);

                totalRecovered.setText(webData.get(webData.size() - 1));
                totalRecovered.setBackgroundColor(R.color.green);

                row.addView(number);
                row.addView(country);
                row.addView(totalCases);
                row.addView(totalDeaths);
                row.addView(totalRecovered);

                ll.addView(row);

                check = false;
            }

            TableRow row = new TableRow(this);

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView number = new TextView(this);
            TextView country = new TextView(this);
            TextView totalCases = new TextView(this);
            TextView totalDeaths = new TextView(this);
            TextView totalRecovered = new TextView(this);

            //number.setText(webData.get(k));
            //number.setBackgroundColor(R.color.green);

            country.setText(webData.get(++k));
            country.setBackgroundColor(R.color.green);

            String c = webData.get(++k) + '\n' + webData.get(++k);
            totalCases.setText(c);
            totalCases.setBackgroundColor(R.color.green);

            String d = webData.get(++k) + '\n' + webData.get(++k);
            totalDeaths.setText(d);
            totalDeaths.setBackgroundColor(R.color.green);

            totalRecovered.setText(webData.get(++k));
            totalRecovered.setBackgroundColor(R.color.green);

            row.addView(number);
            row.addView(country);
            row.addView(totalCases);
            row.addView(totalDeaths);
            row.addView(totalRecovered);

            ll.addView(row);
        }
    }


    class GetTable extends AsyncTask<Void, Void, Void> {

        Document doc = null;
        int size;
        ArrayList<String> webData = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {

            try {
                doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();

                Element table = doc.select("table#main_table_countries_today").get(0);
                table.select("tbody");
                Elements rows = table.select("tr[style='']");
                size = rows.size();

                for (int i = 0; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");

                    for(int j = 0; j < 7; j++)
                        webData.add(cols.get(j).text());

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

            fillTable(size, webData);
            //Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(size), Toast.LENGTH_SHORT);
            //toast.show();
        }
    }

}
