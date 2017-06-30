package com.apichai.jo.silverscreen.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/";
    private static final String THUMBNAIL_SMALL = "w342";
    private static final String THUMBNAIL_MEDIUM = "w500";

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath(String size) {
        String sizePath;

        switch (size) {
            case "small":
                sizePath = THUMBNAIL_SMALL;
                break;
            case "medium":
                sizePath = THUMBNAIL_MEDIUM;
                break;
            default:
                sizePath = THUMBNAIL_SMALL;
                break;
        }

        return BASE_IMAGE_PATH + sizePath + posterPath;
    }

    public String getOverview() {
        return overview;
    }
}
