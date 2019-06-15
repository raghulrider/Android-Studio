package com.raghulrider.androidworkshop;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherTask extends AsyncTask<String, Void, String> {
    WeatherTaskCompleteListener mListener;
    public WeatherTask(WeatherTaskCompleteListener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        String responseString = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(strings[0])
                .build();
        try (Response response = client.newCall(request).execute()) {
            responseString = response.body().string();
            Log.d("JSON Data", responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject reader = new JSONObject(s);
            JSONArray weatherdata = reader.getJSONArray("weather");
            String desc = weatherdata.getJSONObject(0).getString("description");
            JSONObject main = reader.getJSONObject("main");
            Double currentTemp = main.getDouble("temp");
            Double currentmin = main.getDouble("temp_min");
            Double currentmax = main.getDouble("temp_max");
            Weather weather = new Weather(desc, currentTemp, currentmin, currentmax);
            mListener.onWeatherTaskCompleted(weather);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
