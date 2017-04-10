package com.blaizmiko.popcornapp.data.models.rating;

import com.google.gson.annotations.SerializedName;

public class RatingModel {
    @SerializedName("Source")
    private String site;
    @SerializedName("Value")
    private String rating;

    private int siteLogo;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getSiteLogo() {
        return siteLogo;
    }

    public void setSiteLogo(int siteLogo) {
        this.siteLogo = siteLogo;
    }
}
