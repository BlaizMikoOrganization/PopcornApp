package com.blaizmiko.popcornapp.ui.movies.details.review;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.movies.Review;

import java.util.List;

public interface ReviewsView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setReviews(List<Review> reviews);
}
