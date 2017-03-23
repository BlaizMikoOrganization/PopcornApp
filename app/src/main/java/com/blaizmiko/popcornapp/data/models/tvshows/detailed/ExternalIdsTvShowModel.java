package com.blaizmiko.popcornapp.data.models.tvshows.detailed;

import com.google.gson.annotations.SerializedName;

public class ExternalIdsTvShowModel {
    @SerializedName("imdb_id")
    private String imdbId;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
