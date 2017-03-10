package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

public abstract class BaseMovie {
    @SerializedName("adult")
    protected boolean adult;
    @SerializedName("backdrop_path")
    protected String backdropPath;
    @SerializedName("id")
    protected int id;
    @SerializedName("original_language")
    protected String originalLanguage;
    @SerializedName("original_title")
    protected String originalTitle;
    @SerializedName("overview")
    protected String overview;
    @SerializedName("popularity")
    protected double popularity;
    @SerializedName("poster_path")
    protected String posterPath;
    @SerializedName("release_date")
    protected String releaseDate;
    @SerializedName("title")
    protected String title;
    @SerializedName("video")
    protected boolean video;
    @SerializedName("vote_average")
    protected double voteAverage;
    @SerializedName("vote_count")
    protected int voteCount;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
