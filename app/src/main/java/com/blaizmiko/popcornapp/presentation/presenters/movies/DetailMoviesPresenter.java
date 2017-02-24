package com.blaizmiko.popcornapp.presentation.presenters.movies;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailMoviesPresenter extends BaseMvpPresenter{

    @Inject
    PealApi mPealApi;

    public DetailMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMovie(int id) {
        final Subscription detailMovieSubscription = mPealApi.getMovie(id, Constants.Api.ApiKey, Constants.Api.Language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    System.out.println("title" +moviesList.getTitle());
                });

        unSubscribeOnDestroy(detailMovieSubscription);
    }

}
