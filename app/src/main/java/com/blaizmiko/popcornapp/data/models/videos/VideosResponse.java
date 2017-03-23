package com.blaizmiko.popcornapp.data.models.videos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<VideoModel> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VideoModel> getResults() {
        return results;
    }

    public void setResults(List<VideoModel> results) {
        this.results = results;
    }
}
