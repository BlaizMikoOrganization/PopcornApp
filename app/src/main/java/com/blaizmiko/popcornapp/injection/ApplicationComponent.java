package com.blaizmiko.popcornapp.injection;
import com.blaizmiko.popcornapp.injection.modules.ApplicationModule;
import com.blaizmiko.popcornapp.injection.modules.ApiModule;
import com.blaizmiko.popcornapp.injection.modules.NetworkModule;
import com.blaizmiko.popcornapp.ui.actors.PopularActorsPresenter;
import com.blaizmiko.popcornapp.ui.actors.details.DetailsActorPresenter;
import com.blaizmiko.popcornapp.ui.actors.details.biography.BiographyActorPresenter;
import com.blaizmiko.popcornapp.ui.actors.details.movies.MoviesActorPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.similarCinemas.SimilarCinemasPresenter;
import com.blaizmiko.popcornapp.ui.movies.reviews.ReviewPresenter;
import com.blaizmiko.popcornapp.ui.movies.details.info.InfoMoviePresenter;
import com.blaizmiko.popcornapp.ui.tvshows.details.info.InfoTvShowPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingPresenter;
import com.blaizmiko.popcornapp.ui.movies.nowplaying.NowPlayingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.popular.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.top.TopMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.upcoming.UpcomingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.details.review.ReviewsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.seasons.SeasonTvShowPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.nowplaying.NowPlayingTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.popular.PopularTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.top.TopTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.upcoming.UpcomingTvShowsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, NetworkModule.class})
public interface ApplicationComponent {

    //Similar cinemas
    void inject(SimilarCinemasPresenter similarCinemas);

    //Details
    void inject(CastPresenter castPresenter);

    //Movies
    void inject(NowPlayingMoviesPresenter moviesListPresenter);
    void inject(PopularMoviesPresenter popularMoviesPresenter);
    void inject(TopMoviesPresenter topRatedMoviesPresenter);
    void inject(UpcomingMoviesPresenter upcomingMoviesPresenter);
    void inject(RatingPresenter ratingPresenter);

    //Movie details
    void inject(ReviewsPresenter reviewsPresenter);
    void inject(InfoTvShowPresenter tvShowPresenter);
    void inject(InfoMoviePresenter infoMoviePresenter);

    //Movie reviews
    void inject(ReviewPresenter reviewPresenter);

    //Tv Shows
    void inject(PopularTvShowsPresenter popularTvShowsPresenter);
    void inject(TopTvShowsPresenter topTvShowsPresenter);
    void inject(NowPlayingTvShowsPresenter nowPlayingTVShowsPresenter);
    void inject(UpcomingTvShowsPresenter upcomingTvShowsPresenter);

    //Tv Shows episodes
    void inject(SeasonTvShowPresenter seasonTvShowPresenter);

    //Actors
    void inject(PopularActorsPresenter actorsListPresenter);
    void inject(DetailsActorPresenter detailsActorPresenter);
    void inject(BiographyActorPresenter biographyActorPresenter);
    void inject(MoviesActorPresenter moviesActorPresenter);


}
