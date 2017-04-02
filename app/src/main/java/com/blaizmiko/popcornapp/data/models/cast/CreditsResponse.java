package com.blaizmiko.popcornapp.data.models.cast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditsResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("cast")
    private List<CastModel> cast;
    @SerializedName("crew")
    private List<CrewModel> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CastModel> getCast() {
        return cast;
    }

    public void setCast(List<CastModel> cast) {
        this.cast = cast;
    }

    public List<CrewModel> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewModel> crew) {
        this.crew = crew;
    }
}
