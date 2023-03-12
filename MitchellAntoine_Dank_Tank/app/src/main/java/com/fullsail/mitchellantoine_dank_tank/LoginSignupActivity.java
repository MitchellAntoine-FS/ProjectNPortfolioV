package com.fullsail.mitchellantoine_dank_tank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fullsail.mitchellantoine_dank_tank.fragments.LoginSighupFragment;

public class LoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        // Verify Age
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("AGE VERIFICATION");
        dialog.setMessage("Are you 21 years old or older?");

        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Open Login/Signup page
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.login_signup_fragment_container,
                                LoginSighupFragment.newInstance(), LoginSighupFragment.TAG)
                        .commit();
            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), RejectionActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();

    }

}