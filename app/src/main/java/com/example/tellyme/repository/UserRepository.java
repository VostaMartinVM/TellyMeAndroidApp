package com.example.tellyme.repository;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRepository {
    private static UserRepository instance;
    private final FirebaseFirestore db;

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

    @SuppressWarnings("unchecked")
    public void getAllUsernames(final OnCompleteCallback callback)
    {
        DocumentReference documentReference = db.collection("Users")
                .document("Usernames");
        documentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                DocumentSnapshot documentSnapshot = task.getResult();
                callback.onComplete(documentSnapshot.exists(),
                        (ArrayList<String>) Objects.requireNonNull(documentSnapshot.getData()).get("usernames"));
            }
        });
    }

    public interface OnCompleteCallback{
        void onComplete(boolean success, ArrayList<String> usernames);
    }
}
