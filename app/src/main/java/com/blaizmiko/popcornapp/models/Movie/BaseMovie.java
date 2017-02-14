package com.blaizmiko.popcornapp.models.movie;

import com.google.gson.annotations.SerializedName;

public abstract class BaseMovie {
    @SerializedName("adult")
    protected boolean mAdult;
    @SerializedName("backdrop_path")
    protected String mBackdropPath;
    @SerializedName("id")
    protected int mId;
    @SerializedName("original_language")
    protected String mOriginalLanguage;
    @SerializedName("original_title")
    protected String mOriginalTitle;
    @SerializedName("overview")
    protected String mOverview;
    @SerializedName("popularity")
    protected double mPopularity;
    @SerializedName("poster_path")
    protected String mPosterPath;
    @SerializedName("release_date")
    protected String mReleaseDate;
    @SerializedName("title")
    protected String mTitle;
    @SerializedName("video")
    protected boolean mVideo;
    @SerializedName("vote_average")
    protected double mVoteAverage;
    @SerializedName("vote_count")
    protected int mVoteCount;

    public boolean isAdult() {
        return mAdult;
    }

    public void setAdult(boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isVideo() {
        return mVideo;
    }

    public void setVideo(boolean video) {
        mVideo = video;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }
}
