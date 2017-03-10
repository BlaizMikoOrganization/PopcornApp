package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PopularActorsModel extends BaseNetworkResult {

    @SerializedName("results")
    private List<BriefActorModel> popularActors;

    public List<BriefActorModel> getPopularActors() {
        return popularActors;
    }

    public void setPopularActors(final List<BriefActorModel> popularActors) {
        this.popularActors = new ArrayList<>(popularActors);
    }
}
