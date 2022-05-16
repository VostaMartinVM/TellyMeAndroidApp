package com.example.tellyme.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.tellyme.R;
import com.example.tellyme.repository.ListRepository;
import com.example.tellyme.repository.UserRepository;
import com.example.tellyme.view.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

public class AuthWithEmailAndPass {
    private FirebaseAuth mAuth;
    private UserRepository userRepository;
    private ListRepository listRepository;
    private TextView errorMsgCreateAccount;
    private Boolean isValid;

    public AuthWithEmailAndPass() {
        mAuth = FirebaseAuth.getInstance();
        userRepository = UserRepository.getInstance();
        listRepository = ListRepository.getInstance();
        isValid = true;
    }

    private void createAccount(Activity activity, String email, String username, String password)
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
                            System.out.println(task.getException().getMessage());
                            if (task.getException().getMessage()
                                    .matches("The email address is already in use by another account"));
                            {
                                errorMsgCreateAccount.setText("This email is already taken");
                                errorMsgCreateAccount.setVisibility(View.VISIBLE);
                            }
                        }
                    });
    }

    public void validate(Activity activity, String email, String username, String password,
                         String confirmationPass, TextView errorMsgCreateAccount)
    {
        this.errorMsgCreateAccount = errorMsgCreateAccount;
        isValid = true;

        userRepository.getAllUsernames("usernames", new UserRepository.OnCompleteCallback() {
            @Override
            public void onComplete(boolean success, ArrayList<String> usernames) {
             if (success)
             {
                 for (int i = 0; i < usernames.size(); i++) {
                     if (username.matches(usernames.get(i)))
                     {
                         errorMsgCreateAccount.setText("Username already exists");
                         errorMsgCreateAccount.setVisibility(View.VISIBLE);
                         isValid = false;
                     }
                 }
                 if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmationPass.isEmpty()) {
                     errorMsgCreateAccount.setText("All fields must be filled");
                     errorMsgCreateAccount.setVisibility(View.VISIBLE);
                     isValid = false;
                 }

                 if (!password.equals(confirmationPass)){
                     errorMsgCreateAccount.setText("Password and confirm password must match");
                     errorMsgCreateAccount.setVisibility(View.VISIBLE);
                     isValid = false;
                 }
                 if (password.length() < 6)
                 {
                     errorMsgCreateAccount.setText("Your password must be at least 6 characters long");
                     errorMsgCreateAccount.setVisibility(View.VISIBLE);
                     isValid = false;
                 }
                 if (isValid)
                 {
                     createAccount(activity, email, username, password);
                 }
             }
            }
        });
    }


    public void login(Activity activity, String email, String password, TextView errorMsgLogin)
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                Intent intent = new Intent(activity, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                ActivityCompat.finishAffinity(activity);
                errorMsgLogin.setVisibility(View.INVISIBLE);
            }
            else
            {
                errorMsgLogin.setText(task.getException().getMessage());
                errorMsgLogin.setVisibility(View.VISIBLE);
            }


        });
    }
}
