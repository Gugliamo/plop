package com.thestall.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {

    private TextView signUpText;
    private TextView guestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove top bar with title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_in);

        //setting background color to orange
        View background = findViewById(R.id.signInActivity);
        background.setBackgroundColor(getResources().getColor(R.color.orange));

        //onclick listener for createAccount textview
        signUpText = (TextView) findViewById(R.id.createAccount);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send user to signUpActivity
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        //onclick listener for loginAsGuest textview
        guestText = (TextView) findViewById(R.id.loginAsGuest);
        guestText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send user to BottomNavActivity
                Intent intent = new Intent(SignInActivity.this, BottomNavActivity.class);
                startActivity(intent);
            }
        });

    }

}