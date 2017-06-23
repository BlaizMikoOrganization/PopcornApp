package com.blaizmiko.popcornapp.ui.movies.top;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface TopMoviesView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void showTopMoviesList(final List<? extends TileAdapter.ITileItem> topMovies);
}
