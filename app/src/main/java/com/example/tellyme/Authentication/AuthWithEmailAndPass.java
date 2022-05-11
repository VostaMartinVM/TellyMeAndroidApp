package com.example.tellyme.Authentication;

import android.app.Activity;
import android.content.Intent;

import androidx.core.app.ActivityCompat;

import com.example.tellyme.view.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AuthWithEmailAndPass {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


    public AuthWithEmailAndPass() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void createAccount(Activity activity, String email, String username, String password, String confirmationPass)
    {
        if (validate(username, password, confirmationPass))
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            HashMap<String, Object> username1 = new HashMap<>();
                            username1.put("email", email);
                            username1.put("username", username);
                            db.collection(task.getResult().getUser().getUid()).document("Personal information").set(username1);
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            activity.startActivity(intent);
                            ActivityCompat.finishAffinity(activity);
                        }
                        else {
                            System.out.println(task.getException());
                        }
                    });
        }
    }

    private boolean validate(String username, String password, String confirmationPass)
    {
        if (!password.equals(confirmationPass)){
            return false;
        }

        return true;
    }

    public void login(Activity activity, String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                Intent intent = new Intent(activity, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                ActivityCompat.finishAffinity(activity);
            }
        });
    }
}
