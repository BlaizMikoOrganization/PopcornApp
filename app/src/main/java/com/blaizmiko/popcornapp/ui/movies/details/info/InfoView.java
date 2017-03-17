package com.blaizmiko.popcornapp.ui.movies.details.info;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovie;
import com.blaizmiko.popcornapp.data.models.rating.Rating;

public interface InfoView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setInfo(DetailedMovie movie);
    void setFormattedReleaseDate(String releaseDate);
    void setFormattedRuntime(String runtime);
    void setFormattedBudget(String budget);
    void setFormattedRevenue(String budget);
    void setFullRating(Rating rating);
}
