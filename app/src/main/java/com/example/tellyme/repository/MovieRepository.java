package com.example.tellyme.repository;

import android.content.Context;
import android.text.Html;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.tellyme.model.Movie;
import com.example.tellyme.model.MovieRequest;
import com.example.tellyme.network.MovieServiceInterface;
import com.example.tellyme.network.TMDBServiceGenerator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository instance;
    private final MutableLiveData<ArrayList<Movie>> movies;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Context context;

    public MovieRepository() {
        movies = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public static MovieRepository getInstance() {
        if (instance == null)
        {
            instance = new MovieRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Movie>> getMovies() {
        MovieServiceInterface apiService = TMDBServiceGenerator.getRetrofitInstance().create(MovieServiceInterface.class);
        Call<MovieRequest> call = apiService.getAllMovies();
        call.enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                movies.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return movies;
    }

    public void addMovieToList(String listName, int movieID){
        Map<String, Object> movie = new HashMap<>();
        movie.put(listName, FieldValue.arrayUnion(movieID));
        db.collection(mAuth.getCurrentUser().getUid())
                .document("Movie lists")
                .set(movie, SetOptions.merge())
                .addOnSuccessListener(documentReference -> Toast.makeText(context, Html.fromHtml("<big>Successfully added</big>"), Toast.LENGTH_SHORT).show())
                .addOnFailureListener(documentReference ->  Toast.makeText(context, Html.fromHtml("<big>Error, could not be added</big>"), Toast.LENGTH_SHORT));
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
