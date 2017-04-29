package com.blaizmiko.popcornapp.data.models.actors.cinemascredits;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ActorCinemaCreditsResponse {
    @SerializedName("cast")
    private List<ActorCinemaCastModel> cast;
    @SerializedName("crew")
    private List<ActorCinemaCrewModel> crew;
    @SerializedName("id")
    private int id;

    public List<ActorCinemaCastModel> getCast() {
        return cast;
    }

    public void setCast(List<ActorCinemaCastModel> cast) {
        this.cast = cast;
    }

    public List<ActorCinemaCrewModel> getCrew() {
        return crew;
    }

    public void setCrew(List<ActorCinemaCrewModel> crew) {
        this.crew = crew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
