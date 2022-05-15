package com.example.tellyme.repository;

import androidx.annotation.NonNull;

import com.example.tellyme.Authentication.AuthWithEmailAndPass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static UserRepository instance;
    private FirebaseFirestore db;
    private ArrayList<String> usernames;

    private UserRepository(){
        db = FirebaseFirestore.getInstance();
    }

    public static UserRepository getInstance() {
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public void newUser(Map<String, Object> user, String userID){
        db.collection(userID).document("Personal information").set(user);
        Map<String, Object> username = new HashMap<>();
        username.put("usernames", FieldValue.arrayUnion(user.get("username")));
        db.collection("Users").document("Usernames").set(username, SetOptions.merge());
    }

    public void getAllUsernames(final String key, final OnCompleteCallback callback)
    {
        usernames = new ArrayList<>();
        DocumentReference documentReference = db.collection("Users")
                .document("Usernames");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    callback.onComplete(documentSnapshot.exists(), (ArrayList<String>) documentSnapshot.getData().get("usernames"));
                }
            }
        });
    }

    public interface OnCompleteCallback{
        void onComplete(boolean success, ArrayList<String> usernames);
    }
}
