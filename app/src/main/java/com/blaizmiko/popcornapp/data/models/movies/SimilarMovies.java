package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarMovies extends BaseNetworkResult {
    @SerializedName("results")
    private List<BriefMovie> movies;

    public List<BriefMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<BriefMovie> movies) {
        this.movies = movies;
    }
}
