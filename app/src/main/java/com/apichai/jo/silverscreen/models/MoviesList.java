package com.apichai.jo.silverscreen.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesList {
    @SerializedName("results")
    private List<Movie> movies;

    @SerializedName("page")
    private int page;

    public List<Movie> getResults() {
        return movies;
    }
}
