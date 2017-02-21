package com.blaizmiko.popcornapp.models.movies;

import com.blaizmiko.popcornapp.models.base.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingMovies extends BaseResult{
    @SerializedName("dates")
    PremiereDate mPremiereDate;
    @SerializedName("results")
    List<BriefMovie> mMovies;

    public PremiereDate getPremiereDate() {
        return mPremiereDate;
    }

    public void setPremiereDate(PremiereDate premiereDate) {
        mPremiereDate = premiereDate;
    }

    public List<BriefMovie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<BriefMovie> movies) {
        mMovies = movies;
    }
}
