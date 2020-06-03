package com.smapp.virusinfo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    ImageButton imageButton;
    TextView country;
    TextView totalCases;
    TextView totalDeaths;
    TextView totalRecovered;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        imageButton = (ImageButton) findViewById(R.id.but1);
        imageButton.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.text1);
        textView.setVisibility(View.VISIBLE);



        GetTable gt = new GetTable();
        gt.execute();

    }

    public void reloadTable(View view) {
        imageButton.setVisibility(View.INVISIBLE);
        setContentView(R.layout.activity_table);

        imageButton = (ImageButton) findViewById(R.id.but1);
        imageButton.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.text1);
        textView.setVisibility(View.VISIBLE);

        GetTable gt = new GetTable();
        gt.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"ResourceAsColor", "WrongConstant", "SetTextI18n"})
    private void fillTable(int size, ArrayList<String> webData){
        TableLayout ll = (TableLayout) findViewById(R.id.tablelayout);
        textView.setVisibility(View.INVISIBLE);

        boolean check = true;

        for(int i = 0; i < size*7-7; i += 7){

            int k = i;
            int s1 = Integer.parseInt(webData.get(webData.size() - 5).replace(",", ""));
            int s2 = Integer.parseInt(webData.get(k + 2).replace(",", ""));

            if((s1 > s2) && check){

                TableRow row = new TableRow(this);

                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                //TextView number = new TextView(this);
                country = new TextView(this);
                totalCases = new TextView(this);
                totalDeaths = new TextView(this);
                totalRecovered = new TextView(this);

                country.setText(webData.get(i) + " " + webData.get(webData.size() - 6));
                country.setBackgroundResource(R.drawable.cellsc);
                country.setWidth(20);
                country.setHeight(150);
                country.setTextAppearance(this, R.style.textrowblack);
                country.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                String c = webData.get(webData.size() - 5) + '\n' + webData.get(webData.size() - 4);
                totalCases.setText(c);
                totalCases.setBackgroundResource(R.color.yel);
                totalCases.setWidth(0);
                totalCases.setHeight(150);
                totalCases.setTextAppearance(this, R.style.textrow);
                totalCases.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                String d = webData.get(webData.size() - 3) + '\n' + webData.get(webData.size() - 2);
                totalDeaths.setText(d);
                totalDeaths.setBackgroundResource(R.color.red);
                totalDeaths.setWidth(0);
                totalDeaths.setHeight(150);
                totalDeaths.setTextAppearance(this, R.style.textrow);
                totalDeaths.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                totalRecovered.setText(webData.get(webData.size() - 1));
                totalRecovered.setBackgroundResource(R.color.green);
                totalRecovered.setWidth(0);
                totalRecovered.setHeight(150);
                totalRecovered.setTextAppearance(this, R.style.textrow);
                totalRecovered.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                //row.addView(number);
                row.addView(country);
                row.addView(totalCases);
                row.addView(totalDeaths);
                row.addView(totalRecovered);

                ll.addView(row);

                check = false;
            }


            TableRow row= new TableRow(this);

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            //TextView number = new TextView(this);
            country = new TextView(this);
            totalCases = new TextView(this);
            totalDeaths = new TextView(this);
            totalRecovered = new TextView(this);
            country.setVisibility(View.VISIBLE);
            totalCases.setVisibility(View.VISIBLE);
            totalDeaths.setVisibility(View.VISIBLE);
            totalRecovered.setVisibility(View.VISIBLE);

            //number.setText(webData.get(k));
            //number.setBackgroundColor(R.color.green);

            row.addView(country);
            row.addView(totalCases);
            row.addView(totalDeaths);
            row.addView(totalRecovered);

            ll.addView(row);

            if (check)
                country.setText(webData.get(k) + " " + webData.get(++k));
            else
                country.setText(webData.get(k+7) + " " + webData.get(++k));
            country.setBackgroundResource(R.drawable.cellsc);
            country.setWidth(20);
            country.setHeight(170);
            country.setTextAppearance(this, R.style.textrowblack);
            country.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            String c = webData.get(++k) + '\n' + webData.get(++k);
            totalCases.setText(c);
            totalCases.setBackgroundResource(R.color.yel);
            totalCases.setWidth(0);
            totalCases.setHeight(170);
            totalCases.setTextAppearance(this, R.style.textrow);
            totalCases.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            String d = webData.get(++k) + '\n' + webData.get(++k);
            totalDeaths.setText(d);
            totalDeaths.setBackgroundResource(R.color.red);
            totalDeaths.setWidth(0);
            totalDeaths.setHeight(170);
            totalDeaths.setTextAppearance(this, R.style.textrow);
            totalDeaths.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            totalRecovered.setText(webData.get(++k));
            totalRecovered.setBackgroundResource(R.color.green);
            totalRecovered.setWidth(0);
            totalRecovered.setHeight(170);
            totalRecovered.setTextAppearance(this, R.style.textrow);
            totalRecovered.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            //row.addView(number);

        }
    }

    class GetTable extends AsyncTask<Void, Void, Void> {

        Document doc = null;
        int size;
        ArrayList<String> webData = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {

            Elements rows = null;

            try {
                doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();

                Element table = doc.select("table").get(0);
                rows = table.select("tr[style=''], tr[style='background-color:#F0F0F0'], tr[style='background-color:#EAF7D5']");
                size = rows.size();

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (doc != null) {
                for (int i = 0; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");

                    for(int j = 0; j < 7; j++)
                        webData.add(cols.get(j).text());

                }
            }

            return null;
        }

        @Override
        public void onPreExecute() {
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

            fillTable(size, webData);

            imageButton.setVisibility(View.VISIBLE);

        }
    }

}
