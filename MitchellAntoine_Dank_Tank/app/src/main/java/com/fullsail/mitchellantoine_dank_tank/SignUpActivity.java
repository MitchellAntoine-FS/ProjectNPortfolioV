package com.fullsail.mitchellantoine_dank_tank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fullsail.mitchellantoine_dank_tank.fragments.SignupFragment;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.signup_container, SignupFragment.newInstance(), SignupFragment.TAG)
                .commit();
    }
}