package com.thestall.splash.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.thestall.splash.ProfileDetailsActivity;
import com.thestall.splash.R;
import com.thestall.splash.SignInActivity;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Button logout;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private CheckBox checkBox;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
//        //final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        logout=(Button)root.findViewById(R.id.logOutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        //onclick listener for "keep user signed in" checkbox
        checkBox = (CheckBox) root.findViewById(R.id.keepSignedCheckbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if(checked){//if checkbox is checked
                    //add user preference as boolean to sharepref
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("CheckBox_Value", checkBox.isChecked());
                    editor.apply();
                    //toast to show change happened
                    Toast.makeText(getContext(), "Keep user signed in.", Toast.LENGTH_SHORT).show();

                } else {//if not checked
                    //add user preference as boolean to sharepref
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("CheckBox_Value", checkBox.isChecked());
                    editor.apply();
                    //toast to show change happened
                    Toast.makeText(getContext(), "Do not keep user signed in.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return root;
    }
}