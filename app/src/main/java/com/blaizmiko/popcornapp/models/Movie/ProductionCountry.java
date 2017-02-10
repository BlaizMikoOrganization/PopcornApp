package com.blaizmiko.popcornapp.models.Movie;

import com.google.gson.annotations.SerializedName;

public class ProductionCountry {
    @SerializedName("iso_3166_1")
    private String mCode;
    @SerializedName("name")
    private String mName;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
