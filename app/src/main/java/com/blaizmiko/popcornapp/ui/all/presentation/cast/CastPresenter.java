package com.blaizmiko.popcornapp.ui.all.presentation.cast;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.data.models.cast.CreditsResponse;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class CastPresenter extends BaseMvpPresenter<CastView>{

    private final int zeroCastAmount = 0;

    public CastPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Inject
    MovieDbApi movieDbApi;
    @Inject
    DataManager dataManager;

    public void loadMovieCast(final long movieId) {
        getViewState().startLoad();
        dataManager.getCast(movieId)
            .subscribe(castList -> getViewState().showCast(castList)
        , error -> {
            getViewState().finishLoad();
            getViewState().showError();
        }, () -> getViewState().finishLoad());
    }

    public void loadTvShowCast(long tvShowId) {
        getViewState().startLoad();
        loadCast(movieDbApi.getTvShowCredits(tvShowId));
    }

    private void loadCast(Observable<CreditsResponse> observable) {
        Subscription castSubscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(creditsResponse -> Observable.from(creditsResponse.getCast()))
                .distinct(cast -> cast.getId())
                .map(cast -> {
                    if (cast.getCharacter().length() == zeroCastAmount) {
                        cast.setCharacter(StringUtil.NOT_AVAILABLE_STRING);
                    }
                    return cast;
                })
                .toList()
                .subscribe(casts -> {
                    //getViewState().showCast(casts);
                }, error -> {
                    getViewState().showError();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(castSubscription);
    }
}
