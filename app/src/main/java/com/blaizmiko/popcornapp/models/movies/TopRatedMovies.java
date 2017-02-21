package com.blaizmiko.popcornapp.models.movies;

import com.blaizmiko.popcornapp.models.base.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedMovies extends BaseResult{
    @SerializedName("results")
    List<BriefMovie> mMovies;

    public List<BriefMovie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<BriefMovie> movies) {
        mMovies = movies;
    }
}
