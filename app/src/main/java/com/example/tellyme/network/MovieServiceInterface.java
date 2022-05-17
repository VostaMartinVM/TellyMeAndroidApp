package com.example.tellyme.network;

import com.example.tellyme.model.Movie;
import com.example.tellyme.model.MovieRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServiceInterface {
    @GET("discover/movie?" + "&language=en-US&sort_by=popularity.desc&include_adult=false&" +
            "include_video=false&page=1&with_watch_monetization_types=flatrate")
    Call<MovieRequest> getAllMovies(@Query("api_key") String apiKey);

    @GET("movie/{movieID}?"+"&language=en-US")
    Call<Movie> getSpecificMovie(@Path("movieID") int movieID, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieRequest> getMoviesBySearchedText(@Query("api_key") String apiKey, @Query("query") String searchedText);
}
