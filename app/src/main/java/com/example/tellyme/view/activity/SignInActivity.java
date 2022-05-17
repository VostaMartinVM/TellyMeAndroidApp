package com.example.tellyme.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.tellyme.Authentication.AuthWithGoogle;
import com.example.tellyme.R;
import com.example.tellyme.network.GoogleClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInActivity extends AppCompatActivity {

    private GoogleClient googleClient;
    private AuthWithGoogle authWithGoogle;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        googleLogIn();
        facebookLogIn();
        twitterLogin();
        buttonsFunctionalities();
    }

    private void buttonsFunctionalities()
    {
        Button loginButton = findViewById(R.id.login_with_email_button);
        Button createAccountButton = findViewById(R.id.create_account_button);
        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        createAccountButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }

    @SuppressWarnings("deprecation")
    private void googleLogIn()
    {
        googleClient = GoogleClient.getInstance(getApplicationContext());
        authWithGoogle = new AuthWithGoogle();
        FloatingActionButton google = findViewById(R.id.google_authentication_button);
        google.setOnClickListener(view -> {
            Intent signInIntent = googleClient.getGoogleSignInClient().getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }


    private void facebookLogIn()
    {
        FloatingActionButton facebook = findViewById(R.id.facebook_authentication_button);
        facebook.setOnClickListener(view -> {
            Intent signInIntent = new Intent(SignInActivity.this, FacebookAuth.class);
            signInIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(signInIntent);
            overridePendingTransition(0,0);
        });
    }

    private void twitterLogin()
    {
        FloatingActionButton twitter = findViewById(R.id.twitter_authentication_button);
        twitter.setOnClickListener(view -> {
            Intent signInIntent = new Intent(SignInActivity.this, TwitterAuth.class);
            signInIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(signInIntent);
            overridePendingTransition(0,0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authWithGoogle.activityResult(this, requestCode, data, RC_SIGN_IN);
    }
}