package com.example.tellyme.viewModel.SeachViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tellyme.model.Movie;
import com.example.tellyme.repository.MovieRepository;

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

    public void addMovieToSpecificList(String listName, int movieID)
    {
        movieRepository.addMovieToList(listName, movieID);
    }
}