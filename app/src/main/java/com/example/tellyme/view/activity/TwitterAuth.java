package com.example.tellyme.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;

import com.example.tellyme.R;
import com.example.tellyme.repository.UserRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TwitterAuth extends SignInActivity {

    private FirebaseAuth firebaseAuth;

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
                                Map<String, Object> user = new HashMap<>();
                                user.put("username", Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName());
                                UserRepository userRepository = UserRepository.getInstance();
                                userRepository.newUser(user, firebaseAuth.getUid());
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
                                Map<String, Object> user = new HashMap<>();
                                user.put("username", Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName());
                                UserRepository userRepository = UserRepository.getInstance();
                                userRepository.newUser(user, firebaseAuth.getUid());
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