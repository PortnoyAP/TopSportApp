package com.example.topsportapp.main;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LocalJSONFile extends Activity {

    private String citiesFromLocalJson;
    private  ArrayList<String> citiesList;
    private String sportTypesFromLocalJson;
    private  ArrayList<String> sportTypesList;

    private String languageSystem;
    private Context appContext;

    public LocalJSONFile() {
        citiesList = new ArrayList<>();
        sportTypesList = new ArrayList<>();
        System.out.println("LocalJsonFile created");
    }




    public List<String> getInfoFromLocalJsonFileCities(Context context) {

        try {
            InputStream inputStream = context.getAssets().open("cities.json");
            int size = inputStream.available();
            byte[] buffer =new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            citiesFromLocalJson = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(citiesFromLocalJson);
            System.out.println("******jsonArray size**************" + jsonArray.length());

            for(int i = 0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                citiesList.add(object.getString("englishName"));
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        System.out.println("!!!!!!!!!!!!!citiesList size!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+citiesList.size());
        return citiesList;
    }

    public List<String> getInfoFromLocalJsonFileSportType(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("sportType.json");
            int size = inputStream.available();
            byte[] buffer =new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            sportTypesFromLocalJson = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(sportTypesFromLocalJson);
            System.out.println("******jsonArray size**************" + jsonArray.length());

            for(int i = 0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                sportTypesList.add(object.getString("sportTypeEN"));
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        System.out.println("!!!!!!!!!!!!!citiesList size!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+sportTypesList.size());
        return sportTypesList;
    }
}
