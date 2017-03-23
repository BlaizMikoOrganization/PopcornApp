package com.blaizmiko.popcornapp.common.network.api;

import com.blaizmiko.popcornapp.data.models.actors.PopularActors;
import com.blaizmiko.popcornapp.data.models.cast.CreditsResponse;
import com.blaizmiko.popcornapp.data.models.movies.BaseMovieListResponse;
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovieModel;
import com.blaizmiko.popcornapp.data.models.movies.ReviewsMovieResponse;
import com.blaizmiko.popcornapp.data.models.tvshows.BaseTvShowListResponse;
import com.blaizmiko.popcornapp.data.models.tvshows.DetailedTvShowModel;

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
    String PATH_TV_SHOW_ID = "tv_id";

    //Movies
    @GET("movie/popular")
    Observable<BaseMovieListResponse> getPopularMovies(@Query(QUERY_PAGE_KEY) int page,
                                               @Query(QUERY_REGION_KEY) String region);

    @GET("movie/now_playing")
    Observable<BaseMovieListResponse> getNowPlayingMovies(@Query(QUERY_PAGE_KEY) int page,
                                                          @Query(QUERY_REGION_KEY) String region);

    @GET("movie/top_rated")
    Observable<BaseMovieListResponse> getTopRatedMovies(@Query(QUERY_PAGE_KEY) int page,
                                                 @Query(QUERY_REGION_KEY) String region);

    @GET("movie/upcoming")
    Observable<BaseMovieListResponse> getUpcomingMovies(@Query(QUERY_PAGE_KEY) int page,
                                                 @Query(QUERY_REGION_KEY) String region);

    //MovieDetails
    @GET("movie/{movie_id}/credits")
    Observable<CreditsResponse> getCredits(@Path(PATH_MOVIE_ID) int movieId);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewsMovieResponse> getMovieReview(@Path(PATH_MOVIE_ID) int movieId,
                                                    @Query(QUERY_PAGE_KEY) int page);
    @GET("movie/{movie_id}")
    Observable<DetailedMovieModel> getMovieInfo(@Path(PATH_MOVIE_ID) int movieId,
                                                @Query(QUERY_IMAGE_LANGUAGE) String imageLanguage,
                                                @Query(QUERY_APPEND_TO_RESPONSE) String appendToResponse);

    //Tv Shows
    @GET("tv/popular")
    Observable<BaseTvShowListResponse> getPopularTvShows(@Query(QUERY_PAGE_KEY) int page);

    @GET("tv/top_rated")
    Observable<BaseTvShowListResponse> getTopTvShows(@Query(QUERY_PAGE_KEY) int page);

    @GET("tv/airing_today")
    Observable<BaseTvShowListResponse> getNowPlayingTvShows(@Query(QUERY_PAGE_KEY) int page);

    @GET("tv/on_the_air")
    Observable<BaseTvShowListResponse> getUpcomingTvShows(@Query(QUERY_PAGE_KEY) int page);

    //Tv Shows Details
    @GET("tv/{tv_id}/credits")
    Observable<CreditsResponse> getTvShowCredits(@Path(PATH_TV_SHOW_ID) int tvShowId);

    @GET("tv/{tv_id}")
    Observable<DetailedTvShowModel> getTvShowInfo(@Path(PATH_TV_SHOW_ID) int tvShowId,
                                                  @Query(QUERY_IMAGE_LANGUAGE) String includeImageLanguage,
                                                  @Query(QUERY_APPEND_TO_RESPONSE) String appendToResponse);

    //Actors
    @GET("person/popular")
    Observable<PopularActors> getPopularActors(@Query(QUERY_PAGE_KEY) int page);
}
