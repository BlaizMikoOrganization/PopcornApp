package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.api.OMDbApi;
import com.blaizmiko.popcornapp.ui.all.presenters.BaseMvpPresenter;
import com.blaizmiko.popcornapp.ui.movies.FullRatingView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class FullRatingPresenter extends BaseMvpPresenter <FullRatingView>{

    @Inject
    OMDbApi mOMDbApi;

    public FullRatingPresenter() {
        BaseApplication.getComponent().inject(this);
    }


    public void loadRating(String id) {
        getViewState().startLoad();
        final Subscription ratingSubscription = mOMDbApi.getRating(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rating -> {
                    getViewState().setFullRating(rating);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(ratingSubscription);
    }

}
