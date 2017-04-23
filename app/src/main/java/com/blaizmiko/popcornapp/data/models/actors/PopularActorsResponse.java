package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularActorsResponse extends BaseNetworkResult {

    @SerializedName("results")
    private List<PopularActorModel> popularActors;

    public List<PopularActorModel> getPopularActors() {
        return popularActors;
    }

    public void setPopularActors(final List<PopularActorModel> popularActors) {
        this.popularActors = popularActors;
    }
}
