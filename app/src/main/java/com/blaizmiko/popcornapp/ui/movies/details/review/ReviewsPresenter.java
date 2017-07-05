package com.blaizmiko.popcornapp.ui.movies.details.review;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.data.db.models.movies.ReviewDBModel;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ReviewsPresenter extends BaseMvpPresenter<ReviewsView> {

    public ReviewsPresenter() {
        BaseApplication.getComponent().inject(this);
    }
    @Inject
    DataManager dataManager;


    public void loadReviews(long movieId) {
        final int zeroReviewsAmount = 0;
        getViewState().startLoad();

        Subscription reviewsSubscription = dataManager.getReviews(movieId)
            .subscribe(reviews ->
                //if (reviews.size() > zeroReviewsAmount) {
                    getViewState().showReviews(reviews)
/*                    return;
                }*/
                //getViewState().showNoReviewsView();
            , error -> {
                error.printStackTrace();
                getViewState().finishLoad();
                getViewState().showError();
            }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(reviewsSubscription);
    }
}
