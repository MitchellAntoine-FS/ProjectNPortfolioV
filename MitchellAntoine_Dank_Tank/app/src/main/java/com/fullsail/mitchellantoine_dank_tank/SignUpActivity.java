package com.fullsail.mitchellantoine_dank_tank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fullsail.mitchellantoine_dank_tank.fragments.SignupFragment;
import com.fullsail.mitchellantoine_dank_tank.object.SignupListener;

public class SignUpActivity extends AppCompatActivity implements SignupListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.signup_container, SignupFragment.newInstance(), SignupFragment.TAG)
                .commit();
    }

    @Override
    public void closeSignup() {
        finish();
    }
}