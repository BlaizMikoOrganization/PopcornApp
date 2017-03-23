package com.blaizmiko.popcornapp.ui.all.presentation.rating;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.rating.RatingResponse;

public interface RatingView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setFullRating(RatingResponse rating);
}
