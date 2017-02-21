package com.blaizmiko.popcornapp.common.api;

import com.blaizmiko.popcornapp.models.actors.PopularActors;
import com.blaizmiko.popcornapp.models.movies.NowPlayingMovies;
import com.blaizmiko.popcornapp.models.movies.PopularMovies;
import com.blaizmiko.popcornapp.models.movies.TopRatedMovies;
import com.blaizmiko.popcornapp.models.movies.UpcomingMovies;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PealApi {

    //Movies
    @GET("movie/popular")
    Observable<PopularMovies> getPopularMovies(@Query("api_key") String api_key,
                                               @Query("language") String language,
                                               @Query("page") int page,
                                               @Query("region") String region);

    @GET("movie/now_playing")
    Observable<NowPlayingMovies> getNowPlayingMovies(@Query("api_key") String api_key,
                                                     @Query("language") String language,
                                                     @Query("page") int page,
                                                     @Query("region") String region);

    @GET("movie/top_rated")
    Observable<TopRatedMovies> getTopRatedMovies(@Query("api_key") String api_key,
                                                 @Query("language") String language,
                                                 @Query("page") int page,
                                                 @Query("region") String region);

    @GET("movie/upcoming")
    Observable<UpcomingMovies> getUpcomingMovies(@Query("api_key") String api_key,
                                                 @Query("language") String language,
                                                 @Query("page") int page,
                                                 @Query("region") String region);

    //Actors
    @GET("person/popular")
    Observable<PopularActors> getPopularActors(@Query("api_key") String api_key,
                                               @Query("language") String language,
                                               @Query("page") int page);
}
