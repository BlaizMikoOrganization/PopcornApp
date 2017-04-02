package com.blaizmiko.popcornapp.data.models.rating;

import com.google.gson.annotations.SerializedName;

public class RatingModel {
    @SerializedName("Source")
    private String site;
    @SerializedName("Value")
    private String rating;

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
}
