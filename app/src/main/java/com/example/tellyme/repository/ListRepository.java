package com.example.tellyme.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.tellyme.model.Movie;
import com.example.tellyme.model.Show;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ListRepository {
    private static ListRepository instance;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private MovieRepository movieRepository;
    private ShowRepository showRepository;

    private ArrayList<String> listsNamesHelper;
    private MutableLiveData<ArrayList<String>> listsNames;
    private MediatorLiveData<ArrayList> tvPrograms;

    private String[] defaultLists = {"Shows", "Movies", "Favourites"};

    public static ListRepository getInstance() {
        if (instance == null) {
            instance = new ListRepository();
        }
        return instance;
    }

    private ListRepository() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        listsNames = new MutableLiveData<>();
        tvPrograms = new MediatorLiveData<>();
        movieRepository = MovieRepository.getInstance();
        showRepository = ShowRepository.getInstance();
    }

    public MutableLiveData<ArrayList<String>> getLists() {
        listsNames = new MutableLiveData<>();
        listsNamesHelper = new ArrayList<>();
        getListsHelper();
        return listsNames;
    }

    private void getListsHelper() {
        DocumentReference movieDocumentReference = db.collection(mAuth.getCurrentUser().getUid()).document("Lists");
        movieDocumentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Map<String, Object> namesMap = documentSnapshot.getData();
                        reorderList(namesMap, 0);
                        reorderList(namesMap, 1);
                        reorderList(namesMap, 2);
                        for (Map.Entry<String, Object> entry : namesMap.entrySet()) {
                            if (!Arrays.asList(defaultLists).contains(entry.getKey()))
                            {
                                listsNamesHelper.add(entry.getKey());
                                listsNames.setValue(listsNamesHelper);
                            }
                        }
                    }
                }

            }
        });
    }

    private void reorderList(Map<String, Object> namesMap, int i)
    {
        for (Map.Entry<String, Object> entry : namesMap.entrySet()) {
            if (entry.getKey().matches(defaultLists[i]))
            {
                listsNamesHelper.add(entry.getKey());
                listsNames.setValue(listsNamesHelper);
            }
        }
    }

    public void defaultLists() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> showId = new HashMap<>();
        showId.put("showID", FieldValue.arrayUnion());
        Map<String, Object> movieId = new HashMap<>();
        movieId.put("movieID", FieldValue.arrayUnion());
        Map<String, Object> favouriteId = new HashMap<>();
        favouriteId.put("showID", FieldValue.arrayUnion());
        favouriteId.put("movieID", FieldValue.arrayUnion());
        map.put("Shows", showId);
        map.put("Movies", movieId);
        map.put("Favourites", favouriteId);
        db.collection(mAuth.getCurrentUser().getUid()).document("Lists").set(map, SetOptions.merge());
    }

    public void newList(String listName) {
        Map<String, Object> list = new HashMap<>();
        list.put(listName, FieldValue.arrayUnion());
        db.collection(mAuth.getCurrentUser().getUid()).document("Lists").set(list, SetOptions.merge());
    }

    public MediatorLiveData<ArrayList> getTVProgramsForList(String listName)
    {
        tvPrograms = new MediatorLiveData<>();
        tvPrograms.addSource(showRepository.getShowsForSpecificList(listName), shows -> tvPrograms.setValue(shows));
        tvPrograms.addSource(movieRepository.getMoviesForSpecificList(listName), movies -> tvPrograms.setValue(movies));
        return tvPrograms;
    }

}
