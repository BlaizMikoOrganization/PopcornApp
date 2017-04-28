package com.blaizmiko.popcornapp.ui.all.presentation;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.models.cinema.BriefCinema;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class BaseDetailsPresenter extends BaseMvpPresenter<BaseDetailsView>{
    @Inject
    MovieDbApi movieDbApi;

    public BaseDetailsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadBriefMovie(final int movieId) {
        unSubscribeOnDestroy(loadBriefCinemaInfo(movieDbApi.getBriefMovieInfo(movieId, Constants.MovieDbApi.IncludeImageLanguage, "")));
    }

    public void loadBriefTvShow(final int tvShowId) {
        unSubscribeOnDestroy(loadBriefCinemaInfo(movieDbApi.getBriefMovieInfo(tvShowId, Constants.MovieDbApi.IncludeImageLanguage, "")));
    }

    public Subscription loadBriefCinemaInfo(final Observable<BriefCinema> observable) {
        getViewState().startLoad();
        Log.d("loadBriefCinema", "here");
        final Subscription briefCinemaSubscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    getViewState().showToolbar(info);
                }, error -> {
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        return briefCinemaSubscription;
    }
}
