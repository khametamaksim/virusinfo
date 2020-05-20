package com.example.virusinfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.androdocs.httprequest.HttpRequest;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CalculationActivity extends AppCompatActivity {

    public Button button4;
    public Button button5;
    public String city_id = null;
    public String country = null;
    int hum;
    long cels;
    double per;
    double res;
    String message = "";
    String selected;
    EditText people_count;
    boolean mask;
    EditText temperature;
    Spinner feeling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        people_count = (EditText) findViewById(R.id.editText2);
        temperature = (EditText) findViewById(R.id.editText);
        temperature.addTextChangedListener(new PatternedTextWatcher("##.#"));
        feeling = findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.feels)
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        feeling.setAdapter(adapter);

    }

    public void Result(View view) {

        Intent intent;

        if (button4.getText().equals(getString(R.string.location)) || button4.getText().equals(getString(R.string.notfound))) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.defineloc, Toast.LENGTH_SHORT);
                    toast.show();
        }
        else if (button5.getText().equals(getString(R.string.weather)) || button4.getText().equals(getString(R.string.notfound))) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.definewea, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (people_count.getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.defineppl, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (temperature.getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.definetem, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (Double.parseDouble(temperature.getText().toString()) < 35 || Double.parseDouble(temperature.getText().toString()) > 39) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.entercurrent, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            testing();
            intent = new Intent(this, ResultActivity.class);
            intent.putExtra("res", res);
            intent.putExtra("message", message);
            startActivity(intent);
        }
    }

    public void Location (View view) {

        User user = new User();
        user.execute();
        Get get = new Get();
        get.execute();

    }

    public void Weather (View view) {

        Weather weather = new Weather();
        weather.execute();
    }

    @SuppressLint("StaticFieldLeak")
    class User extends AsyncTask<Void, Void, Void> {

        public  String location;

        final Map<String, String> map = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

        public void CountryCodes() {

            map.put("Andorra, Principality Of", "AD");
            map.put("United Arab Emirates", "AE");
            map.put("Afghanistan, Islamic State Of", "AF");
            map.put("Antigua And Barbuda", "AG");
            map.put("Anguilla", "AI");
            map.put("Albania", "AL");
            map.put("Armenia", "AM");
            map.put("Netherlands Antilles", "AN");
            map.put("Angola", "AO");
            map.put("Antarctica", "AQ");
            map.put("Argentina", "AR");
            map.put("American Samoa", "AS");
            map.put("Austria", "AT");
            map.put("Australia", "AU");
            map.put("Aruba", "AW");
            map.put("Azerbaidjan", "AZ");
            map.put("Bosnia-Herzegovina", "BA");
            map.put("Barbados", "BB");
            map.put("Bangladesh", "BD");
            map.put("Belgium", "BE");
            map.put("Burkina Faso", "BF");
            map.put("Bulgaria", "BG");
            map.put("Bahrain", "BH");
            map.put("Burundi", "BI");
            map.put("Benin", "BJ");
            map.put("Bermuda", "BM");
            map.put("Brunei Darussalam", "BN");
            map.put("Bolivia", "BO");
            map.put("Brazil", "BR");
            map.put("Bahamas", "BS");
            map.put("Bhutan", "BT");
            map.put("Bouvet Island", "BV");
            map.put("Botswana", "BW");
            map.put("Belarus", "BY");
            map.put("Belize", "BZ");
            map.put("Canada", "CA");
            map.put("Cocos (Keeling) Islands", "CC");
            map.put("Central African Republic", "CF");
            map.put("Congo, The Democratic Republic Of The", "CD");
            map.put("Congo", "CG");
            map.put("Switzerland", "CH");
            map.put("Ivory Coast (Cote D'Ivoire)", "CI");
            map.put("Cook Islands", "CK");
            map.put("Chile", "CL");
            map.put("Cameroon", "CM");
            map.put("China", "CN");
            map.put("Colombia", "CO");
            map.put("Costa Rica", "CR");
            map.put("Former Czechoslovakia", "CS");
            map.put("Cuba", "CU");
            map.put("Cape Verde", "CV");
            map.put("Christmas Island", "CX");
            map.put("Cyprus", "CY");
            map.put("Czech Republic", "CZ");
            map.put("Germany", "DE");
            map.put("Djibouti", "DJ");
            map.put("Denmark", "DK");
            map.put("Dominica", "DM");
            map.put("Dominican Republic", "DO");
            map.put("Algeria", "DZ");
            map.put("Ecuador", "EC");
            map.put("Estonia", "EE");
            map.put("Egypt", "EG");
            map.put("Western Sahara", "EH");
            map.put("Eritrea", "ER");
            map.put("Spain", "ES");
            map.put("Ethiopia", "ET");
            map.put("Finland", "FI");
            map.put("Fiji", "FJ");
            map.put("Falkland Islands", "FK");
            map.put("Micronesia", "FM");
            map.put("Faroe Islands", "FO");
            map.put("France", "FR");
            map.put("France (European Territory)", "FX");
            map.put("Gabon", "GA");
            map.put("Great Britain", "UK");
            map.put("Grenada", "GD");
            map.put("Georgia", "GE");
            map.put("French Guyana", "GF");
            map.put("Ghana", "GH");
            map.put("Gibraltar", "GI");
            map.put("Greenland", "GL");
            map.put("Gambia", "GM");
            map.put("Guinea", "GN");
            map.put("Guadeloupe (French)", "GP");
            map.put("Equatorial Guinea", "GQ");
            map.put("Greece", "GR");
            map.put("S. Georgia & S. Sandwich Isls.", "GS");
            map.put("Guatemala", "GT");
            map.put("Guam (USA)", "GU");
            map.put("Guinea Bissau", "GW");
            map.put("Guyana", "GY");
            map.put("Hong Kong", "HK");
            map.put("Heard And McDonald Islands", "HM");
            map.put("Honduras", "HN");
            map.put("Croatia", "HR");
            map.put("Haiti", "HT");
            map.put("Hungary", "HU");
            map.put("Indonesia", "ID");
            map.put("Ireland", "IE");
            map.put("Israel", "IL");
            map.put("India", "IN");
            map.put("British Indian Ocean Territory", "IO");
            map.put("Iraq", "IQ");
            map.put("Iran", "IR");
            map.put("Iceland", "IS");
            map.put("Italy", "IT");
            map.put("Jamaica", "JM");
            map.put("Jordan", "JO");
            map.put("Japan", "JP");
            map.put("Kenya", "KE");
            map.put("Kyrgyz Republic (Kyrgyzstan)", "KG");
            map.put("Cambodia, Kingdom Of", "KH");
            map.put("Kiribati", "KI");
            map.put("Comoros", "KM");
            map.put("Saint Kitts & Nevis Anguilla", "KN");
            map.put("North Korea", "KP");
            map.put("South Korea", "KR");
            map.put("Kuwait", "KW");
            map.put("Cayman Islands", "KY");
            map.put("Kazakhstan", "KZ");
            map.put("Laos", "LA");
            map.put("Lebanon", "LB");
            map.put("Saint Lucia", "LC");
            map.put("Liechtenstein", "LI");
            map.put("Sri Lanka", "LK");
            map.put("Liberia", "LR");
            map.put("Lesotho", "LS");
            map.put("Lithuania", "LT");
            map.put("Luxembourg", "LU");
            map.put("Latvia", "LV");
            map.put("Libya", "LY");
            map.put("Morocco", "MA");
            map.put("Monaco", "MC");
            map.put("Moldavia", "MD");
            map.put("Madagascar", "MG");
            map.put("Marshall Islands", "MH");
            map.put("Macedonia", "MK");
            map.put("Mali", "ML");
            map.put("Myanmar", "MM");
            map.put("Mongolia", "MN");
            map.put("Macau", "MO");
            map.put("Northern Mariana Islands", "MP");
            map.put("Martinique (French)", "MQ");
            map.put("Mauritania", "MR");
            map.put("Montserrat", "MS");
            map.put("Malta", "MT");
            map.put("Mauritius", "MU");
            map.put("Maldives", "MV");
            map.put("Malawi", "MW");
            map.put("Mexico", "MX");
            map.put("Malaysia", "MY");
            map.put("Mozambique", "MZ");
            map.put("Namibia", "NA");
            map.put("New Caledonia (French)", "NC");
            map.put("Niger", "NE");
            map.put("Norfolk Island", "NF");
            map.put("Nigeria", "NG");
            map.put("Nicaragua", "NI");
            map.put("Netherlands", "NL");
            map.put("Norway", "NO");
            map.put("Nepal", "NP");
            map.put("Nauru", "NR");
            map.put("Neutral Zone", "NT");
            map.put("Niue", "NU");
            map.put("New Zealand", "NZ");
            map.put("Oman", "OM");
            map.put("Panama", "PA");
            map.put("Peru", "PE");
            map.put("Polynesia (French)", "PF");
            map.put("Papua New Guinea", "PG");
            map.put("Philippines", "PH");
            map.put("Pakistan", "PK");
            map.put("Poland", "PL");
            map.put("Saint Pierre And Miquelon", "PM");
            map.put("Pitcairn Island", "PN");
            map.put("Puerto Rico", "PR");
            map.put("Portugal", "PT");
            map.put("Palau", "PW");
            map.put("Paraguay", "PY");
            map.put("Qatar", "QA");
            map.put("Reunion (French)", "RE");
            map.put("Romania", "RO");
            map.put("Russian Federation", "RU");
            map.put("Russia", "RU");
            map.put("Rwanda", "RW");
            map.put("Saudi Arabia", "SA");
            map.put("Solomon Islands", "SB");
            map.put("Seychelles", "SC");
            map.put("Sudan", "SD");
            map.put("Sweden", "SE");
            map.put("Singapore", "SG");
            map.put("Saint Helena", "SH");
            map.put("Slovenia", "SI");
            map.put("Svalbard And Jan Mayen Islands", "SJ");
            map.put("Slovak Republic", "SK");
            map.put("Sierra Leone", "SL");
            map.put("San Marino", "SM");
            map.put("Senegal", "SN");
            map.put("Somalia", "SO");
            map.put("Suriname", "SR");
            map.put("Saint Tome (Sao Tome) And Principe", "ST");
            map.put("Former USSR", "SU");
            map.put("El Salvador", "SV");
            map.put("Syria", "SY");
            map.put("Swaziland", "SZ");
            map.put("Turks And Caicos Islands", "TC");
            map.put("Chad", "TD");
            map.put("French Southern Territories", "TF");
            map.put("Togo", "TG");
            map.put("Thailand", "TH");
            map.put("Tadjikistan", "TJ");
            map.put("Tokelau", "TK");
            map.put("Turkmenistan", "TM");
            map.put("Tunisia", "TN");
            map.put("Tonga", "TO");
            map.put("East Timor", "TP");
            map.put("Turkey", "TR");
            map.put("Trinidad And Tobago", "TT");
            map.put("Tuvalu", "TV");
            map.put("Taiwan", "TW");
            map.put("Tanzania", "TZ");
            map.put("Ukraine", "UA");
            map.put("Uganda", "UG");
            map.put("United Kingdom", "UK");
            map.put("USA Minor Outlying Islands", "UM");
            map.put("United States", "US");
            map.put("Uruguay", "UY");
            map.put("Uzbekistan", "UZ");
            map.put("Holy See (Vatican City State)", "VA");
            map.put("Saint Vincent & Grenadines", "VC");
            map.put("Venezuela", "VE");
            map.put("Virgin Islands (British)", "VG");
            map.put("Virgin Islands (USA)", "VI");
            map.put("Vietnam", "VN");
            map.put("Vanuatu", "VU");
            map.put("Wallis And Futuna Islands", "WF");
            map.put("Samoa", "WS");
            map.put("Yemen", "YE");
            map.put("Mayotte", "YT");
            map.put("Yugoslavia", "YU");
            map.put("South Africa", "ZA");
            map.put("Zambia", "ZM");
            map.put("Zaire", "ZR");
            map.put("Zimbabwe", "ZW");

        }

        public String getCode(String country) {
            CountryCodes();
            String countryFound = map.get(country);

            return countryFound;
        }

        @Override
        public Void doInBackground(Void... voids) {

            String ip;
            String city;
            Document doc = null;

            try {
                doc = Jsoup.connect("https://ip.city").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
            try (WebServiceClient client = new WebServiceClient.Builder(289797, "iOn3aq3jXb9Ia8Ro")
                    .build()) {

                InetAddress ipAddress = InetAddress.getByName("128.101.101.101");

                // Do the lookup
                CountryResponse response = client.country(ipAddress);

                Country country = response.getCountry();
                location = country.getName();
            } catch (IOException | GeoIp2Exception e) {
                e.printStackTrace();
            }
            */

            if (doc != null) {
                ip = doc.getElementsByClass("form-control").val();
                String str = doc.getElementsByClass("details").select("h3").text();
                city = str.substring(str.indexOf("City:") + 6, str.indexOf("Country") - 1);
                country = str.substring(str.indexOf("Country:") + 9, str.indexOf("ISP") - 1);
                if (country.equals("Russian Federation"))
                    country = "Russia";
                if (country.equals("United States"))
                    country = "USA";
                if (country.equals("United Kingdom"))
                    country = "UK";
                if (country.equals("Country: Korea, Republic of"))
                    country = "S. Korea";
                city_id = city + "," + getCode(country);
                location = city + ", " + country;
            } else
                location = getString(R.string.notfound);

            return null;
        }

        @Override
        public void onPreExecute() {
            button4.setText(R.string.load);

        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

            button4.setText(location);

        }

    }

    class Get extends AsyncTask<Void, Void, Void> {

        String[] country_array = new String[200];
        int index = 0;
        String s1, s2;

        @Override
        public Void doInBackground(Void... voids) {

            Document web = null;

            try {
                web = Jsoup.connect("https://www.worldometers.info/coronavirus/#countries").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (web != null) {

                Elements permln = web.getElementsByClass("mt_a");

                for (int i = 0; i < 199; i++) {
                    country_array[i] = permln.eachText().get(i);
                    if (country_array[i].equals(country))
                        index = i;
                }

                Element table = web.select("table").get(0);
                Elements rows = table.select("tr[style='']");

                Element row = rows.get(index);
                Elements cols = row.select("td");

                s1 = cols.get(1).text();
                s1 = s1.replace("," , "");
                s2 = cols.get(5).text();
                s2 = s2.replace("," , "");
                per = Double.parseDouble(s1) / Double.parseDouble(s2);
                }

            else {

                per = 3;

            }

            return null; //11 czerwony
        }

        @Override
        public void onPreExecute() {

        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

        }

    }

    @SuppressLint("StaticFieldLeak")
    class Weather extends AsyncTask<String, Void, String> {

        String Api = "d860b64cc00fd69054946979f713ae30";

        @Override
        public String doInBackground(String... args) {

            if (city_id == null) {
                button5.setText(R.string.define);
                return null;
            }
            else {
                return HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + city_id + "&units=metric&appid=" + Api);
            }
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();

            button5.setText(R.string.load);

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onPostExecute(String result) {

            if (result != null) {

                try {
                    JSONObject jsonObj = new JSONObject(result);
                    JSONObject main = jsonObj.getJSONObject("main");
                    JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                    long tempMi = Math.round(Double.parseDouble(main.getString("temp_min")));
                    String tempMin = "" + tempMi + "°C";
                    long tempMa = Math.round(Double.parseDouble(main.getString("temp_max")));
                    String tempMax = "" + tempMa + "°C";
                    cels = tempMi;
                    String temp;
                    if (tempMin.equals(tempMax))
                        temp = tempMin;
                    else temp = (tempMin + "-" + tempMax);
                    String humidity = getString(R.string.humidity) + " " + main.getString("humidity") + "%";
                    hum = Integer.parseInt(main.getString("humidity"));

                    String weatherDescription = weather.getString("description");

                    button5.setText(temp + ", " + humidity);// + ", " + weatherDescription);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            else
                if (city_id == null)
                    button5.setText(R.string.define);
                else
                    button5.setText(getString(R.string.notfound));
        }
    }

    public void testing () {

        double k = 0;
        int ppl = Integer.parseInt(people_count.getText().toString());
        double tmp = Double.parseDouble(temperature.getText().toString());
        mask = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
        selected = feeling.getSelectedItem().toString();

        if (selected.equals(getString(R.string.ifeelg))) {
            if (tmp <= 36.8) {

                if (per < 1.5)
                    k = k + 2;
                else if (per <= 3)
                    k = k + 5;
                else if (per <= 6)
                    k = k + 9;
                else if (per <= 9)
                    k = k + 14;
                else
                    k = k + 20;

                if (hum < 35 || hum > 65)
                    k = k + 1;
                else
                    k = k + 0;

                if (mask)
                    k = k + 0.5;
                else
                    k = k + 1.5;

                if (cels <= -15)
                    k = k + 0;
                else if (cels <= 0)
                    k = k + 1;
                else if (cels <= 22)
                    k = k + 2;
                else if (cels <= 37)
                    k = k + 1;
                else
                    k = k + 0;

                if (ppl == 0)
                    k = k * 0;
                else if (ppl <= 2)
                    k = k * 0.2;
                else if (ppl <= 4)
                    k = k * 0.45;
                else if (ppl <= 6)
                    k = k * 0.75;
                else if (ppl <= 9)
                    k = k * 1.1;
                else if (ppl <= 15)
                    k = k * 1.55;
                else if (ppl <= 21)
                    k = k * 2.1;
                else
                    k = k * 3;

                if (k >= 10) {
                    message = getString(R.string.reduce);
                }

            }
            else if (tmp < 37.2) {

                k = k + 2;

                if (per < 1.5)
                    k = k + 2;
                else if (per <= 3)
                    k = k + 5;
                else if (per <= 6)
                    k = k + 9;
                else if (per <= 9)
                    k = k + 14;
                else
                    k = k + 20;

                if (hum < 35 || hum > 65)
                    k = k + 1;
                else
                    k = k + 0;

                if (mask)
                    k = k + 0.5;
                else
                    k = k + 1.5;

                if (cels <= -15)
                    k = k + 0;
                else if (cels <= 0)
                    k = k + 1;
                else if (cels <= 22)
                    k = k + 2;
                else if (cels <= 37)
                    k = k + 1;
                else
                    k = k + 0;

                if (ppl == 0)
                    k = k * 0;
                else if (ppl <= 2)
                    k = k * 0.2;
                else if (ppl <= 4)
                    k = k * 0.45;
                else if (ppl <= 6)
                    k = k * 0.75;
                else if (ppl <= 9)
                    k = k * 1.1;
                else if (ppl <= 15)
                    k = k * 1.55;
                else if (ppl <= 21)
                    k = k * 2.1;
                else
                    k = k * 3;

                if (k >= 10) {
                    message = "" + getString(R.string.reduce) + ". " + getString(R.string.tempert);
                }

            }
            else {

                k = k + 3;

                if (per < 1.5)
                    k = k + 2;
                else if (per <= 3)
                    k = k + 5;
                else if (per <= 6)
                    k = k + 9;
                else if (per <= 9)
                    k = k + 14;
                else
                    k = k + 20;

                if (hum < 35 || hum > 65)
                    k = k + 1;
                else
                    k = k + 0;

                if (mask)
                    k = k + 0.5;
                else
                    k = k + 1.5;

                if (cels <= -15)
                    k = k + 0;
                else if (cels <= 0)
                    k = k + 1;
                else if (cels <= 22)
                    k = k + 2;
                else if (cels <= 37)
                    k = k + 1;
                else
                    k = k + 0;

                if (ppl == 0)
                    k = k * 0;
                else if (ppl <= 2)
                    k = k * 0.2;
                else if (ppl <= 4)
                    k = k * 0.45;
                else if (ppl <= 6)
                    k = k * 0.75;
                else if (ppl <= 9)
                    k = k * 1.1;
                else if (ppl <= 15)
                    k = k * 1.55;
                else if (ppl <= 21)
                    k = k * 2.1;
                else
                    k = k * 3;

                if (k >= 10) {
                    message = "" + getString(R.string.reduce) + ". " + getString(R.string.tempert);
                }
            }
            if (tmp > 37.4) {
                k = 20;
                message = getString(R.string.recommend);
            }
        }

        else {
            if (tmp <= 36.8) {

                k = k + 20;

                if (per < 1.5)
                    k = k + 2;
                else if (per <= 3)
                    k = k + 5;
                else if (per <= 6)
                    k = k + 9;
                else if (per <= 9)
                    k = k + 14;
                else
                    k = k + 20;

                if (hum < 35 || hum > 65)
                    k = k + 1;
                else
                    k = k + 0;

                if (mask)
                    k = k + 0.5;
                else
                    k = k + 1.5;

                if (cels <= -15)
                    k = k + 0;
                else if (cels <= 0)
                    k = k + 1;
                else if (cels <= 22)
                    k = k + 2;
                else if (cels <= 37)
                    k = k + 1;
                else
                    k = k + 0;

                if (ppl <= 2)
                    k = k * 0.3;
                else if (ppl <= 4)
                    k = k * 0.45;
                else if (ppl <= 6)
                    k = k * 0.75;
                else if (ppl <= 9)
                    k = k * 1.1;
                else if (ppl <= 15)
                    k = k * 1.55;
                else if (ppl <= 21)
                    k = k * 2.1;
                else
                    k = k * 3;

                if (k >= 10) {
                    message = "" + getString(R.string.illnes) + ". " + getString(R.string.lookfor);
                }

            }
            else if (tmp < 37.2) {

                k = k + 30;

                if (per < 1.5)
                    k = k + 2;
                else if (per <= 3)
                    k = k + 5;
                else if (per <= 6)
                    k = k + 9;
                else if (per <= 9)
                    k = k + 14;
                else
                    k = k + 20;

                if (hum < 35 || hum > 65)
                    k = k + 1;
                else
                    k = k + 0;

                if (mask)
                    k = k + 0.5;
                else
                    k = k + 1.5;

                if (cels <= -15)
                    k = k + 0;
                else if (cels <= 0)
                    k = k + 1;
                else if (cels <= 22)
                    k = k + 2;
                else if (cels <= 37)
                    k = k + 1;
                else
                    k = k + 0;

                if (ppl <= 2)
                    k = k * 0.3;
                else if (ppl <= 4)
                    k = k * 0.45;
                else if (ppl <= 6)
                    k = k * 0.75;
                else if (ppl <= 9)
                    k = k * 1.1;
                else if (ppl <= 15)
                    k = k * 1.55;
                else if (ppl <= 21)
                    k = k * 2.1;
                else
                    k = k * 3;

                if (k >= 10) {
                    message = "" + getString(R.string.illnes) + ". " + getString(R.string.lookfor);
                }

            }
            else {

                k = k + 40;

                if (per < 1.5)
                    k = k + 2;
                else if (per <= 3)
                    k = k + 5;
                else if (per <= 6)
                    k = k + 9;
                else if (per <= 9)
                    k = k + 14;
                else
                    k = k + 20;

                if (hum < 35 || hum > 65)
                    k = k + 1;
                else
                    k = k + 0;

                if (mask)
                    k = k + 0.5;
                else
                    k = k + 1.5;

                if (cels <= -15)
                    k = k + 0;
                else if (cels <= 0)
                    k = k + 1;
                else if (cels <= 22)
                    k = k + 2;
                else if (cels <= 37)
                    k = k + 1;
                else
                    k = k + 0;

                if (ppl <= 2)
                    k = k * 0.3;
                else if (ppl <= 4)
                    k = k * 0.45;
                else if (ppl <= 6)
                    k = k * 0.75;
                else if (ppl <= 9)
                    k = k * 1.1;
                else if (ppl <= 15)
                    k = k * 1.55;
                else if (ppl <= 21)
                    k = k * 2.1;
                else
                    k = k * 3;

                if (k >= 10 && k < 20) {
                    message = "" + getString(R.string.illnes) + ". " + getString(R.string.lookfor);
                }
                else if (k >= 20) {
                    message = "" + getString(R.string.illnes) + ". " + getString(R.string.recommend);
                }

                if (tmp > 37.4) {
                    k = 20;
                    message = "" + getString(R.string.illnes) + ". " + getString(R.string.recommend);
                }
            }
        }

        res = k;

    }
}