package com.example.tellyme.Authentication;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.tellyme.repository.ListRepository;
import com.example.tellyme.repository.UserRepository;
import com.example.tellyme.view.activity.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.HashMap;
import java.util.Map;

public class AuthWithGoogle {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private UserRepository userRepository;
    private ListRepository listRepository;

    public AuthWithGoogle()
    {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        userRepository = UserRepository.getInstance();
        listRepository = ListRepository.getInstance();
    }

    public void activityResult(Activity activity, int requestCode, Intent data, int RC_SIGN_IN) {
        if (requestCode == RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account, activity);
            } catch (ApiException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct, Activity activity)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Map<String, Object> user = new HashMap<>();
                            user.put("email",auth.getCurrentUser().getEmail());
                            user.put("username", auth.getCurrentUser().getDisplayName());
                            userRepository.newUser(user, auth.getUid());
                            listRepository.defaultLists();
                            currentUser = auth.getCurrentUser();
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            activity.startActivity(intent);
                            ActivityCompat.finishAffinity(activity);
                        }
                        else {

                        }
                    }
                });
    }
}
