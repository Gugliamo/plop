package com.thestall.splash;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.thestall.splash.ui.profile.Profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class ProfileDetailsActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference usersRef = database.getReference("users");
    private EditText nameText, phoneText, ageText;
    private Switch privacySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove top bar with title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile_details);
        nameText = (EditText) findViewById(R.id.ProfDetailName);
        phoneText = (EditText) findViewById(R.id.ProfDetailPhone);
        ageText = (EditText) findViewById(R.id.ProfDetailAge);
        privacySwitch = (Switch) findViewById(R.id.ProfDetailPrivacySwitch);

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())) {
                    Profile profile = dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue(Profile.class);
                    nameText.setText(profile.name);
                    phoneText.setText(profile.phone);
                    if(profile.age != -1) {
                        ageText.setText(Integer.toString(profile.age));
                    }
                    else {
                        ageText.setText("");
                    }
                    privacySwitch.setChecked(profile.isPrivate);
                } else {
                    nameText.setText("");
                    phoneText.setText("");
                    ageText.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
        Button updateButton = (Button) findViewById(R.id.updateDetails);
        updateButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                usersRef.child(mAuth.getCurrentUser().getUid()).setValue(new Profile(nameText.getText().toString(), phoneText.getText().toString(), Integer.parseInt(ageText.getText().toString()), privacySwitch.isChecked()));
                                            }
                                        }
        );
    }
}