package com.blaizmiko.popcornapp.data.models.genretags;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresResponse {
    @SerializedName("genres")
    List<GenreModel> genres;

    public List<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }
}
