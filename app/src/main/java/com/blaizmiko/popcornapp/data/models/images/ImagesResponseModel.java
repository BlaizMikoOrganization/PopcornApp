package com.blaizmiko.popcornapp.data.models.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesResponseModel {
    @SerializedName("id")
    private int id;
    @SerializedName("backdrops")
    private List<ImageModel> backdrops;
    @SerializedName("posters")
    private List<ImageModel> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ImageModel> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<ImageModel> backdrops) {
        this.backdrops = backdrops;
    }

    public List<ImageModel> getPosters() {
        return posters;
    }

    public void setPosters(List<ImageModel> posters) {
        this.posters = posters;
    }
}
