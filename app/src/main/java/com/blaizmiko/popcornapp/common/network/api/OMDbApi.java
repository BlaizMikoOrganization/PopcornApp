package com.blaizmiko.popcornapp.common.network.api;

import com.blaizmiko.popcornapp.data.models.rating.RatingMovieResponse;
import com.blaizmiko.popcornapp.data.models.rating.RatingTvShowResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OMDbApi {

    String QUERY_ID = "i";
    String QUERY_TOMATOES = "tomatoes";

    @GET(".")
    Observable<RatingMovieResponse> getMovieRating(@Query(QUERY_ID) String id);

    @GET(".")
    Observable<RatingTvShowResponse> getTvShowRating(@Query(QUERY_ID) String id);
}
