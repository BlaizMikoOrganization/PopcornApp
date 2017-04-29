package com.blaizmiko.popcornapp.data.models.actors.moviecredits;

import com.google.gson.annotations.SerializedName;

public class ActorMovieCastModel {
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}

