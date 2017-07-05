package com.blaizmiko.popcornapp.data.db.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("results")
    private List<ReviewDBModel> reviews;

    public List<ReviewDBModel> getReviews() {
        return reviews;
    }
    public void setReviews(List<ReviewDBModel> reviews) {
        this.reviews = reviews;
    }
}
