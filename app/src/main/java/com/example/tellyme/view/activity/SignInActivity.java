package com.example.tellyme.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tellyme.Authentication.AuthWithGoogle;
import com.example.tellyme.R;
import com.example.tellyme.network.GoogleClient;
import com.facebook.CallbackManager;
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
        CallbackManager mCallBackManager = CallbackManager.Factory.create();
        FloatingActionButton facebookButton = findViewById(R.id.facebook_authentication_button);

    }

    private void googleLogIn()
    {
        googleClient = GoogleClient.getInstance(getApplicationContext());
        authWithGoogle = new AuthWithGoogle();
        FloatingActionButton google = findViewById(R.id.google_authentication_button);
        google.setOnClickListener(view -> {
            signIn();
        });
    }

    @SuppressWarnings("deprecation")
    private void signIn()
    {
        Intent signInIntent = googleClient.getGoogleSignInClient().getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authWithGoogle.activityResult(this, requestCode, data, RC_SIGN_IN);

    }
}