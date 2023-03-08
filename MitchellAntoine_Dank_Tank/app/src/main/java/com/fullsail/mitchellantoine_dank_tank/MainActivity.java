package com.fullsail.mitchellantoine_dank_tank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity.TAG";

    private FirebaseAuth mAuth;
    private boolean loggedIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if (!loggedIn) {
            // Open login/signup page
            Intent intent = new Intent(this, LoginSignupActivity.class);
            startActivity(intent);
        }else {
            // Open main grid fragment

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if current user is logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            loggedIn = true;
            currentUser.reload();
        }
    }
}