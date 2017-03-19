package com.blaizmiko.popcornapp.ui.movies.top;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface TopMoviesView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setTopMoviesList(List<TileAdapter.Item> topMovies);
}
