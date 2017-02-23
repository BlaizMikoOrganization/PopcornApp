package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import java.util.List;

public interface NowMoviesView extends MvpView {
    void showError();
    void finishLoad();
    void startLoad();
    void setNowMoviesList(List<TileAdapter.Item> nowPlayingMovies);
}