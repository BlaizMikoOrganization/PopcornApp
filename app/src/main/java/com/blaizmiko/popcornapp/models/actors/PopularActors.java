package com.blaizmiko.popcornapp.models.actors;

import com.blaizmiko.popcornapp.models.base.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularActors extends BaseResult {

    @SerializedName("results")
    private List<BaseActor> mPopularActors;

    public List<BaseActor> getPopularActors() {
        return mPopularActors;
    }

    public void setPopularActors(final List<BaseActor> popularActors) {
        mPopularActors = popularActors;
    }
}
