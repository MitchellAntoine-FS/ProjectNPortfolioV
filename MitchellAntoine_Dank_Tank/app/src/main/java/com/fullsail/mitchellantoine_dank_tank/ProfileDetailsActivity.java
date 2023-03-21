package com.fullsail.mitchellantoine_dank_tank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fullsail.mitchellantoine_dank_tank.fragments.ProfileDetailsFragment;
import com.fullsail.mitchellantoine_dank_tank.fragments.ProfileFragment;
import com.fullsail.mitchellantoine_dank_tank.object.ProfileDetailsListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.fullsail.mitchellantoine_dank_tank.util.FavoriteStorageUtil;

public class ProfileDetailsActivity extends AppCompatActivity implements ProfileDetailsListener {
    public static final String TAG = "ProfileDetailsActivity";
    public static final String EXTRA_POSITION = "com.fullsail.mitchellantoine_dank_tank.EXTRA_POSITION";
    Strains strain;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        if (savedInstanceState == null) {

            Intent intent = getIntent();
            strain = (Strains) intent.getSerializableExtra(Intent.EXTRA_INTENT);
            position = intent.getIntExtra(EXTRA_POSITION, 0);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.profile_details_container, ProfileDetailsFragment.newInstance(), ProfileDetailsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {

            Log.i(TAG, "onOptionsItemSelected: " + strain);

            FavoriteStorageUtil.deleteStrain(this, position);

            ProfileFragment fragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(ProfileFragment.TAG);
            if (fragment != null) {
                fragment.refresh();
            }
            finish();
        }

        return true;
    }

    @Override
    public Strains getStrain() {
        return strain;
    }

}