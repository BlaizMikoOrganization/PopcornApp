package com.blaizmiko.popcornapp.data.models.rating;

import com.google.gson.annotations.SerializedName;

public class RatingTvShowResponse {
    @SerializedName("imdbRating")
    private String imdbRating;

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }
}
