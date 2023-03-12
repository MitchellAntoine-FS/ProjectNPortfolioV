package com.fullsail.mitchellantoine_dank_tank;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fullsail.mitchellantoine_dank_tank.fragments.StrainGridFragment;
import com.fullsail.mitchellantoine_dank_tank.helper.StrainHelper;
import com.fullsail.mitchellantoine_dank_tank.object.StrainListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StrainListener {
    private static final String TAG = "MainActivity.TAG";

    private FirebaseAuth mAuth;
    public static boolean loggedIn;

    ArrayList<Strains> strainsArray;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        strainsArray = new ArrayList<>();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Intent loggedIn_Intent = getIntent();
        loggedIn = loggedIn_Intent.getBooleanExtra(Intent.EXTRA_TEXT, false);

        if (!loggedIn) {
            // Open login/signup page
            Intent intent = new Intent(this, LoginSignupActivity.class);
            startActivity(intent);
        } else {
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
            currentUser.reload();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (loggedIn) {

            // Check to see if current user is logged in
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Log.i(TAG, "onStart: "+ currentUser);

            if (currentUser != null) {
                loggedIn = true;
                currentUser.reload();
            }else {
                loggedIn = false;
            }

            // Open main grid fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, StrainGridFragment.newInstance(), StrainGridFragment.TAG)
                    .commit();
        }
    }

    @Override
    public ArrayList<Strains> getStrains() {

        String results = StrainHelper.getJsonFromUri(this);

        strainsArray = StrainHelper.getStrainsFromJson(results);

        return strainsArray;
    }

    @Override
    public Strains getStrainPosition(int position) {


        return null;
    }



}










