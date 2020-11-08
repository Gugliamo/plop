package com.thestall.splash.ui.post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.thestall.splash.R;

public class CreatePost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_post);
    }
}