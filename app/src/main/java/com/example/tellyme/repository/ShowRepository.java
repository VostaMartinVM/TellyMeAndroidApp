package com.example.tellyme.repository;

import android.content.Context;
import android.text.Html;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.tellyme.model.Show;
import com.example.tellyme.model.ShowRequest;
import com.example.tellyme.network.ShowServiceInterface;
import com.example.tellyme.network.TMDBServiceGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRepository {
    private static ShowRepository instance;
    private final MutableLiveData<ArrayList<Show>> shows;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Context context;

    private Map<String, Object> showIds;

    public ShowRepository() {
        shows = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        showIds = new HashMap<>();
    }

    public static ShowRepository getInstance() {
        if (instance == null)
        {
            instance = new ShowRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Show>> getShows() {
        ShowServiceInterface apiService = TMDBServiceGenerator.getRetrofitInstance().create(ShowServiceInterface.class);
        Call<ShowRequest> call = apiService.getAllShows();
        call.enqueue(new Callback<ShowRequest>() {
            @Override
            public void onResponse(Call<ShowRequest> call, Response<ShowRequest> response) {
                shows.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ShowRequest> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return shows;
    }

    public void addShowToList(String listName, int showID){
        Map<String, Object> show = new HashMap<>();
        show.put(listName, FieldValue.arrayUnion(showID));
        db.collection(mAuth.getCurrentUser().getUid())
                .document("Show lists")
                .set(show, SetOptions.merge())
                .addOnSuccessListener(documentReference -> Toast.makeText(context, Html.fromHtml("<big>Successfully added</big>"), Toast.LENGTH_SHORT).show())
                .addOnFailureListener(documentReference ->  Toast.makeText(context, Html.fromHtml("<big>Error, could not be added</big>"), Toast.LENGTH_SHORT));
    }

    public MutableLiveData<ArrayList<Show>> getShowsForSpecificList(String listName)
    {
        DocumentReference docRef = db.collection(mAuth.getCurrentUser().getUid()).document("Show lists");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        showIds = document.getData();
                        System.out.println(showIds.get(listName));
                    }
                }
            }
        });
        return shows;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
