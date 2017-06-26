package com.blaizmiko.popcornapp.data.models.genretags;

import com.blaizmiko.popcornapp.data.db.models.genres.GenreDBModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresResponse {
    @SerializedName("genres")
    List<GenreDBModel> genres;

    public List<GenreDBModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDBModel> genres) {
        this.genres = genres;
    }
}
