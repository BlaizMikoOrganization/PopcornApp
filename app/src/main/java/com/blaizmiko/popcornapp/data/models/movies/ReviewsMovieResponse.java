package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsMovieResponse extends BaseNetworkResult{
    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<ReviewMovieModel> reviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReviewMovieModel> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewMovieModel> reviews) {
        this.reviews = reviews;
    }
}
