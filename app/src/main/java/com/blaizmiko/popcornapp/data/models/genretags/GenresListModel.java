package com.blaizmiko.popcornapp.data.models.genretags;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresListModel {
    @SerializedName("genres")
    List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
