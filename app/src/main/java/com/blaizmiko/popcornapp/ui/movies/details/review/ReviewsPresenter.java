package com.blaizmiko.popcornapp.ui.movies.details.review;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
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
    MovieDbApi movieDbApi;


    public void loadReviews(int movieId) {
        final int zeroReviewsAmount = 0;
        getViewState().startLoad();
        final Subscription reviewsSubscription = movieDbApi.getMovieReview(movieId, Constants.MovieDbApi.FirstPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieReviews -> {
                    if (movieReviews.getReviews().size() > zeroReviewsAmount) {
                        getViewState().showReviews(movieReviews.getReviews());
                        return;
                    }
                    getViewState().showNoReviewsView();
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(reviewsSubscription);
    }
}
