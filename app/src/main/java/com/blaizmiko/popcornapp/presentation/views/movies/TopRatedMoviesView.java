package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import java.util.List;

public interface TopRatedMoviesView extends MvpView{

    void showProgress();

    void hideProgress();

    void showError();

    void setTopRatedMoviesList(List<TileAdapter.Item> topRatedMovies);
}
