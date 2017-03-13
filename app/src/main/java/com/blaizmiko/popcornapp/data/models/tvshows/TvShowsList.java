package com.blaizmiko.popcornapp.data.models.tvshows;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsList extends BaseNetworkResult {
    @SerializedName("results")
    private List<BriefTvShow> tvShows;

    public List<BriefTvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<BriefTvShow> tvShows) {
        this.tvShows = tvShows;
    }
}
