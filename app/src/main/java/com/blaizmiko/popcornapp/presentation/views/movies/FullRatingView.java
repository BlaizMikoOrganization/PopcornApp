package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.models.rating.Rating;

public interface FullRatingView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setFullRating(Rating rating);
}
