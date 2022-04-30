package com.example.watchtracker.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.watchtracker.model.Show;
import com.example.watchtracker.model.ShowRequest;
import com.example.watchtracker.network.ShowServiceInterface;
import com.example.watchtracker.network.TMDBServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRepository {
    private static ShowRepository instance;
    private final MutableLiveData<ArrayList<Show>> shows;


    public ShowRepository() {
        shows = new MutableLiveData<>();
    }

    public static ShowRepository getInstance() {
        if (instance == null)
        {
            instance = new ShowRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Show>> getShows() {
        ShowServiceInterface apiService = TMDBServiceGenerator.getRetrofitInstance().create(ShowServiceInterface.class);
        Call<ShowRequest> call = apiService.getAllShows();
        call.enqueue(new Callback<ShowRequest>() {
            @Override
            public void onResponse(Call<ShowRequest> call, Response<ShowRequest> response) {
                shows.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ShowRequest> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return shows;
    }

}
