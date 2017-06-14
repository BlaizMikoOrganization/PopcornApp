package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.blaizmiko.popcornapp.data.models.cinema.BaseCinemaModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseMovieListResponse extends BaseNetworkResult{
    @SerializedName("results")
    private List<BaseCinemaModel> movies;

    public List<BaseCinemaModel> getMovies() {
        return movies;
    }
    public void setMovies(List<BaseCinemaModel> movies) {
        this.movies = movies;
    }
}
