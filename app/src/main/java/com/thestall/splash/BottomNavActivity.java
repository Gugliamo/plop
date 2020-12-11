package com.thestall.splash;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;

public class BottomNavActivity extends AppCompatActivity {

    private Button profileDetails;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove top bar with title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_bottom_nav);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_profile, R.id.navigation_map, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        if(mAuth.getCurrentUser() == null) {
            navView.getMenu().findItem(R.id.navigation_profile).setEnabled(false);
            navView.getMenu().findItem(R.id.navigation_profile).getIcon().setTint(Color.argb(255, 192, 192, 192));
        }

        /*
        //catch the profile details fragment
        //final Fragment details = getFragmentManager().findFragmentById(R.id.location_details);
        FragmentManager fm = getSupportFragmentManager();
        Fragment detailsFragment = fm.findFragmentByTag("MapDetails");


        //onclick listener for end point imageButton
        findViewById(R.id.endLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getChildFragmentManager();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(details)
                        .commit();

            }
        });

*/

    }

}