package com.yonathanzetune.codepathparstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";


    EditText usernameTv;
    EditText passwordTv;
    Button signInButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity();
        }
        usernameTv = findViewById(R.id.tvUsername);
        passwordTv = findViewById(R.id.tvPassword);
        signInButton = (Button) findViewById(R.id.signInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTv.getText().toString();
                String password = passwordTv.getText().toString();
                loginWithUser(username, password);


            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTv.getText().toString();
                String password = passwordTv.getText().toString();
                signUpUser(username, password);


            }
        });

    }

    private void loginWithUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Parse Login Error: " + e.toString());
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                } else {
                    goToMainActivity();

                }
            }
        });

    }

    private void signUpUser(String username, String password) {
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername(username);
        user.setPassword(password);
// Set custom properties
// Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    goToMainActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Sign up unsucessfull", Toast.LENGTH_SHORT).show();
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Parse Login Error: " + e.toString());
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                } else {
                    goToMainActivity();

                }
            }
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}