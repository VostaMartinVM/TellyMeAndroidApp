package com.example.watchtracker.network;

import com.example.watchtracker.BuildConfig;
import com.example.watchtracker.model.MovieRequest;
import com.example.watchtracker.model.ShowRequest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieServiceInterface {
    @GET("discover/movie?api_key=" + BuildConfig.TMDB_API_KEY +
            "&language=en-US&sort_by=popularity.desc&include_adult=false&" +
            "include_video=false&page=1&with_watch_monetization_types=flatrate")
    Call<MovieRequest> getAllMovies();
}
