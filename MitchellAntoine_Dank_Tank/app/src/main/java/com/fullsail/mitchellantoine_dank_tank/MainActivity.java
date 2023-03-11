package com.fullsail.mitchellantoine_dank_tank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fullsail.mitchellantoine_dank_tank.fragments.StrainGridFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity.TAG";

    private FirebaseAuth mAuth;
    private boolean loggedIn;

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
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, StrainGridFragment.newInstance(), StrainGridFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if current user is logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.i(TAG, "onStart: "+ currentUser);

        if (currentUser != null) {
            loggedIn = true;
            currentUser.reload();
        }else {
            loggedIn = false;
        }
    }
}