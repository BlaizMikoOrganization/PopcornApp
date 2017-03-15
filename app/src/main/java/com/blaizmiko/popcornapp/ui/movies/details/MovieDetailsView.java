package com.blaizmiko.popcornapp.ui.movies.details;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovie;

public interface MovieDetailsView extends MvpView {
    void showError();
    void finishLoad();
    void startLoad();
    void setMovie(DetailedMovie movie);
    void setMovieDirector(String directorName);
    void setFormattedReleaseDate(String releaseDate);
    void setFormattedRuntime(String runtime);
    void setFormattedBudget(String budget);
    void setFormattedRevenue(String budget);
}
