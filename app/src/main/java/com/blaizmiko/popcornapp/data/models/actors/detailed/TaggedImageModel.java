package com.blaizmiko.popcornapp.data.models.actors.detailed;

import com.blaizmiko.popcornapp.data.db.models.movies.ImageDBModel;
import com.google.gson.annotations.SerializedName;

public class TaggedImageModel extends ImageDBModel{
    @SerializedName("image_type")
    private String imageType;

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
