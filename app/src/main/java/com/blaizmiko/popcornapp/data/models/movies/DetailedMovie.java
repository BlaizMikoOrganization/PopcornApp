package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.cast.Credits;
import com.blaizmiko.popcornapp.data.models.images.MovieImages;
import com.google.gson.annotations.SerializedName;

public class DetailedMovie extends Movie{
    @SerializedName("credits")
    private Credits credits;
    @SerializedName("images")
    private MovieImages movieImages;
    @SerializedName("videos")
    private MovieVideos movieVideos;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public MovieImages getMovieImages() {
        return movieImages;
    }

    public void setMovieImages(MovieImages movieImages) {
        this.movieImages = movieImages;
    }

    public MovieVideos getMovieVideos() {
        return movieVideos;
    }

    public void setMovieVideos(MovieVideos movieVideos) {
        this.movieVideos = movieVideos;
    }
}

