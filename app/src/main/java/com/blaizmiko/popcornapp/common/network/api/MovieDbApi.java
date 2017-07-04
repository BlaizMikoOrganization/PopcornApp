package com.blaizmiko.popcornapp.common.network.api;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import com.blaizmiko.popcornapp.data.models.actors.cinemascredits.ActorCinemaCreditsResponse;
import com.blaizmiko.popcornapp.data.models.actors.detailed.DetailedActorModel;
import com.blaizmiko.popcornapp.data.models.actors.detailed.TaggedImagesResponse;
import com.blaizmiko.popcornapp.data.models.actors.popular.PopularActorsResponse;
import com.blaizmiko.popcornapp.data.models.cast.CreditsResponse;
import com.blaizmiko.popcornapp.data.models.cinema.BriefCinema;
import com.blaizmiko.popcornapp.data.models.images.ImagesActorResponse;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponse;
import com.blaizmiko.popcornapp.data.models.movies.ReviewsMovieResponse;
import com.blaizmiko.popcornapp.data.models.seasons.SeasonModel;
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
    String PATH_SEASON_NUMBER = "season_number";
    String PATH_PERSON_ID = "person_id";

    //Movies
    @GET("movie/popular")
    Observable<MoviesResponseDBModel> getPopularMovies(@Query(QUERY_PAGE_KEY) int page,
                                               @Query(QUERY_REGION_KEY) String region);

    @GET("movie/now_playing")
    Observable<MoviesResponseDBModel> getNowPlayingMovies(@Query(QUERY_PAGE_KEY) int page,
                                                          @Query(QUERY_REGION_KEY) String region);

    @GET("movie/top_rated")
    Observable<MoviesResponseDBModel> getTopRatedMovies(@Query(QUERY_PAGE_KEY) int page,
                                                 @Query(QUERY_REGION_KEY) String region);

    @GET("movie/upcoming")
    Observable<MoviesResponseDBModel> getUpcomingMovies(@Query(QUERY_PAGE_KEY) int page,
                                                 @Query(QUERY_REGION_KEY) String region);

    //MovieDetails
    @GET("movie/{movie_id}/credits")
    Observable<com.blaizmiko.popcornapp.data.db.models.cast.CreditsResponse> getMovieCredits(@Path(PATH_MOVIE_ID) long movieId);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewsMovieResponse> getMovieReview(@Path(PATH_MOVIE_ID) int movieId,
                                                    @Query(QUERY_PAGE_KEY) int page);
    @GET("movie/{movie_id}")
    Observable<DetailedMovieDBModel> getMovieInfo(@Path(PATH_MOVIE_ID) long movieId,
                                                  @Query(QUERY_IMAGE_LANGUAGE) String imageLanguage,
                                                  @Query(QUERY_APPEND_TO_RESPONSE) String appendToResponse);

    @GET("movie/{movie_id}")
    Observable<BriefCinema> getBriefMovieInfo(@Path(PATH_MOVIE_ID) long movieId,
                                              @Query(QUERY_IMAGE_LANGUAGE) String imageLanguage,
                                              @Query(QUERY_APPEND_TO_RESPONSE) String appendToResponse);

    //Movie Reviews
    @GET("movie/{movie_id}/images")
    Observable<ImagesResponse> getMovieImages(@Path(PATH_MOVIE_ID) int movieId,
                                              @Query(QUERY_IMAGE_LANGUAGE) String imageLanguage);

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
    Observable<CreditsResponse> getTvShowCredits(@Path(PATH_TV_SHOW_ID) long tvShowId);

    @GET("tv/{tv_id}")
    Observable<BriefCinema> getBriefTvShowInfo(@Path(PATH_TV_SHOW_ID) long movieId,
                                               @Query(QUERY_IMAGE_LANGUAGE) String includeImageLanguage,
                                               @Query(QUERY_APPEND_TO_RESPONSE) String appendToResponse);

    @GET("tv/{tv_id}")
    Observable<DetailedTvShowModel> getTvShowInfo(@Path(PATH_TV_SHOW_ID) int tvShowId,
                                                  @Query(QUERY_IMAGE_LANGUAGE) String includeImageLanguage,
                                                  @Query(QUERY_APPEND_TO_RESPONSE) String appendToResponse);

    //Tv Shows Episodes
    @GET("tv/{tv_id}/season/{season_number}")
    Observable<SeasonModel> getTvShowsSeasonInfo(@Path(PATH_TV_SHOW_ID) int tvShowId,
                                                 @Path(PATH_SEASON_NUMBER) int seasonNumber);
    //Actors
    @GET("person/popular")
    Observable<PopularActorsResponse> getPopularActors(@Query(QUERY_PAGE_KEY) int page);

    @GET("person/{person_id}")
    Observable<DetailedActorModel> getActorInfo(@Path(PATH_PERSON_ID) int personId);

    @GET("person/{person_id}/tagged_images")
    Observable<TaggedImagesResponse> getTaggedImages(@Path(PATH_PERSON_ID) long personId);

    @GET("person/{person_id}/images")
    Observable<ImagesActorResponse> getActorPhoto(@Path(PATH_PERSON_ID) int personId);

    @GET("person/{person_id}/movie_credits")
    Observable<ActorCinemaCreditsResponse> getActorMovieCredits(@Path(PATH_PERSON_ID) int personId);

    @GET("person/{person_id}/tv_credits")
    Observable<ActorCinemaCreditsResponse> getActorTvShowCredits(@Path(PATH_PERSON_ID) int personId);
}
