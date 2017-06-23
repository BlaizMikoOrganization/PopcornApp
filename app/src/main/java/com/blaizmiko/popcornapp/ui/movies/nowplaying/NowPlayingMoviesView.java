package com.blaizmiko.popcornapp.ui.movies.nowplaying;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface NowPlayingMoviesView extends MvpView {
    void showError();
    void finishLoad();
    void startLoad();
    void showNowMoviesList(final List<? extends TileAdapter.ITileItem> nowPlayingMovies);
}