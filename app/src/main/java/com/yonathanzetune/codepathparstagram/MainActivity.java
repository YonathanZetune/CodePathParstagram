package com.yonathanzetune.codepathparstagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.yonathanzetune.codepathparstagram.fragments.ComposeFragment;
import com.yonathanzetune.codepathparstagram.fragments.PostsFragment;
import com.yonathanzetune.codepathparstagram.fragments.ProfileFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    BottomNavigationView navBar;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.mainToolbar);
        navBar = findViewById(R.id.bottom_navigation);
        navBar.setBackgroundColor(getResources().getColor(R.color.white));

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.profileMi:
//                        Toast.makeText(MainActivity.this, "profileMi", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                    case R.id.composeMi:
//                        Toast.makeText(MainActivity.this, "composeMi", Toast.LENGTH_SHORT).show();

                        fragment = new ComposeFragment();
                        break;
                    case R.id.homeMi:
                    default:
//                        Toast.makeText(MainActivity.this, "homeMi", Toast.LENGTH_SHORT).show();

                        fragment = new PostsFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        navBar.setSelectedItemId(R.id.homeMi);

    }


}