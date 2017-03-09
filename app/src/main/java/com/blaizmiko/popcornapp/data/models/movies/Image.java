package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("aspect_ratio")
    private double mAspectRatio;
    @SerializedName("file_path")
    private String mFilePath;
    @SerializedName("height")
    private double mHeight;
    @SerializedName("iso_639_1")
    private String mLanguage;
    @SerializedName("vote_average")
    private double mVoteAverage;
    @SerializedName("vote_count")
    private int mVoteCount;
    @SerializedName("width")
    private int mWidth;

    public double getAspectRatio() {
        return mAspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        mAspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public double getHeight() {
        return mHeight;
    }

    public void setHeight(double height) {
        mHeight = height;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
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

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }
}
