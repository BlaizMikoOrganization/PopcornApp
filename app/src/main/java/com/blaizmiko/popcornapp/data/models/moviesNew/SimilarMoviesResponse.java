package com.blaizmiko.popcornapp.data.models.moviesNew;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarMoviesResponse extends BaseNetworkResult{
    @SerializedName("results")
    List<BaseMovieModel> list;

    public List<BaseMovieModel> getList() {
        return list;
    }

    public void setList(List<BaseMovieModel> list) {
        this.list = list;
    }
}
