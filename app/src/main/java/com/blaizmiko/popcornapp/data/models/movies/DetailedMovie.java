package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.cast.Credits;
import com.blaizmiko.popcornapp.data.models.images.Pictures;
import com.blaizmiko.popcornapp.data.models.videos.Videos;
import com.google.gson.annotations.SerializedName;

public class DetailedMovie extends Movie{
    @SerializedName("credits")
    private Credits credits;
    @SerializedName("images")
    private Pictures pictures;
    @SerializedName("videos")
    private Videos videos;
    @SerializedName("similar")
    private SimilarMovies similarMovies;
    @SerializedName("reviews")
    private MovieReviews movieReviews;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Pictures getPictures() {
        return pictures;
    }

    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    public SimilarMovies getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(SimilarMovies similarMovies) {
        this.similarMovies = similarMovies;
    }

    public MovieReviews getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(MovieReviews movieReviews) {
        this.movieReviews = movieReviews;
    }
}

