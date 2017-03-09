package com.blaizmiko.popcornapp.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {
    @SerializedName("id")
    private int mId;
    @SerializedName("cast")
    private List<Cast> mCast;
    @SerializedName("crew")
    private List<Crew> mCrew;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Cast> getCast() {
        return mCast;
    }

    public void setCast(List<Cast> cast) {
        mCast = cast;
    }

    public List<Crew> getCrew() {
        return mCrew;
    }

    public void setCrew(List<Crew> crew) {
        mCrew = crew;
    }
}
