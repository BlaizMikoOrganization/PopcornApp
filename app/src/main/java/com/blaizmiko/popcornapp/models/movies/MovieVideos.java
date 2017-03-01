package com.blaizmiko.popcornapp.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideos {
    @SerializedName("id")
    private int mId;
    @SerializedName("results")
    private List<Video> mResults;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Video> getResults() {
        return mResults;
    }

    public void setResults(List<Video> results) {
        mResults = results;
    }
}
