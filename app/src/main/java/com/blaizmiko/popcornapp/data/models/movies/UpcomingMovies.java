package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingMovies extends BaseNetworkResult {
    @SerializedName("dates")
    PremiereDate premiereDate;
    @SerializedName("results")
    List<BriefMovie> movies;

    public PremiereDate getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(PremiereDate premiereDate) {
        this.premiereDate = premiereDate;
    }

    public List<BriefMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<BriefMovie> movies) {
        this.movies = movies;
    }
}
