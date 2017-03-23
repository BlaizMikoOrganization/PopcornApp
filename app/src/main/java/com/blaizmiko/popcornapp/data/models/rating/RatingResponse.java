package com.blaizmiko.popcornapp.data.models.rating;

import com.google.gson.annotations.SerializedName;

public class RatingResponse {
    @SerializedName("Metascore")
    private String metascore;
    @SerializedName("imdbRating")
    private String iMDb;
    @SerializedName("tomatoMeter")
    private String tomatometer;
    @SerializedName("tomatoUserMeter")
    private String tomatoAudienceScore;

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getIMDb() {
        return iMDb;
    }

    public void setIMDb(String IMDb) {
        iMDb = IMDb;
    }

    public String getTomatometer() {
        return tomatometer;
    }

    public void setTomatometer(String tomatometer) {
        this.tomatometer = tomatometer;
    }

    public String getTomatoAudienceScore() {
        return tomatoAudienceScore;
    }

    public void setTomatoAudienceScore(String tomatoAudienceScore) {
        this.tomatoAudienceScore = tomatoAudienceScore;
    }
}
