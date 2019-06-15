package com.raghulrider.androidworkshop;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;



public class WeatherActivity extends AppCompatActivity {
    ActionBar actionBar;
    AutoCompleteTextView actcities;
    Button btn;
    TextView fetch,weatherdesc,min,max;
    String [] cities = {"India","USA","China","Australia","Chennai","Coimbatore","Salem","Attur"} ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        btn = findViewById(R.id.fetch);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Weather");
        fetch = findViewById(R.id.temp);
        min = findViewById(R.id.minvalue);
        max = findViewById(R.id.maxvalue);
        weatherdesc = findViewById(R.id.weatherdesc);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actcities = findViewById(R.id.cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, cities);
        actcities.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.openweathermap.org/data/2.5/weather?q="+ actcities.getText().toString() +"&units=metric&appid=97e4d766c0fa603d5221d96d3535ae2b";
                WeatherTask wt = new WeatherTask(new WeatherTaskCompleteListener() {
                    @Override
                    public void onWeatherTaskCompleted(Weather weather) {
                        weatherdesc.setText(weather.getDesc());
                        String maxi = String.valueOf(weather.getCurrentmax());
                        String mini = String.valueOf(weather.getCurrentmin());
                        String temperature = String.valueOf(weather.getCurrentTemp());
                        fetch.setText(temperature + " °C" );
                        min.setText(mini + " °C");
                        max.setText(maxi + " °C");
                    }
                });
                wt.execute(url);
            }
        });
}
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
