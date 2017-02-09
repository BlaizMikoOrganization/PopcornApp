package com.blaizmiko.popcornapp.models.api;

import com.blaizmiko.popcornapp.models.Movie.NowPlayingResponse;
import com.blaizmiko.popcornapp.models.Movie.PopularResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PealApi {

    @GET("/movie/popular")
    Observable<PopularResponse> getPopularMovies(@Query("api_key") String api_key,
                                                 @Query("language") String language,
                                                 @Query("page") int page,
                                                 @Query("region") String region);

    @GET("/movie/now_playing")
    Observable<NowPlayingResponse> getNowPlayingMovies(@Query("api_key") String api_key,
                                                       @Query("language") String language,
                                                       @Query("page") int page,
                                                       @Query("region") String region);

}
