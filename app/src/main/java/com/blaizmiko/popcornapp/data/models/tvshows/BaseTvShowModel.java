package com.blaizmiko.popcornapp.data.models.tvshows;

import com.blaizmiko.popcornapp.data.models.cinema.BaseCinemaModel;
import com.google.gson.annotations.SerializedName;

public class BaseTvShowModel extends BaseCinemaModel {
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String firstAirDate;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

}
