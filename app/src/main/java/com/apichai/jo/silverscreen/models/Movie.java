package com.apichai.jo.silverscreen.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/";
    private static final String THUMBNAIL_SIZE = "w342";

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    public String getTitle() {
        return title;
    }

    public String getThumbnailPath() {
        return BASE_IMAGE_PATH + THUMBNAIL_SIZE + posterPath;
    }
}
