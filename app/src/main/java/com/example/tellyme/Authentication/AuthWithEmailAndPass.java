package com.example.tellyme.Authentication;

import android.app.Activity;
import android.content.Intent;

import androidx.core.app.ActivityCompat;

import com.example.tellyme.repository.ListRepository;
import com.example.tellyme.repository.UserRepository;
import com.example.tellyme.view.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class AuthWithEmailAndPass {
    private FirebaseAuth mAuth;
    private UserRepository userRepository;
    private ListRepository listRepository;

    public AuthWithEmailAndPass() {
        mAuth = FirebaseAuth.getInstance();
        userRepository = UserRepository.getInstance();
        listRepository = ListRepository.getInstance();
    }

    public void createAccount(Activity activity, String email, String username, String password, String confirmationPass)
    {
        if (validate(username, password, confirmationPass))
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, task -> {
                        if (task.isSuccessful()) {
                            HashMap<String, Object> user = new HashMap<>();
                            user.put("email", email);
                            user.put("username", username);
                            userRepository.newUser(user, task.getResult().getUser().getUid());
                            listRepository.defaultLists();
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
                Intent intent = new Intent(activity, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                ActivityCompat.finishAffinity(activity);
            }
        });
    }
}
