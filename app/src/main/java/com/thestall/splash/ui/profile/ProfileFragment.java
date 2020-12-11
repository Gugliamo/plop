package com.thestall.splash.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thestall.splash.ProfileDetailsActivity;
import com.thestall.splash.R;
import com.thestall.splash.SignInActivity;

import com.google.firebase.auth.*;
import com.google.firebase.database.*;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference usersRef = database.getReference("users");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        root.findViewById(R.id.detailsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileDetailsActivity.class);
                startActivity(intent);
            }
        });
        final TextView profileName = (TextView) root.findViewById(R.id.name_text);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())) {
                    Profile profile = dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue(Profile.class);
                    profileName.setText(profile.name);
                } else {
                    profileName.setText(R.string.no_name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
        return root;
    }


}