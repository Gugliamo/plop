package com.thestall.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private EditText email;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.SignInEmailAddress);
        pass = (EditText) findViewById(R.id.SignInTextPassword);

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

        //onclick listener for signinButton
        signIn = (Button) findViewById(R.id.signinButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send user to bottomNavActivity
//                signIn(email.getText().toString(),
//                        pass.getText().toString());
                //Toast.makeText(SignInActivity.this,pass.getText().toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(SignInActivity.this,email.getText().toString(),Toast.LENGTH_SHORT).show();

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
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignInActivity.this,BottomNavActivity.class);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            // [START_EXCLUDE]
                            //checkForMultiFactorFailure(task.getException());
                            // [END_EXCLUDE]
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            //mBinding.status.setText(R.string.auth_failed);
                        }
                        //hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

}