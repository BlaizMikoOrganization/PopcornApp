package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.videos.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideos {
    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<Trailer> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
