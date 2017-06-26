package com.blaizmiko.popcornapp.data.models.images;

import com.blaizmiko.popcornapp.data.db.models.images.ImageDBModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesActorResponse {
    @SerializedName("profiles")
    private List<ImageDBModel> profiles;

    public List<ImageDBModel> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ImageDBModel> profiles) {
        this.profiles = profiles;
    }
}
