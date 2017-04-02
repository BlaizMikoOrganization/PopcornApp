package com.blaizmiko.popcornapp.ui.tvshows.seasons;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class SeasonTvShowPresenter extends BaseMvpPresenter<SeasonTvShowView>{

    SeasonTvShowPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Inject
    protected MovieDbApi movieDbApi;

    public void loadEpisodes(final int tvShowId, final int seasonNumber) {
        getViewState().startLoad();

        final Subscription episodeSubscription = movieDbApi.getTvShowsSeasonInfo(tvShowId, seasonNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(season -> {
                    getViewState().setEpisodeInfo(season.getEpisodes());
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(episodeSubscription);
    }
}
