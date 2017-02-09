package com.blaizmiko.popcornapp.models.Movie;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Влад on 09.02.2017.
 */
public class Date {
    @SerializedName("maximum")
    private String mMaximum;
    @SerializedName("minimum")
    private String mMinimum;

    public String getMaximum() {
        return mMaximum;
    }

    public void setMaximum(String maximum) {
        mMaximum = maximum;
    }

    public String getMinimum() {
        return mMinimum;
    }

    public void setMinimum(String minimum) {
        mMinimum = minimum;
    }
}
