package com.blaizmiko.popcornapp.data.models.actors.cinemascredits;
import com.google.gson.annotations.SerializedName;

public class ActorCinemaCastModel {
    @SerializedName("id")
    private int id;
    @SerializedName(value="title", alternate={"name"})
    private String title;
    @SerializedName("poster_path")
    private String posterPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
