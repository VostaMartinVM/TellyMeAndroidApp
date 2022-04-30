package com.example.watchtracker.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.watchtracker.model.Movie;
import com.example.watchtracker.model.MovieRequest;
import com.example.watchtracker.model.ShowRequest;
import com.example.watchtracker.network.MovieServiceInterface;
import com.example.watchtracker.network.ShowServiceInterface;
import com.example.watchtracker.network.TMDBServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository instance;
    private final MutableLiveData<ArrayList<Movie>> movies;


    public MovieRepository() {
        movies = new MutableLiveData<>();
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

}
