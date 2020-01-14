package com.example.salt.network;



import com.example.salt.model.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("top-headlines")
    Observable<NewsResponse> getMovies(@Query("country") String country,@Query("apiKey") String apiKey);



}