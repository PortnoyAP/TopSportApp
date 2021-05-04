package com.example.topsportapp.clubs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topsportapp.R;
import com.example.topsportapp.main.LocalJSONFile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.topsportapp.signIn.SignInActivity.PREF_EMAIL_USER;
import static com.example.topsportapp.signIn.SignInActivity.PREF_NAME;

public class AddClubFormActivity extends AppCompatActivity {
    private static final String TAG = "AddClubFormActivity";
    private static final String USERS = "USERS";
    private static final String USERS_CLUBS = "USERS_CLUBS";


    private SharedPreferences sharedPreferences;
    private FirebaseFirestore db;
    private Club club;
    private LocalJSONFile localJSONFile;

    private TextInputLayout city, sportStyle, clubName, address, email, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_club_form);
        init();
    }

    public void init() {
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        db = FirebaseFirestore.getInstance();
        localJSONFile = new LocalJSONFile();

        city = findViewById(R.id.text_input_city);
        sportStyle = findViewById(R.id.text_input_sport_style);
        clubName = findViewById(R.id.text_input_club_name);
        address = findViewById(R.id.text_input_address);
        email = findViewById(R.id.text_input_email);
        phoneNumber = findViewById(R.id.text_input_phone_number);
    }

    public void addNewClub(View view) {

        club = new Club(city.getEditText().getText().toString(), sportStyle.getEditText().getText().toString()
                , clubName.getEditText().getText().toString(), address.getEditText().getText().toString()
                , email.getEditText().getText().toString(), phoneNumber.getEditText().getText().toString());

        String actualUserEmail = sharedPreferences.getString(PREF_EMAIL_USER, "NO_HAVE_EMAIL");

        db.collection(club.getCity().toUpperCase()).document(club.getSportStyle().toUpperCase())
                .collection(USERS).document(actualUserEmail).collection(USERS_CLUBS).document(club.getClubName()).set(club)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddClubFormActivity.this, "Club Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddClubFormActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
            }
        });


    }

}