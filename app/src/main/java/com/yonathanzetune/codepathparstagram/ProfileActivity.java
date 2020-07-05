package com.yonathanzetune.codepathparstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.parse.ParseUser;
import com.yonathanzetune.codepathparstagram.fragments.ProfileFragment;

import org.parceler.Parcels;

public class ProfileActivity extends AppCompatActivity {


    public static final String TAG = "ProfileActivity";
    public static final String PROFILE_EXTRA_USER = "PROFILE_EXTRA_USER";


    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ParseUser parseUser = (ParseUser) Parcels.unwrap(getIntent().getParcelableExtra(PROFILE_EXTRA_USER));

        if(parseUser != null){
            Log.i(TAG, "onCreate: PARSE USER EXISTS");


        }


        Fragment fragment = new ProfileFragment(false, parseUser);
        fragmentManager.beginTransaction().replace(R.id.profContainer, fragment).commit();

    }
}