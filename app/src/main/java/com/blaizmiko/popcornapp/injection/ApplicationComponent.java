package com.blaizmiko.popcornapp.injection;

import com.blaizmiko.popcornapp.injection.modules.ApplicationModule;
import com.blaizmiko.popcornapp.injection.modules.ApiModule;
import com.blaizmiko.popcornapp.injection.modules.NetworkModule;
import com.blaizmiko.popcornapp.ui.actors.PopularActorsPresenter;
import com.blaizmiko.popcornapp.ui.actors.details.ActorDetailsPresenter;
import com.blaizmiko.popcornapp.ui.movies.FullRatingPresenter;
import com.blaizmiko.popcornapp.ui.movies.MovieDetailsPresenter;
import com.blaizmiko.popcornapp.ui.movies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.TopMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.UpcomingMoviesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(PopularActorsPresenter actorsListPresenter);

    void inject(NowMoviesPresenter moviesListPresenter);

    void inject(PopularMoviesPresenter popularMoviesPresenter);

    void inject(TopMoviesPresenter topRatedMoviesPresenter);

    void inject(UpcomingMoviesPresenter upcomingMoviesPresenter);

    void inject(MovieDetailsPresenter movieDetailsPresenter);

    void inject(FullRatingPresenter fullRatingPresenter);

    void inject(ActorDetailsPresenter actorDetailsPresenter);

}
