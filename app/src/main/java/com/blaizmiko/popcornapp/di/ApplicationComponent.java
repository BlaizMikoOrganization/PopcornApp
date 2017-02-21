package com.blaizmiko.popcornapp.di;

import com.blaizmiko.popcornapp.di.modules.ApplicationModule;
import com.blaizmiko.popcornapp.di.modules.ApiModule;
import com.blaizmiko.popcornapp.di.modules.NetworkModule;
import com.blaizmiko.popcornapp.presentation.presenters.actors.ActorsPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.TopMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.UpcomingMoviesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(ActorsPresenter actorsListPresenter);
    void inject(NowMoviesPresenter moviesListPresenter);
    void inject(PopularMoviesPresenter popularMoviesPresenter);
    void inject(TopMoviesPresenter topRatedMoviesPresenter);
    void inject(UpcomingMoviesPresenter upcomingMoviesPresenter);
}
