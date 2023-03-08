package com.fullsail.mitchellantoine_dank_tank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fullsail.mitchellantoine_dank_tank.fragments.LoginSighupFragment;

public class LoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_signup_fragment_container, LoginSighupFragment.newInstance(), LoginSighupFragment.TAG)
                .commit();
    }
}