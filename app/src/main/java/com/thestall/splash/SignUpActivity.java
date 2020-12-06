package com.thestall.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private Button signIn;
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove top bar with title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        //setting up the email and password from signup
        email = (EditText) findViewById(R.id.SignUpEmailAddress);
        pass = (EditText) findViewById(R.id.SignUPTextPassword);

        //setting background color to orange
        View background = findViewById(R.id.signUpActivity);
        background.setBackgroundColor(getResources().getColor(R.color.orange));

        //onclick listener on custom back button
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //onclick listener for signupButton button
        signIn = (Button) findViewById(R.id.signupButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send user to BottomNavActivity
                createAccount(email.getText().toString(),pass.getText().toString());
                Toast.makeText(SignUpActivity.this,email.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    private void createAccount(String email, String password) {

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }
}