package com.example.tellyme.viewModel.SeachViewModels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.tellyme.repository.MovieRepository;
import com.example.tellyme.repository.ShowRepository;

import java.util.ArrayList;


@SuppressWarnings("rawtypes")
public class SearchedProgramsViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final MediatorLiveData tvPrograms;
    private Context context;

    public SearchedProgramsViewModel(Application application){
        super(application);
        movieRepository = MovieRepository.getInstance();
        showRepository = ShowRepository.getInstance();
        tvPrograms = new MediatorLiveData();
    }

    @SuppressWarnings("unchecked")
    public LiveData<ArrayList> getSearchedPrograms(String searchedText)
    {
        movieRepository.setContext(context);
        showRepository.setContext(context);
        tvPrograms.addSource(movieRepository.getMoviesBySearchedText(searchedText), tvPrograms::setValue);
        tvPrograms.addSource(showRepository.getShowsBySearchedText(searchedText), tvPrograms::setValue);
        return tvPrograms;
    }

    public void addShowToSpecificList(String listName, int showID)
    {
        showRepository.addShowToList(listName, showID);
    }

    public void addMovieToSpecificList(String listName, int movieID)
    {
        movieRepository.addMovieToList(listName, movieID);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}