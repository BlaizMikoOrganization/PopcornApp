package com.blaizmiko.popcornapp.data.models.cinema;

import com.google.gson.annotations.SerializedName;

public class BriefCinema {
    @SerializedName(value="title", alternate={"name"})
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
}
