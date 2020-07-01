package com.yonathanzetune.codepathparstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText usernameTv;
    EditText passwordTv;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameTv = findViewById(R.id.tvUsername);
        passwordTv = findViewById(R.id.tvPassword);
        signInButton = (Button) findViewById(R.id.signInButton);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTv.getText().toString();
                String password = passwordTv.getText().toString();
                loginWithUser(username,password);


            }
        });

    }

    private void loginWithUser(String username, String password) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}