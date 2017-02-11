package com.blaizmiko.popcornapp.models.actors;

import com.google.gson.annotations.SerializedName;

public class BaseActor {

    @SerializedName("profile_path")
    private String mProfileImageUrl;

    @SerializedName("adult")
    private boolean mAdult;

    @SerializedName("name")
    private String mName;

    @SerializedName("popularity")
    private double mPopularity;

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public void setProfileImageUrl(final String profileImageUrl) {
        mProfileImageUrl = profileImageUrl;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public void setAdult(final boolean adult) {
        mAdult = adult;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(final double popularity) {
        mPopularity = popularity;
    }
}
