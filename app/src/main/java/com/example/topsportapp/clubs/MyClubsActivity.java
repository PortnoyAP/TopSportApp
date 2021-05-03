package com.example.topsportapp.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.topsportapp.R;

public class MyClubsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clubs);
    }

    public void toClubFormActivity(View view) {
        startActivity(new Intent(this, AddClubFormActivity.class));
    }
}