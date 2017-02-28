package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.models.movies.Movie;

public interface MovieDetailsView extends MvpView {
    void showError();
    void finishLoad();
    void startLoad();
    void setMovie(Movie movie);
    void expandStoryLine();
    void hideStoryLine();
}
