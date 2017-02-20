package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.adapters.movies.TileAdapter;

import java.util.List;

public interface NowMoviesView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError();

    void setNowMoviesList(List<TileAdapter.Item> nowPlayingMovies);
}