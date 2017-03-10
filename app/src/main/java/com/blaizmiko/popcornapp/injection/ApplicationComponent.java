package com.blaizmiko.popcornapp.injection;

import com.blaizmiko.popcornapp.injection.modules.ApplicationModule;
import com.blaizmiko.popcornapp.injection.modules.ApiModule;
import com.blaizmiko.popcornapp.injection.modules.NetworkModule;
import com.blaizmiko.popcornapp.ui.actors.PopularActorsPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingPresenter;
import com.blaizmiko.popcornapp.ui.movies.details.MovieDetailsPresenter;
import com.blaizmiko.popcornapp.ui.movies.NowPlayingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.TopMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.UpcomingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.NowPlayingTVShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.PopularTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.TopTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.UpcomingTvShowsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, NetworkModule.class})
public interface ApplicationComponent {
    //Movies
    void inject(PopularActorsPresenter actorsListPresenter);
    void inject(NowPlayingMoviesPresenter moviesListPresenter);
    void inject(PopularMoviesPresenter popularMoviesPresenter);
    void inject(TopMoviesPresenter topRatedMoviesPresenter);
    void inject(UpcomingMoviesPresenter upcomingMoviesPresenter);
    void inject(MovieDetailsPresenter movieDetailsPresenter);
    void inject(RatingPresenter ratingPresenter);

    //Tv Shows
    void inject(PopularTvShowsPresenter popularTvShowsPresenter);
    void inject(TopTvShowsPresenter topTvShowsPresenter);
    //void inject(NowPlayingTVShowsPresenter nowPlayingTVShowsPresenter);
    //void inject(UpcomingTvShowsPresenter upcomingTvShowsPresenter);

}
