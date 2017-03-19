package com.blaizmiko.popcornapp.ui.movies.popular;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface PopularMoviesView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setPopularMoviesList(List<TileAdapter.Item> popularMovies);
}
