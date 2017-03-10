package com.blaizmiko.popcornapp.common.api;

import com.blaizmiko.popcornapp.data.models.actors.ActorModel;
import com.blaizmiko.popcornapp.data.models.actors.PopularActorsModel;
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

    //Movies
    @GET("movie/popular")
    Observable<PopularMovies> getPopularMovies(@Query("page") int page,
                                               @Query("region") String region);

    @GET("movie/now_playing")
    Observable<NowPlayingMovies> getNowPlayingMovies(@Query("page") int page,
                                                     @Query("region") String region);

    @GET("movie/top_rated")
    Observable<TopRatedMovies> getTopRatedMovies(@Query("page") int page,
                                                 @Query("region") String region);

    @GET("movie/upcoming")
    Observable<UpcomingMovies> getUpcomingMovies(@Query("page") int page,
                                                 @Query("region") String region);
    @GET("movie/{movie_id}")
    Observable<DetailedMovie> getMovie(@Path("movie_id") int movie,
                                       @Query("include_image_language") String include_image_language,
                                       @Query("append_to_response") String append_to_response);


    //Actors
    @GET("person/popular")
    Observable<PopularActorsModel> getPopularActors(@Query("page") int page);

    @GET("person/{person_id}")
    Observable<ActorModel> getActorDetails(@Path("person_id") int actorId);
}
