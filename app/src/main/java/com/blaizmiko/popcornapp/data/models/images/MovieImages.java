package com.blaizmiko.popcornapp.data.models.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieImages {
    @SerializedName("id")
    private int id;
    @SerializedName("backdrops")
    private List<Image> backdrops;
    @SerializedName("posters")
    private List<Image> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Image> getPosters() {
        return posters;
    }

    public void setPosters(List<Image> posters) {
        this.posters = posters;
    }
}
