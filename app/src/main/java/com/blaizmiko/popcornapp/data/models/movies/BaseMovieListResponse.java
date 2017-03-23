package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseMovieListResponse extends BaseNetworkResult{
    @SerializedName("results")
    private List<BaseMovieModel> movies;

    public List<BaseMovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<BaseMovieModel> movies) {
        this.movies = movies;
    }
}
