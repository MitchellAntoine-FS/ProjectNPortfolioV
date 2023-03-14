package com.fullsail.mitchellantoine_dank_tank;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fullsail.mitchellantoine_dank_tank.fragments.StrainDetailsFragment;

public class StrainDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strain_details);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, StrainDetailsFragment.newInstance(), StrainDetailsFragment.TAG)
                    .commit();
        }
    }
}