package com.fullsail.mitchellantoine_dank_tank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fullsail.mitchellantoine_dank_tank.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_container, LoginFragment.newInstance(), LoginFragment.TAG)
                .commit();
    }

}