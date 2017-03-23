package com.blaizmiko.popcornapp.data.models.tvshows;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseTvShowListResponse extends BaseNetworkResult {
    @SerializedName("results")
    private List<BaseTvShowModel> tvShows;

    public List<BaseTvShowModel> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<BaseTvShowModel> tvShows) {
        this.tvShows = tvShows;
    }
}
