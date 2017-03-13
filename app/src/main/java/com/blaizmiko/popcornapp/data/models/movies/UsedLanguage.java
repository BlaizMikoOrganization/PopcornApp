package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

public class UsedLanguage {
    @SerializedName("iso_639_1")
    private String code;
    @SerializedName("name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
