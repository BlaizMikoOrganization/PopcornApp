package com.blaizmiko.popcornapp.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BriefMovie extends BaseMovie {
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds;

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }
}
