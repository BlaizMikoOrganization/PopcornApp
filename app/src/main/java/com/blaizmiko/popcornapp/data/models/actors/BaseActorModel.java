package com.blaizmiko.popcornapp.data.models.actors;

import com.google.gson.annotations.SerializedName;

public class BaseActorModel {

    @SerializedName("id")
    private int id;

    @SerializedName("profile_path")
    private String profileImageUrl;

    @SerializedName("adult")
    private boolean isAdult;

    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private double popularity;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(final String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(final boolean adult) {
        this.isAdult = adult;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(final double popularity) {
        this.popularity = popularity;
    }
}
