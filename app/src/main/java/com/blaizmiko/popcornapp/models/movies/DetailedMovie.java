package com.blaizmiko.popcornapp.models.movies;

import com.google.gson.annotations.SerializedName;

public class DetailedMovie extends Movie{
    @SerializedName("credits")
    private Credits mCredits;
    @SerializedName("images")
    private MovieImages mMovieImages;
    @SerializedName("videos")
    private MovieVideos mMovieVideos;

    public Credits getCredits() {
        return mCredits;
    }

    public void setCredits(Credits credits) {
        mCredits = credits;
    }

    public MovieImages getMovieImages() {
        return mMovieImages;
    }

    public void setMovieImages(MovieImages movieImages) {
        mMovieImages = movieImages;
    }

    public MovieVideos getMovieVideos() {
        return mMovieVideos;
    }

    public void setMovieVideos(MovieVideos movieVideos) {
        mMovieVideos = movieVideos;
    }
}

