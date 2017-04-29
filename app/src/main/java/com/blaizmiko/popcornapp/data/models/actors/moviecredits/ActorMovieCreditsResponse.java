package com.blaizmiko.popcornapp.data.models.actors.moviecredits;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActorMovieCreditsResponse {
    @SerializedName("cast")
    private List<ActorMovieCastModel> cast;
    @SerializedName("crew")
    private List<ActorMovieCrewModel> crew;
    @SerializedName("id")
    private int id;

    public List<ActorMovieCastModel> getCast() {
        return cast;
    }

    public void setCast(List<ActorMovieCastModel> cast) {
        this.cast = cast;
    }

    public List<ActorMovieCrewModel> getCrew() {
        return crew;
    }

    public void setCrew(List<ActorMovieCrewModel> crew) {
        this.crew = crew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
