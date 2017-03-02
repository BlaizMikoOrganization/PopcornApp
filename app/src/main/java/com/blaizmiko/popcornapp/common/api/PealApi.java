package com.blaizmiko.popcornapp.common.api;

import com.blaizmiko.popcornapp.models.actors.PopularActors;
import com.blaizmiko.popcornapp.models.movies.DetailedMovie;
import com.blaizmiko.popcornapp.models.movies.Movie;
import com.blaizmiko.popcornapp.models.movies.NowPlayingMovies;
import com.blaizmiko.popcornapp.models.movies.PopularMovies;
import com.blaizmiko.popcornapp.models.movies.TopRatedMovies;
import com.blaizmiko.popcornapp.models.movies.UpcomingMovies;

import retrofit2.http.GET;
import retrofit2.http.Path;
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
    @GET("movie/{movie_id}")
    Observable<DetailedMovie> getMovie(@Path("movie_id") int movie,
                                       @Query("api_key") String api_key,
                                       @Query("language") String language,
                                       @Query("include_image_language") String include_image_language,
                                       @Query("append_to_response") String append_to_response);

    //Actors
    @GET("person/popular")
    Observable<PopularActors> getPopularActors(@Query("api_key") String api_key,
                                               @Query("language") String language,
                                               @Query("page") int page);
}
