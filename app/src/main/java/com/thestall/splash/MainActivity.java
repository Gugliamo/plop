package com.thestall.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button profileDetails;

    //length of splash screen
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove top bar with title
        getSupportActionBar().hide();

        //loading splash screen
        SplashView splashView = new SplashView(this);
        setContentView(splashView);

        //switch to SignInActivity with intent after
        //predetermined amount of seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);




    }
}