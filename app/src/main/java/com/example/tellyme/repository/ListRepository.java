package com.example.tellyme.repository;


import android.content.Context;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

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
import java.util.Objects;

public class ListRepository {
    private static ListRepository instance;
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;

    private ArrayList<String> listsNamesHelper;
    private MutableLiveData<ArrayList<String>> listsNames;
    @SuppressWarnings("rawtypes")
    private MediatorLiveData<ArrayList> tvPrograms;

    private final String[] defaultLists = {"Shows", "Movies", "Favourites"};
    private Context context;

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
        DocumentReference movieDocumentReference = db.collection(Objects.requireNonNull(mAuth.getCurrentUser())
                .getUid()).document("Lists");
        movieDocumentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()) {
                    Map<String, Object> namesMap = documentSnapshot.getData();
                    if (namesMap != null) {
                        reorderList(namesMap, 0);
                        reorderList(namesMap, 1);
                        reorderList(namesMap, 2);
                    }
                    if (namesMap != null) {
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
        db.collection(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                .document("Lists").set(map, SetOptions.merge());
    }

    public void newList(String listName) {
        Map<String, Object> list = new HashMap<>();
        list.put(listName, FieldValue.arrayUnion());
        db.collection(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                .document("Lists").set(list, SetOptions.merge());
    }

    @SuppressWarnings("rawtypes")
    public MediatorLiveData<ArrayList> getTVProgramsForList(String listName)
    {
        tvPrograms = new MediatorLiveData<>();
        showRepository.setContext(context);
        movieRepository.setContext(context);
        tvPrograms.addSource(showRepository.getShowsForSpecificList(listName), shows -> tvPrograms.setValue(shows));
        tvPrograms.addSource(movieRepository.getMoviesForSpecificList(listName), movies -> tvPrograms.setValue(movies));
        return tvPrograms;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
