package com.blaizmiko.popcornapp.ui.tvshows.details;

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
public class TvShowDetailsPresenter extends BaseMvpPresenter<TvShowDetailsView> {
    @Inject
    MovieDbApi pealApi;

    public TvShowDetailsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadTvShow(int id) {
        getViewState().startLoad();
        final Subscription tvShowDetailsSubscription = pealApi.getTvShow(id, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.TvShowDetailsAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShow -> {
                    getViewState().setTvShow(tvShow);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(tvShowDetailsSubscription);
    }
}