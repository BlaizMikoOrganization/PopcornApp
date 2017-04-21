package com.blaizmiko.popcornapp.data.models.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesActorResponse {
    @SerializedName("profiles")
    private List<ImageModel> profiles;

    public List<ImageModel> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ImageModel> profiles) {
        this.profiles = profiles;
    }
}
