package com.blaizmiko.popcornapp.presentation.views.popularMovies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.models.movie.NowPlayingMovies;
import com.blaizmiko.popcornapp.models.movie.PopularMovies;

/**
 * Created by Uladzislau_Nikitsin on 16.02.2017.
 */

public interface PopularMoviesView extends MvpView{

    void showProgress();

    void hideProgress();

    void showError();

    void setPopularMoviesList(PopularMovies popularMovies);
}
