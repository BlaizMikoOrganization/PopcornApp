package com.blaizmiko.popcornapp.common.network.api;

import com.blaizmiko.popcornapp.data.models.rating.RatingResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OMDbApi {

    String QUERY_ID = "i";
    String QUERY_TOMATOES = "tomatoes";

    @GET(".")
    Observable<RatingResponse> getRating(@Query(QUERY_ID) String id);
}
