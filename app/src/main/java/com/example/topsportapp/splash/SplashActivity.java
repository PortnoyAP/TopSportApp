package com.example.topsportapp.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.topsportapp.R;
import com.example.topsportapp.SignIn.SignInActivity;

public class SplashActivity extends AppCompatActivity {

    ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        enabledViewForLogo();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try{
                    sleep(2000);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashActivity.this,SignInActivity.class));
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


    public void init() {
        logoImage = findViewById(R.id.image_view_logo_top_sport);
        logoImage.setAlpha(0f);
        logoImage.setScaleX(0.5f);
        logoImage.setScaleY(0.5f);
        logoImage.setRotationX(90);

    }

    public void enabledViewForLogo() {
            logoImage.animate().alpha(1).setDuration(1000);
            logoImage.animate().scaleX(1.2f).scaleY(1.2f).setDuration(1000);
            logoImage.animate().rotationX(0).setDuration(1000);
    }
}