package com.blaizmiko.popcornapp.data.models.tvshows.detailed;

import com.google.gson.annotations.SerializedName;

public class ExternalIds {
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("freebase_mid")
    private String freebaseMId;
    @SerializedName("freebase_id")
    private String freebaseId;
    @SerializedName("tvrage_id")
    private int tvRageId;
    @SerializedName("id")
    private int id;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getFreebaseMId() {
        return freebaseMId;
    }

    public void setFreebaseMId(String freebaseMId) {
        this.freebaseMId = freebaseMId;
    }

    public String getFreebaseId() {
        return freebaseId;
    }

    public void setFreebaseId(String freebaseId) {
        this.freebaseId = freebaseId;
    }

    public int getTvRageId() {
        return tvRageId;
    }

    public void setTvRageId(int tvRageId) {
        this.tvRageId = tvRageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
