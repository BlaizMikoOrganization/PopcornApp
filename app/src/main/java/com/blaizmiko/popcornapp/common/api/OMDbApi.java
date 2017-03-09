package com.blaizmiko.popcornapp.common.api;

import com.blaizmiko.popcornapp.models.movies.PopularMovies;
import com.blaizmiko.popcornapp.models.rating.Rating;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface OMDbApi {

    @GET(".")
    Observable<Rating> getRating(@Query("i") String id);
}
