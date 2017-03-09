package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieImages {
    @SerializedName("id")
    private int mId;
    @SerializedName("backdrops")
    private List<Image> mBackdrops;
    @SerializedName("posters")
    private List<Image> mPosters;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Image> getBackdrops() {
        return mBackdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        mBackdrops = backdrops;
    }

    public List<Image> getPosters() {
        return mPosters;
    }

    public void setPosters(List<Image> posters) {
        mPosters = posters;
    }
}
