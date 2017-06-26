package com.blaizmiko.popcornapp.data.models.videos;

import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<VideoDBModel> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VideoDBModel> getResults() {
        return results;
    }

    public void setResults(List<VideoDBModel> results) {
        this.results = results;
    }
}
