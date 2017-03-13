package com.blaizmiko.popcornapp.data.models.cast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {
    @SerializedName("id")
    private int id;
    @SerializedName("cast")
    private List<Cast> cast;
    @SerializedName("crew")
    private List<Crew> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }
}
