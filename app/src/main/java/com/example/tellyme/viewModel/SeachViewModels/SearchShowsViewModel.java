package com.example.tellyme.viewModel.SeachViewModels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tellyme.model.Show;
import com.example.tellyme.repository.ShowRepository;

import java.util.ArrayList;

public class SearchShowsViewModel extends AndroidViewModel {
    ShowRepository showRepository;
    Context context;

   public SearchShowsViewModel (Application application)
   {
       super(application);
       showRepository = ShowRepository.getInstance();
   }

   public LiveData<ArrayList<Show>> getShows()
   {
       showRepository.setContext(context);
       return showRepository.getShows();
   }

   public void addShowsToSpecificList(String listName, int showId)
   {
        showRepository.addShowToList(listName, showId);
   }

    public void setContext(Context context) {
        this.context = context;
    }
}