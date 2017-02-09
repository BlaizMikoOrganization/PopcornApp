package com.blaizmiko.popcornapp.models.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Влад on 09.02.2017.
 */
public class ShortMovie extends BaseMovie{
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds;

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }
}
