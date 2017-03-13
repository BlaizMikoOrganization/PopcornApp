package com.blaizmiko.popcornapp.common.network.api;

import com.blaizmiko.popcornapp.data.models.actors.PopularActors;
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovie;
import com.blaizmiko.popcornapp.data.models.movies.NowPlayingMovies;
import com.blaizmiko.popcornapp.data.models.movies.PopularMovies;
import com.blaizmiko.popcornapp.data.models.movies.TopRatedMovies;
import com.blaizmiko.popcornapp.data.models.movies.UpcomingMovies;
import com.blaizmiko.popcornapp.data.models.tvshows.TvShowsList;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.DetailedTvShow;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieDbApi {

    String QUERY_API_KEY = "api_key";
    String QUERY_LANGUAGE = "language";
    String QUERY_PAGE_KEY = "page";
    String QUERY_REGION_KEY = "region";
    String QUERY_IMAGE_LANGUAGE = "include_image_language";
    String QUERY_APPEND_TO_RESPONSE = "append_to_response";

    String PATH_MOVIE_ID = "movie_id";

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
    Observable<DetailedMovie> getMovie(@Path(PATH_MOVIE_ID) int movieId,
                                       @Query(QUERY_IMAGE_LANGUAGE) String imageLanguage,
                                       @Query(QUERY_APPEND_TO_RESPONSE) String appendToResponse);

    //Tv Shows
    @GET("tv/popular")
    Observable<TvShowsList> getPopularTvShows(@Query("page") int page);

    @GET("tv/top_rated")
    Observable<TvShowsList> getTopTvShows(@Query("page") int page);

    @GET("tv/airing_today")
    Observable<TvShowsList> getNowPlayingTvShows(@Query("page") int page);

    @GET("tv/on_the_air")
    Observable<TvShowsList> getUpcomingTvShows(@Query("page") int page);

    @GET("tv/{tv_show_id}")
    Observable<DetailedTvShow> getTvShow(@Path("tv_show_id") int tv_show_id,
                                         @Query("include_image_language") String include_image_language,
                                         @Query("append_to_response") String append_to_response);

    //Actors
    @GET("person/popular")
    Observable<PopularActors> getPopularActors(@Query(QUERY_PAGE_KEY) int page);
}
