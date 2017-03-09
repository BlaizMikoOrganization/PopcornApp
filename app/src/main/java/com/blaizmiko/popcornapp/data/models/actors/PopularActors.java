package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularActors extends BaseNetworkResult {

    @SerializedName("results")
    private List<BaseActor> mPopularActors;

    public List<BaseActor> getPopularActors() {
        return mPopularActors;
    }

    public void setPopularActors(final List<BaseActor> popularActors) {
        mPopularActors = popularActors;
    }
}
