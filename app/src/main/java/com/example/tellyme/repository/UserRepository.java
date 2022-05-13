package com.example.tellyme.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class UserRepository {
    private static UserRepository instance;
    private FirebaseFirestore db;

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
    }
}
