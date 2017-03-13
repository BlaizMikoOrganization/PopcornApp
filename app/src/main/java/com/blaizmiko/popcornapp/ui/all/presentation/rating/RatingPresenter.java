package com.blaizmiko.popcornapp.ui.all.presentation.rating;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.OMDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class RatingPresenter extends BaseMvpPresenter <RatingView>{

    @Inject
    OMDbApi OMDbApi;

    public RatingPresenter() {
        BaseApplication.getComponent().inject(this);
    }


    public void loadRating(String id) {
        getViewState().startLoad();
        final Subscription ratingSubscription = OMDbApi.getRating(id)
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
