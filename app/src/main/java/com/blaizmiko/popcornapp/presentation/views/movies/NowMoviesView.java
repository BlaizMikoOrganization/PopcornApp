package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.models.movies.NowPlayingMovies;

public interface NowMoviesView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError();

    void setNowMoviesList(NowPlayingMovies nowPlayingMovies);
}