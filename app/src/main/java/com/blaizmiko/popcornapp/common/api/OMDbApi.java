package com.blaizmiko.popcornapp.common.api;

import com.blaizmiko.popcornapp.data.models.rating.Rating;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OMDbApi {

    @GET(".")
    Observable<Rating> getRating(@Query("i") String id);
}
