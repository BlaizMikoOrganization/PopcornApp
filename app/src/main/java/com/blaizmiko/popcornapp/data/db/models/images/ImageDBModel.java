package com.blaizmiko.popcornapp.data.db.models.images;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ImageDBModel extends RealmObject{
    @SerializedName("id")
    private long id;
    @SerializedName("aspect_ratio")
    private double aspectRatio;
    @PrimaryKey
    @SerializedName("file_path")
    private String filePath;
    @SerializedName("height")
    private double height;
    @SerializedName("iso_639_1")
    private String language;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("width")
    private int width;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }
    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public double getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "ImageDBModel{" +
                "id=" + id +
                ", aspectRatio=" + aspectRatio +
                ", filePath='" + filePath + '\'' +
                ", height=" + height +
                ", language='" + language + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", width=" + width +
                '}';
    }
}
