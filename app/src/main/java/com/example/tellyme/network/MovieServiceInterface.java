package com.example.tellyme.network;

import com.example.tellyme.BuildConfig;
import com.example.tellyme.model.Movie;
import com.example.tellyme.model.MovieRequest;
import com.example.tellyme.model.Show;
import com.example.tellyme.model.ShowRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServiceInterface {
    @GET("discover/movie?api_key=" + BuildConfig.TMDB_API_KEY +
            "&language=en-US&sort_by=popularity.desc&include_adult=false&" +
            "include_video=false&page=1&with_watch_monetization_types=flatrate")
    Call<MovieRequest> getAllMovies();

    @GET("movie/{movieID}?api_key=" + BuildConfig.TMDB_API_KEY + "&language=en-US")
    Call<Movie> getSpecificMovie(@Path("movieID") int movieID);

    @GET("search/movie")
    Call<MovieRequest> getMoviesBySearchedText(@Query("api_key") String apiKey, @Query("query") String searchedText);
}
