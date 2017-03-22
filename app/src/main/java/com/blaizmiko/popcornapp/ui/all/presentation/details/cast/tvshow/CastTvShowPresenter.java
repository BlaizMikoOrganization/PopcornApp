package com.blaizmiko.popcornapp.ui.all.presentation.details.cast.tvshow;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.cast.base.CastView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class CastTvShowPresenter extends BaseMvpPresenter<CastView>{

    public CastTvShowPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Inject
    MovieDbApi movieDbApi;

    public static CastTvShowPresenter newInstance() {
        return new CastTvShowPresenter();
    }

    public void loadCast(int tvShowId) {
        getViewState().startLoad();
        Subscription castTvShowSubscription =  movieDbApi.getTvShowCredits(tvShowId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(credits -> {
                    getViewState().setCast(credits.getCast());
                }, error -> {
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(castTvShowSubscription);
    }
}
