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

    public void loadBriefMovie(final long movieId, final String cinemaName, final String backdropUrl) {
        Log.d("movie", "here");
        if (cinemaName != null && backdropUrl != null) {
            Log.d("movieIn", ""+cinemaName +" " +backdropUrl);
            getViewState().showToolbar(new BriefCinema(cinemaName, backdropUrl));
            return;
        }
        unSubscribeOnDestroy(loadBriefCinemaInfo(movieDbApi.getBriefMovieInfo(movieId, Constants.MovieDbApi.IncludeImageLanguage, "")));
    }

    public void loadBriefTvShow(final long tvShowId, final String cinemaName, final String backdropUrl) {
        Log.d("tvShow", "here");
        if (cinemaName != null && backdropUrl != null) {
            getViewState().showToolbar(new BriefCinema(cinemaName, backdropUrl));
            return;
        }
        unSubscribeOnDestroy(loadBriefCinemaInfo(movieDbApi.getBriefTvShowInfo(tvShowId, Constants.MovieDbApi.IncludeImageLanguage, "")));
    }

    private Subscription loadBriefCinemaInfo(final Observable<BriefCinema> observable) {
        getViewState().startLoad();
        final Subscription briefCinemaSubscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    Log.d("loadBrieft", "succes");
                    Log.d("info ", "" +info.getTitle() +" " +info.getBackdropPath());
                    getViewState().showToolbar(info);
                }, error -> {
                    Log.d("tag", ""+error.getMessage());
                    getViewState().showError();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());
        return briefCinemaSubscription;
    }
}
