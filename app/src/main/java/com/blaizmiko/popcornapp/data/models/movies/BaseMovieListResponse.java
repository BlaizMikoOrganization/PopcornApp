package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.blaizmiko.popcornapp.data.models.cinema.BaseCinemaModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseMovieListResponse extends BaseNetworkResult{
    @SerializedName("results")
    private List<? extends BaseCinemaModel> movies;

    public List<? extends BaseCinemaModel> getMovies() {
        return movies;
    }

    public void setMovies(List<? extends BaseCinemaModel> movies) {
        this.movies = movies;
    }
}
