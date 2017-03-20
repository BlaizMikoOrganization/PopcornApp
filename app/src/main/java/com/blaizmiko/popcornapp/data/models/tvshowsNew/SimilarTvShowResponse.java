package com.blaizmiko.popcornapp.data.models.tvshowsNew;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarTvShowResponse extends BaseNetworkResult{
    @SerializedName("result")
    List<BaseTvShowModel> list;

    public List<BaseTvShowModel> getList() {
        return list;
    }

    public void setList(List<BaseTvShowModel> list) {
        this.list = list;
    }
}
