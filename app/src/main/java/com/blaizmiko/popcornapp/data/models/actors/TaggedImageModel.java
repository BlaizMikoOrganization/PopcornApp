package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.data.models.images.ImageModel;
import com.google.gson.annotations.SerializedName;

public class TaggedImageModel extends ImageModel{
    @SerializedName("image_type")
    private String imageType;

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
