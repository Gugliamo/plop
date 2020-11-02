package com.thestall.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    private ImageButton backBtn2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notifications);
        getSupportActionBar().hide();

        View background = findViewById(R.id.notificationActivity);
        background.setBackgroundColor(getResources().getColor(R.color.orange));


        backBtn2 = (ImageButton) findViewById(R.id.backBtn2);
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}