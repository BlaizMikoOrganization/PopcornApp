package com.blaizmiko.popcornapp.ui.all.presentation.rating;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.rating.RatingModel;

import java.util.List;

public interface RatingView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();

    void showFullRating(final List<RatingModel> ratings);
}
