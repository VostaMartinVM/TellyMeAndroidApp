package com.example.tellyme.network;

import com.example.tellyme.BuildConfig;
import com.example.tellyme.model.MovieRequest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieServiceInterface {
    @GET("discover/movie?api_key=" + BuildConfig.TMDB_API_KEY +
            "&language=en-US&sort_by=popularity.desc&include_adult=false&" +
            "include_video=false&page=1&with_watch_monetization_types=flatrate")
    Call<MovieRequest> getAllMovies();
}
