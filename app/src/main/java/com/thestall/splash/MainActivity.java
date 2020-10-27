package com.thestall.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //loading orange background
        SplashView splashView = new SplashView(this);
        setContentView(splashView);
    }
}