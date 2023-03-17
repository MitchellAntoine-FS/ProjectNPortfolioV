package com.fullsail.mitchellantoine_dank_tank;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fullsail.mitchellantoine_dank_tank.fragments.StrainGridFragment;
import com.fullsail.mitchellantoine_dank_tank.helper.StrainGridAdapter;
import com.fullsail.mitchellantoine_dank_tank.helper.StrainHelper;
import com.fullsail.mitchellantoine_dank_tank.object.StrainListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StrainListener {
    private static final String TAG = "MainActivity.TAG";

    private FirebaseAuth mAuth;
    public static boolean loggedIn;

    ArrayList<Strains> strainsArray;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        strainsArray = new ArrayList<>();

        Intent loggedIn_Intent = getIntent();
        loggedIn = loggedIn_Intent.getBooleanExtra(Intent.EXTRA_TEXT, false);

        if (savedInstanceState == null) {
            if (!loggedIn) {
                // Open login/signup page
                Intent logInIntent = new Intent(this, LoginSignupActivity.class);
                startActivity(logInIntent);
            } else {
                // Open main grid fragment
                updateUI();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if current user is logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.i(TAG, "onStart: "+ currentUser);

        if (currentUser != null) {
            currentUser.reload();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (loggedIn) {

            // Check to see if current user is logged in
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Log.i(TAG, "onStart: "+ currentUser);

            if (currentUser != null) {
                loggedIn = true;
                currentUser.reload();
            }else {
                loggedIn = false;
            }

            // Open main grid fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, StrainGridFragment.newInstance(), StrainGridFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.i(TAG, "onQueryTextChange: " + newText);
                StrainGridAdapter adapter =
                        new StrainGridAdapter(getApplicationContext(), getStrains());
                adapter.getFilter().filter(newText);


                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.app_bar_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList<Strains> getStrains() {

        String results = StrainHelper.getJsonFromUri(this);

        strainsArray = StrainHelper.getStrainsFromJson(results);

        return strainsArray;
    }

    @Override
    public void getStrainSelected(Strains strain) {

        Intent detailsIntent = new Intent(getApplicationContext(), StrainDetailsActivity.class);



    }

    private void updateUI() {
        StrainGridFragment fragment = (StrainGridFragment)
                getSupportFragmentManager().findFragmentByTag(StrainGridFragment.TAG);


    }


}










