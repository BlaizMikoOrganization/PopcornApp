package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import java.util.List;

public interface TopMoviesView extends MvpView{

    void startLoad();

    void hideProgress();

    void setTopMoviesList(List<TileAdapter.Item> topMovies);
}
