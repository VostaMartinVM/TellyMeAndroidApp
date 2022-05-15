package com.example.tellyme.repository;

import android.content.Context;
import android.text.Html;
import android.text.method.MovementMethod;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.tellyme.BuildConfig;
import com.example.tellyme.model.Movie;
import com.example.tellyme.model.MovieRequest;
import com.example.tellyme.model.Show;
import com.example.tellyme.network.MovieServiceInterface;
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
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository instance;
    private MovieServiceInterface apiService;
    private MutableLiveData<ArrayList<Movie>> movies;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Context context;

    private ArrayList<Long> movieIds;
    private ArrayList<Movie> movieListHelper;

    public MovieRepository() {
        movies = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        apiService = TMDBServiceGenerator.getRetrofitInstance().create(MovieServiceInterface.class);
    }

    public static MovieRepository getInstance() {
        if (instance == null)
        {
            instance = new MovieRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Movie>> getMovies() {
        movies = new MutableLiveData<>();
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

    public MutableLiveData<ArrayList<Movie>> getMoviesBySearchedText(String searchedText){
        movies = new MutableLiveData<>();
        Call<MovieRequest> call = apiService.getMoviesBySearchedText(BuildConfig.TMDB_API_KEY, searchedText);
        call.enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                if (response.body() != null)
                {
                    movies.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {

            }
        });
        return movies;
    }

    public void addMovieToList(String listName, int movieID){
        Map<String, Object> movie = new HashMap<>();
        Map<String, Object> id = new HashMap<>();
        id.put("movieID", FieldValue.arrayUnion(movieID));
        movie.put(listName, id);
        db.collection(mAuth.getCurrentUser().getUid())
                .document("Lists")
                .set(movie, SetOptions.merge());
    }

    public MutableLiveData<ArrayList<Movie>> getMoviesForSpecificList(String listName)
    {
        movies = new MutableLiveData<>();
        DocumentReference docRef = db.collection(mAuth.getCurrentUser().getUid()).document("Lists");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if (document.get(listName) instanceof Map)
                        {
                            Map<String, Object> temp = (Map<String, Object>) document.get(listName);
                            if (temp != null)
                            {
                                movieIds = (ArrayList<Long>) temp.get("movieID");
                                if (movieIds != null)
                                {
                                    Integer[] showIdsArray = new Integer[movieIds.size()];
                                    for (int i = 0; i < movieIds.size(); i++) {
                                        showIdsArray[i] = ((Long) movieIds.get(i)).intValue();
                                    }

                                    movieListHelper = new ArrayList<>();

                                    for (int i = 0; i < showIdsArray.length; i++) {
                                        getSpecificMoviesHelper(showIdsArray[i]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        return movies;
    }

    private void getSpecificMoviesHelper(int id) {
        Call<Movie> call = apiService.getSpecificMovie(id);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieListHelper.add(response.body());
                movies.setValue(movieListHelper);
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
