package com.example.topsportapp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.topsportapp.R;
import com.example.topsportapp.clubs.MyClubsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toMyClubsActivity(View view) {
        startActivity(new Intent(this, MyClubsActivity.class));
    }
}