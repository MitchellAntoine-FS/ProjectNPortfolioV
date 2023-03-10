package com.fullsail.mitchellantoine_dank_tank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fullsail.mitchellantoine_dank_tank.fragments.LoginFragment;
import com.fullsail.mitchellantoine_dank_tank.object.LogInListener;

public class LoginActivity extends AppCompatActivity implements LogInListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_container, LoginFragment.newInstance(), LoginFragment.TAG)
                .commit();
    }

    @Override
    public void closeLogIn() {
        finish();
    }
}