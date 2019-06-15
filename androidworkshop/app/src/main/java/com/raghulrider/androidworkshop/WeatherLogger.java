package com.raghulrider.androidworkshop;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeatherLogger extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    EditText cityname;
    Button replacefab;
    FloatingActionButton fab;
    String temperature,description,cityn;
    private List<WeatherLog> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_logger);

        recyclerView = findViewById(R.id.recyclerView);
        replacefab = findViewById(R.id.countrychanger);
        fab = findViewById(R.id.refreshfab);
        cityname = findViewById(R.id.fetchcity);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        logs = new ArrayList<>();
        replacefab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityn = cityname.getText().toString();
                cityname.setVisibility(View.GONE);
            }
        });
        replacefab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cityname.setVisibility(View.VISIBLE);
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.openweathermap.org/data/2.5/weather?q="+ cityn +"&units=metric&appid=97e4d766c0fa603d5221d96d3535ae2b";
                WeatherTask wt = new WeatherTask(new WeatherTaskCompleteListener() {
                    @Override
                    public void onWeatherTaskCompleted(Weather weather) {
                        temperature = String.valueOf(weather.getCurrentTemp());
                        description = weather.getDesc();
                        initializeData();
                        initializeAdapter();
                    }
                });
                wt.execute(url);
            }
        });

    }

    private void initializeData() {
        logs.add(new WeatherLog(cityn, temperature + " °C", description));
    }

    private void initializeAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(logs);
        recyclerView.setAdapter(adapter);
    }
}

    class WeatherLog {
        String city;
        String temperature;
        String description;
        WeatherLog(String city, String temperature, String description){
            this.city = city;
            this.temperature = temperature;
            this.description = description;
    }
}