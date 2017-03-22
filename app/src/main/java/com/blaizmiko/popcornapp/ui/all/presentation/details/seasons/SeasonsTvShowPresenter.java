package com.blaizmiko.popcornapp.ui.all.presentation.details.seasons;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class SeasonsTvShowPresenter extends BaseMvpPresenter<SeasonsTvShowView>{

    SeasonsTvShowPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Inject
    MovieDbApi movieDbApi;

    public void loadSeasons(int tvShowId) {
        Subscription seasonsTvShowSubsciption = movieDbApi.getTvShow
    }


}
