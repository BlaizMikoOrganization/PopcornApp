package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaggedImagesResponse extends BaseNetworkResult{
    @SerializedName("results")
    private List<TaggedImageModel> images;

    public List<TaggedImageModel> getImages() {
        return images;
    }

    public void setImages(List<TaggedImageModel> images) {
        this.images = images;
    }
}
