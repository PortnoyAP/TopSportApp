package com.example.topsportapp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.topsportapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toMyClubsActivity(View view) {
        Toast.makeText(this, "TO MY CLUBS", Toast.LENGTH_SHORT).show();
    }
}