package com.blaizmiko.popcornapp.common.api;

import com.blaizmiko.popcornapp.data.models.actors.PopularActors;
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovie;
import com.blaizmiko.popcornapp.data.models.movies.NowPlayingMovies;
import com.blaizmiko.popcornapp.data.models.movies.PopularMovies;
import com.blaizmiko.popcornapp.data.models.movies.TopRatedMovies;
import com.blaizmiko.popcornapp.data.models.movies.UpcomingMovies;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface PealApi {

    String QUERY_PAGE_KEY = "page";
    String QUERY_REGION_KEY = "region";

    //Movies
    @GET("movie/popular")
    Observable<PopularMovies> getPopularMovies(@Query(QUERY_PAGE_KEY) int page,
                                               @Query(QUERY_REGION_KEY) String region);

    @GET("movie/now_playing")
    Observable<NowPlayingMovies> getNowPlayingMovies(@Query(QUERY_PAGE_KEY) int page,
                                                     @Query(QUERY_REGION_KEY) String region);

    @GET("movie/top_rated")
    Observable<TopRatedMovies> getTopRatedMovies(@Query(QUERY_PAGE_KEY) int page,
                                                 @Query(QUERY_REGION_KEY) String region);

    @GET("movie/upcoming")
    Observable<UpcomingMovies> getUpcomingMovies(@Query(QUERY_PAGE_KEY) int page,
                                                 @Query(QUERY_REGION_KEY) String region);
    @GET("movie/{movie_id}")
    Observable<DetailedMovie> getMovie(@Path("movie_id") int movie,
                                       @Query("include_image_language") String include_image_language,
                                       @Query("append_to_response") String append_to_response);


    //Actors
    @GET("person/popular")
    Observable<PopularActors> getPopularActors(@Query(QUERY_PAGE_KEY) int page);
}
