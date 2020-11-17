package com.thestall.splash.ui.post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.thestall.splash.R;

public class CreatePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove top bar with title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_create_post);

        findViewById(R.id.backBtnCreatePost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}