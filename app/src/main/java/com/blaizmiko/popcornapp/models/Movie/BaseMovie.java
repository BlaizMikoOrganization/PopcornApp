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
    protected int mPopularity;
    @SerializedName("poster_path")
    protected String mPosterPath;
    @SerializedName("release_date")
    protected String mReleaseDate;
    @SerializedName("title")
    protected String mTitle;
    @SerializedName("video")
    protected boolean mVideo;
    @SerializedName("vote_average")
    protected int mVoteAverage;
    @SerializedName("vote_count")
    protected int mVoteCount;

    protected boolean isAdult() {
        return mAdult;
    }

    protected void setAdult(boolean adult) {
        mAdult = adult;
    }

    protected String getBackdropPath() {
        return mBackdropPath;
    }

    protected void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    protected int getId() {
        return mId;
    }

    protected void setId(int id) {
        mId = id;
    }

    protected String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    protected void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    protected String getOriginalTitle() {
        return mOriginalTitle;
    }

    protected void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    protected String getOverview() {
        return mOverview;
    }

    protected void setOverview(String overview) {
        mOverview = overview;
    }

    protected int getPopularity() {
        return mPopularity;
    }

    protected void setPopularity(int popularity) {
        mPopularity = popularity;
    }

    protected String getPosterPath() {
        return mPosterPath;
    }

    protected void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    protected String getReleaseDate() {
        return mReleaseDate;
    }

    protected void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    protected String getTitle() {
        return mTitle;
    }

    protected void setTitle(String title) {
        mTitle = title;
    }

    protected boolean isVideo() {
        return mVideo;
    }

    protected void setVideo(boolean video) {
        mVideo = video;
    }

    protected int getVoteAverage() {
        return mVoteAverage;
    }

    protected void setVoteAverage(int voteAverage) {
        mVoteAverage = voteAverage;
    }

    protected int getVoteCount() {
        return mVoteCount;
    }

    protected void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }
}
