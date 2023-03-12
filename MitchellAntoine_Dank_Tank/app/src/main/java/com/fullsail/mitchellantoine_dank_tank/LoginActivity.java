package com.fullsail.mitchellantoine_dank_tank;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
        boolean loggedIn = true;
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        mainIntent.putExtra(Intent.EXTRA_TEXT, loggedIn);
        startActivity(mainIntent);
        finish();
    }
}