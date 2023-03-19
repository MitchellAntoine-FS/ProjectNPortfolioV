package com.fullsail.mitchellantoine_dank_tank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.fullsail.mitchellantoine_dank_tank.fragments.ProfileFragment;
import com.fullsail.mitchellantoine_dank_tank.object.ProfileListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.fullsail.mitchellantoine_dank_tank.util.FileUtility;

import java.io.File;

public class ProfileActivity extends AppCompatActivity implements ProfileListener {
    private static final String TAG = "ProfileActivity";
    public static final String IMAGE_FOLDER = "image";
    public static final String IMAGE_NAME = "profile.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.profile_container, ProfileFragment.newInstance(), ProfileFragment.TAG)
                    .commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        ProfileFragment fragment = (ProfileFragment) getSupportFragmentManager()
                .findFragmentByTag(ProfileFragment.TAG);
        if (fragment != null) {
            fragment.refresh();
        }
    }

    @Override
    public void getStrainSelected(Strains strain) {

        Intent profileDetailsIntent = new Intent(getApplicationContext(), ProfileDetailsActivity.class);
        profileDetailsIntent.putExtra(Intent.EXTRA_INTENT, strain);
        startActivity(profileDetailsIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.camera_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_take_picture) {

           takePicture();
        }
        return true;
    }

    private void takePicture() {

        // Get Image file
        File file = FileUtility.createImageFile(this,IMAGE_NAME, IMAGE_FOLDER);

        // Get image Uri
        Uri imageUri = null;
        if (file != null) {
            Log.i(TAG, "takePicture: File Path: " + file.getAbsolutePath());
            imageUri = FileProvider.getUriForFile(this, "com.fullsail.mitchellantoine_dank_tank", file);

        }
        Log.i(TAG, "takePicture: Image Uri: " + imageUri);

        // Start camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        ActivityCompat.startActivityForResult(this, intent, 0, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView iv = findViewById(R.id.profile_image);

        // Get image file reference
        File imageFile = FileUtility.getImageFileReference(this, IMAGE_NAME, IMAGE_FOLDER);
        // Get image uri
        Uri imageUri = FileProvider.getUriForFile(this, "com.fullsail.mitchellantoine_dank_tank", imageFile);
        // Assign image to profile
        iv.setImageURI(imageUri);
    }
}