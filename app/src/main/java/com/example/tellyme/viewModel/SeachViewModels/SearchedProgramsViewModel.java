package com.example.tellyme.viewModel.SeachViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.tellyme.repository.MovieRepository;
import com.example.tellyme.repository.ShowRepository;

import java.util.ArrayList;

public class SearchedProgramsViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private ShowRepository showRepository;
    private MediatorLiveData tvPrograms;

    public SearchedProgramsViewModel(Application application){
        super(application);
        movieRepository = MovieRepository.getInstance();
        showRepository = ShowRepository.getInstance();
        tvPrograms = new MediatorLiveData();
    }

    public LiveData<ArrayList> getSearchedPrograms(String searchedText)
    {
        tvPrograms.addSource(movieRepository.getMoviesBySearchedText(searchedText), movies -> tvPrograms.setValue(movies));
        tvPrograms.addSource(showRepository.getShowsBySearchedText(searchedText), shows -> tvPrograms.setValue(shows));
        return tvPrograms;
    }
}