package com.apichai.jo.silverscreen.api;


import com.apichai.jo.silverscreen.models.MoviesList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MovieService {
    @GET("movie/popular")
    Call<MoviesList> getPopularMovies(@QueryMap Map<String, String> options);
}
