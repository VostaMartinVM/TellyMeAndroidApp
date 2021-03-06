package com.example.tellyme.network;

import com.example.tellyme.model.Show;
import com.example.tellyme.model.ShowRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShowServiceInterface {
    @GET("discover/tv?" + "&language=en-US&sort_by=popularity.desc&page=1" +
            "&timezone=America%2FNew_York&include_null_first_air_dates=false" +
            "&with_watch_monetization_types=flatrate&with_status=0&with_type=0")
    Call<ShowRequest> getAllShows(@Query("api_key") String apiKey);

    @GET("tv/{tvID}?" + "&language=en-US")
    Call<Show> getSpecificShow(@Path("tvID") int tvID, @Query("api_key") String apiKey);

    @GET("search/tv")
    Call<ShowRequest> getShowsBySearchedText(@Query("api_key") String apiKey ,@Query("query") String searchedText);

}
