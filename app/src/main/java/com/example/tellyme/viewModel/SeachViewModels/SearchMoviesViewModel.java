package com.example.tellyme.viewModel.SeachViewModels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tellyme.model.Movie;
import com.example.tellyme.repository.MovieRepository;

import java.util.ArrayList;

public class SearchMoviesViewModel extends AndroidViewModel {
    MovieRepository movieRepository;
    Context context;

    public SearchMoviesViewModel (Application application)
    {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<ArrayList<Movie>> getMovies()
    {
        movieRepository.setContext(context);
        return movieRepository.getMovies();
    }

    public void addMovieToSpecificList(String listName, int movieID)
    {
        movieRepository.addMovieToList(listName, movieID);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}