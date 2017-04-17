package com.blaizmiko.popcornapp.data.models.actors;

import com.google.gson.annotations.SerializedName;

public class BaseActorModel {
    @SerializedName("profile_path")
    private String profileImageUrl;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private double popularity;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
