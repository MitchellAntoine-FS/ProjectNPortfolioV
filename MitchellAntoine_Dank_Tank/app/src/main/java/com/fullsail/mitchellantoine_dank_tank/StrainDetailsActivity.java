package com.fullsail.mitchellantoine_dank_tank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fullsail.mitchellantoine_dank_tank.fragments.StrainDetailsFragment;
import com.fullsail.mitchellantoine_dank_tank.object.DetailsListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.fullsail.mitchellantoine_dank_tank.util.FavoriteStorageUtil;

import java.util.ArrayList;

public class StrainDetailsActivity extends AppCompatActivity implements DetailsListener {

    private static final String TAG = "StrainDetailsActivity";

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_save) {

            ArrayList<Strains> savedList = FavoriteStorageUtil.loadStrains(this);

            if (strain != null && !savedList.contains(strain)) {

                Log.i(TAG, "onOptionsItemSelected: " + strain.getName());

                FavoriteStorageUtil.saveStrain(this, strain);

                finish();
            }
        }

        return true;
    }

    @Override
    public Strains getStrain() {
        return strain;
    }
}