package com.example.watchtracker.viewModel.SeachViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.watchtracker.model.Movie;
import com.example.watchtracker.repository.MovieRepository;

import java.util.ArrayList;

public class SearchMoviesViewModel extends AndroidViewModel {
    MovieRepository movieRepository;

    public SearchMoviesViewModel (Application application)
    {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<ArrayList<Movie>> getMovies()
    {
        return movieRepository.getMovies();
    }
}