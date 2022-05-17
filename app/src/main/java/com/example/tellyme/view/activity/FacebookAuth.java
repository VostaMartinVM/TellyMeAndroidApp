package com.example.tellyme.view.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;

import com.example.tellyme.repository.ListRepository;
import com.example.tellyme.repository.UserRepository;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FacebookAuth extends SignInActivity {

    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList("public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> user = new HashMap<>();
                        user.put("username", Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());
                        UserRepository userRepository = UserRepository.getInstance();
                        userRepository.newUser(user, mAuth.getUid());
                        ListRepository listRepository = ListRepository.getInstance();
                        listRepository.defaultLists();
                        updateUI();
                    }
                });
    }

    private void updateUI() {
        Intent intent = new Intent(FacebookAuth.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        ActivityCompat.finishAffinity(FacebookAuth.this);
    }
}