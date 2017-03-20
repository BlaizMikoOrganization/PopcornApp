package com.blaizmiko.popcornapp.data.models.cinema;

import com.google.gson.annotations.SerializedName;

public class SimilarCinemaModel {
    @SerializedName("backdrop_path")
    protected String backdrop;
    @SerializedName("poster_path")
    protected String poster;
    @SerializedName("vote_average")
    protected double voteAverage;

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
