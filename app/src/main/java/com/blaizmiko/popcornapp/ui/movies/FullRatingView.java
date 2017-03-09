package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.rating.Rating;

public interface FullRatingView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setFullRating(Rating rating);
}
