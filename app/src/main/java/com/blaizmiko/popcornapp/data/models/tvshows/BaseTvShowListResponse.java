package com.blaizmiko.popcornapp.data.models.tvshows;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.blaizmiko.popcornapp.data.models.cinema.BaseCinemaModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseTvShowListResponse extends BaseNetworkResult {
    @SerializedName("results")
    private List<? extends BaseCinemaModel> tvShows;

    public List<? extends BaseCinemaModel> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<? extends BaseCinemaModel> tvShows) {
        this.tvShows = tvShows;
    }
}
