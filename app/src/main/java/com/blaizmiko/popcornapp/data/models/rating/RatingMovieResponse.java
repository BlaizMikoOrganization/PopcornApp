package com.blaizmiko.popcornapp.data.models.rating;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingMovieResponse {
    @SerializedName("Ratings")
    private List<RatingModel> ratings;

    public List<RatingModel> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingModel> ratings) {
        this.ratings = ratings;
    }
}
