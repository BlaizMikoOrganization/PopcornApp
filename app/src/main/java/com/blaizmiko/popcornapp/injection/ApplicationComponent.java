package com.blaizmiko.popcornapp.injection;
import com.blaizmiko.popcornapp.injection.modules.ApplicationModule;
import com.blaizmiko.popcornapp.injection.modules.ApiModule;
import com.blaizmiko.popcornapp.injection.modules.NetworkModule;
import com.blaizmiko.popcornapp.ui.actors.PopularActorsPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingPresenter;
import com.blaizmiko.popcornapp.ui.movies.nowplaying.NowPlayingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.popular.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.top.TopMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.upcoming.UpcomingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.cast.CastPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.InfoPresenter;
import com.blaizmiko.popcornapp.ui.movies.details.review.ReviewsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.NowPlayingTvShowsPresenter;
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

    void inject(RatingPresenter ratingPresenter);

    //Movie details
    void inject(CastPresenter castPresenter);
    void inject(ReviewsPresenter reviewsPresenter);
    void inject(InfoPresenter infoPresenter);
    void inject(GenresTagsPresenter genresTagsPresenter);

    //Tv Shows
    void inject(PopularTvShowsPresenter popularTvShowsPresenter);
    void inject(TopTvShowsPresenter topTvShowsPresenter);
    void inject(NowPlayingTvShowsPresenter nowPlayingTVShowsPresenter);
    void inject(UpcomingTvShowsPresenter upcomingTvShowsPresenter);

}
