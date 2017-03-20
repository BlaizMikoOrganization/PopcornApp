package com.blaizmiko.popcornapp.ui.all.presentation.details.info.tvshow;

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
public class InfoTvShowPresenter extends BaseMvpPresenter<InfoTvShowView>{
    @Inject
    MovieDbApi movieDbApi;

    InfoTvShowPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    private void loadTvShowInfo(int tvShowId) {
        final Subscription creditsMovieSubscription = movieDbApi.getTvShow(tvShowId, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.MovieInfoAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    getViewState().setTvShowInfo(info);
                }, error -> {
                    error.getStackTrace();
                    //getViewState().finishLoad();
                    //getViewState().showError();
                }); //() -> getViewState().finishLoad());
        unSubscribeOnDestroy(creditsMovieSubscription);
    }
}
