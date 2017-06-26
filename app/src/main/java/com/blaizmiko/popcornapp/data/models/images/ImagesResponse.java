package com.blaizmiko.popcornapp.data.models.images;

import com.blaizmiko.popcornapp.data.db.models.images.ImageDBModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("backdrops")
    private List<ImageDBModel> backdrops;
    @SerializedName("posters")
    private List<ImageDBModel> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ImageDBModel> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<ImageDBModel> backdrops) {
        this.backdrops = backdrops;
    }

    public List<ImageDBModel> getPosters() {
        return posters;
    }

    public void setPosters(List<ImageDBModel> posters) {
        this.posters = posters;
    }
}
