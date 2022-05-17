package com.example.tellyme.viewModel.ShowsViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tellyme.model.Show;
import com.example.tellyme.repository.ShowRepository;

import java.util.ArrayList;

public class WatchListShowsViewModel extends AndroidViewModel {

    ShowRepository showRepository;
    Context context;

    public WatchListShowsViewModel(@NonNull Application application) {
        super(application);
        showRepository = ShowRepository.getInstance();
    }

    public LiveData<ArrayList<Show>> getShows()
    {
        showRepository.setContext(context);
        return showRepository.getShowsForSpecificList("Shows");
    }

    public void setContext(Context context) {
        this.context = context;
    }
}