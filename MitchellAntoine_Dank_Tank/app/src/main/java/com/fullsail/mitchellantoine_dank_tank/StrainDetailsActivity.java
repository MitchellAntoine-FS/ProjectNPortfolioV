package com.fullsail.mitchellantoine_dank_tank;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fullsail.mitchellantoine_dank_tank.fragments.StrainDetailsFragment;
import com.fullsail.mitchellantoine_dank_tank.object.DetailsListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;

public class StrainDetailsActivity extends AppCompatActivity implements DetailsListener {

    Strains strain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strain_details);

        if (savedInstanceState == null) {

            Intent intent = getIntent();
            strain = (Strains) intent.getSerializableExtra(Intent.EXTRA_INTENT);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, StrainDetailsFragment.newInstance(), StrainDetailsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public Strains getStrain() {
        return strain;
    }
}