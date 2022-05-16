package com.example.tellyme.viewModel.MoviesViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tellyme.model.Movie;
import com.example.tellyme.repository.MovieRepository;

import java.util.ArrayList;

public class WatchListMoviesViewModel extends AndroidViewModel {
    MovieRepository movieRepository;
    Context context;

    public WatchListMoviesViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<ArrayList<Movie>> getMovies()
    {
        movieRepository.setContext(context);
        return movieRepository.getMoviesForSpecificList("Movies");
    }

    public void setContext(Context context) {
        this.context = context;
    }
}