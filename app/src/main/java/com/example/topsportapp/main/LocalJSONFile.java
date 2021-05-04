package com.example.topsportapp.main;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LocalJSONFile extends AppCompatActivity {

    private String citiesFromLocalJson;
    private ArrayList<String> citiesList;
    private String languageSystem;

    public LocalJSONFile() {
        citiesList = new ArrayList<>();
    }



    public void getListOfCitiesFromLocalJson() {

        try {
            InputStream inputStream = getAssets().open("cities.json");
            int size = inputStream.available();
            byte[] buffer =new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            citiesFromLocalJson = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(citiesFromLocalJson);

            for(int i = 0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                citiesList.add(object.getString("cityName"));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCitiesList() {
        return citiesList;
    }
}
