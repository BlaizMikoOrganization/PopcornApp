package com.blaizmiko.popcornapp.data.models.cinema;

import com.blaizmiko.popcornapp.data.models.BaseNetworkResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarCinemasResponse extends BaseNetworkResult{

    @SerializedName("results")
    private List<SimilarCinemaModel> similarCinemas;

    public List<SimilarCinemaModel> getSimilarCinemas() {
        return similarCinemas;
    }

    public void setSimilarCinemas(List<SimilarCinemaModel> similarCinemas) {
        this.similarCinemas = similarCinemas;
    }
}
