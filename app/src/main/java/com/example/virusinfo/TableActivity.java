package com.example.virusinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

    private void fillTable(int size, ArrayList<String> webData){
        TableLayout ll = (TableLayout) findViewById(R.id.tablelayout);

        for(int i = 0; i < size; i += 7){

            TableRow row= new TableRow(this);

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView number = new TextView(this);
            TextView country = new TextView(this);
            TextView totalCases = new TextView(this);
            TextView totalDeaths = new TextView(this);
            TextView totalRecovered = new TextView(this);

            int k = i;
            number.setText(webData.get(k));

            country.setText(webData.get(++k));

            String c = webData.get(++k) + '\n' + webData.get(++k);
            totalCases.setText(c);

            String d = webData.get(++k) + '\n' + webData.get(++k);
            totalDeaths.setText(d);

            totalRecovered.setText(webData.get(++k));

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
        private int size;
        ArrayList<String> webData = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {

            try {
                doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();

                Element table = doc.select("table#main_table_countries_today").get(0);
                table.select("tbody");
                Elements rows = table.select("tr");
                size = rows.size() - 5;

                for (int i = 3; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");

                    for(int j = 0; j < 7; j++)
                        webData.add(cols.get(i).text());
                    if(i == 3)
                        i+=2;
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
        }
    }

}
