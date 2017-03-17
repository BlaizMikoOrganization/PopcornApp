package com.blaizmiko.popcornapp.ui.movies.details.cast;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class CastPresenter extends BaseMvpPresenter<CastView> {

    @Inject
    public MovieDbApi movieDbApi;

    public CastPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadCast(int movieId) {
        getViewState().startLoad();
        final Subscription creditsMovieSubscription = movieDbApi.getCredits(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(credits -> {
                    getViewState().setCast(credits.getCast());
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(creditsMovieSubscription);
    }

}
