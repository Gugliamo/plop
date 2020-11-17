package com.thestall.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ProfileDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove top bar with title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile_details);
    }
}