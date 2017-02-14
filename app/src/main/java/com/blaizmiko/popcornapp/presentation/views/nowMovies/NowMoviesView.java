package com.blaizmiko.popcornapp.presentation.views.nowMovies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.models.movie.NowPlayingMovies;

public interface NowMoviesView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError();

    void setNowMoviesList(NowPlayingMovies nowPlayingMovies);
}