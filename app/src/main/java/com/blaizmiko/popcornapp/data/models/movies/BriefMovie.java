package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BriefMovie extends BaseMovie {
    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }
}
