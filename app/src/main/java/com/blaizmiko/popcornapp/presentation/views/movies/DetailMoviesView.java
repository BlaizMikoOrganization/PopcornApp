package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.models.movies.Movie;

public interface DetailMoviesView extends MvpView {
    void setMovie(Movie movie);
}
