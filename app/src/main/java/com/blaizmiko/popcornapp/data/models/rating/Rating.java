package com.blaizmiko.popcornapp.data.models.rating;

import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("Metascore")
    private String mMetascore;
    @SerializedName("imdbRating")
    private String mIMDb;
    @SerializedName("tomatoMeter")
    private String mTomatometer;
    @SerializedName("tomatoUserMeter")
    private String mTomatoAudienceScore;

    public String getMetascore() {
        return mMetascore;
    }

    public void setMetascore(String metascore) {
        mMetascore = metascore;
    }

    public String getIMDb() {
        return mIMDb;
    }

    public void setIMDb(String IMDb) {
        mIMDb = IMDb;
    }

    public String getTomatometer() {
        return mTomatometer;
    }

    public void setTomatometer(String tomatometer) {
        mTomatometer = tomatometer;
    }

    public String getTomatoAudienceScore() {
        return mTomatoAudienceScore;
    }

    public void setTomatoAudienceScore(String tomatoAudienceScore) {
        mTomatoAudienceScore = tomatoAudienceScore;
    }
}
