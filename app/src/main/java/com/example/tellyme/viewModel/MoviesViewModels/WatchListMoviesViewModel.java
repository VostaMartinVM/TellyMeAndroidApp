package com.example.tellyme.viewModel.MoviesViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tellyme.model.Movie;
import com.example.tellyme.repository.MovieRepository;

import java.util.ArrayList;

public class WatchListMoviesViewModel extends AndroidViewModel {
    MovieRepository movieRepository;

    public WatchListMoviesViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<ArrayList<Movie>> getMovies()
    {
        return movieRepository.getMoviesForSpecificList("Movies");
    }

}