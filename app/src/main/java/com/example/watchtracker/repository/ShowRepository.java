package com.example.watchtracker.repository;

import android.content.Context;
import android.text.Html;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.watchtracker.model.Show;
import com.example.watchtracker.model.ShowRequest;
import com.example.watchtracker.network.ShowServiceInterface;
import com.example.watchtracker.network.TMDBServiceGenerator;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRepository {
    private static ShowRepository instance;
    private final MutableLiveData<ArrayList<Show>> shows;
    private FirebaseFirestore db;
    private Context context;

    public ShowRepository() {
        shows = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
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

    public void addShowToList(int showID){
        Map<String, Object> show = new HashMap<>();
        show.put("showId", showID);
        db.collection("Default Show List")
                .add(show)
                .addOnSuccessListener(documentReference -> Toast.makeText(context, Html.fromHtml("<big>Successfully added</big>"), Toast.LENGTH_SHORT).show())
                .addOnFailureListener(documentReference ->  Toast.makeText(context, Html.fromHtml("<big>Error, could not be added</big>"), Toast.LENGTH_SHORT));
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
