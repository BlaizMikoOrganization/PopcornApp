package com.blaizmiko.popcornapp.presentation.views.popularMovies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.adapters.movies.TileAdapter;

import java.util.List;

/**
 * Created by Uladzislau_Nikitsin on 16.02.2017.
 */

public interface PopularMoviesView extends MvpView{

    void showProgress();

    void hideProgress();

    void showError();

    void setPopularMoviesList(List<TileAdapter.Item> popularMovies);
}
