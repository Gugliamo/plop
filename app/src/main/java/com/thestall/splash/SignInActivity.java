package com.thestall.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {

    private TextView signUpText;
    private TextView guestText;
    private Button signIn;
    private FirebaseAuth mAuth;
    private EditText signInEmail;
    private EditText signInPass;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //remove top bar with title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        signInEmail = (EditText) findViewById(R.id.SignInEmailAddress);
        signInPass = (EditText) findViewById(R.id.SignInTextPassword);

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

        //onclick listener for signinButton
        signIn = (Button) findViewById(R.id.signinButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send user to bottomNavActivity
                signIn(signInEmail.getText().toString(),
                        signInPass.getText().toString());

            }
        });

        final SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        //onclick listener for "keep user signed in" checkbox
        checkBox = (CheckBox) findViewById(R.id.keepSignedCheckbox);
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
                    Toast.makeText(SignInActivity.this, "Keep user signed in.", Toast.LENGTH_SHORT).show();

                } else {//if not checked
                    //add user preference as boolean to sharepref
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("CheckBox_Value", checkBox.isChecked());
                    editor.apply();
                    //toast to show change happened
                    Toast.makeText(SignInActivity.this, "Do not keep user signed in.", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    private void signIn(String email, String password) {



        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignInActivity.this,BottomNavActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (!task.isSuccessful()) {

                        }

                    }
                });
        // [END sign_in_with_email]
    }

}