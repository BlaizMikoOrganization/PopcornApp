package com.blaizmiko.popcornapp.data.models.seasons;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeasonModel {

    @SerializedName("air_date")
    private String airDate;
    @SerializedName("crew")
    private List<Crew> crewList;
    @SerializedName("episode_number")
    private int episodeNumber;

}
