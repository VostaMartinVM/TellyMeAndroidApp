package com.example.tellyme.view.activity;

import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;

import com.example.tellyme.repository.ListRepository;
import com.example.tellyme.repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TwitterAuth extends SignInActivity {

    private FirebaseAuth firebaseAuth;
    private Map<String, Object> user = new HashMap<>();
    private UserRepository userRepository = UserRepository.getInstance();
    private ListRepository listRepository = ListRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
        provider.addCustomParameter("lang", "en");

        Task<AuthResult> pendingResultTask = firebaseAuth.getPendingAuthResult();
        if (pendingResultTask != null) {
            pendingResultTask
                    .addOnSuccessListener(
                            authResult -> {
                                user.put("username", Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName());
                                userRepository.newUser(user, firebaseAuth.getUid());
                                listRepository.defaultLists();
                                Intent intent = new Intent(TwitterAuth.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                ActivityCompat.finishAffinity(TwitterAuth.this);
                            })
                    .addOnFailureListener(
                            e -> e.printStackTrace());
        } else {
            firebaseAuth
                    .startActivityForSignInWithProvider(this, provider.build())
                    .addOnSuccessListener(
                            authResult -> {
                                user.put("username", Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName());
                                userRepository.newUser(user, firebaseAuth.getUid());
                                listRepository.defaultLists();
                                Intent intent = new Intent(TwitterAuth.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                ActivityCompat.finishAffinity(TwitterAuth.this);
                            })
                    .addOnFailureListener(
                            e -> {
                                e.printStackTrace();
                            });

        }
    }
}