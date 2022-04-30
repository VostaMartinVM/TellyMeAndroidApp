package com.example.watchtracker.network;

import com.example.watchtracker.BuildConfig;
import com.example.watchtracker.model.ShowRequest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShowServiceInterface {
    @GET("discover/tv?api_key=" + BuildConfig.TMDB_API_KEY +
            "&language=en-US&sort_by=popularity.desc&page=1" +
            "&timezone=America%2FNew_York&include_null_first_air_dates=false" +
            "&with_watch_monetization_types=flatrate&with_status=0&with_type=0")
    Call<ShowRequest> getAllShows();
}
