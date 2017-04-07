package com.blaizmiko.popcornapp.ui.movies.upcoming;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface UpcomingMoviesView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void showUpcomingMoviesList(final List<TileAdapter.Item> topRatedMovies);
}
