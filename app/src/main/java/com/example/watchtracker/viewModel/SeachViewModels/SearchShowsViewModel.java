package com.example.watchtracker.viewModel.SeachViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.watchtracker.model.Show;
import com.example.watchtracker.model.ShowRequest;
import com.example.watchtracker.network.ShowServiceInterface;
import com.example.watchtracker.network.TMDBServiceGenerator;
import com.example.watchtracker.repository.ShowRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchShowsViewModel extends AndroidViewModel {
    ShowRepository showRepository;

   public SearchShowsViewModel (Application application)
   {
       super(application);
       showRepository = ShowRepository.getInstance();
   }

   public LiveData<ArrayList<Show>> getShows()
   {
       return showRepository.getShows();
   }
}