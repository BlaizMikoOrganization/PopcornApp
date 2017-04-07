package com.blaizmiko.popcornapp.data.models.cinema;

import com.google.gson.annotations.SerializedName;

public class BaseCinemaModel {
    @SerializedName("id")
    private int id;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName(value="title", alternate={"name"})
    private String title;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("backdrop_path")
    private String backdropPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    public String toString() {
        return "BaseCinemaModel{" +
                "id=" + id +
                ", posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", backdropPath='" + backdropPath + '\'' +
                '}';
    }
}
