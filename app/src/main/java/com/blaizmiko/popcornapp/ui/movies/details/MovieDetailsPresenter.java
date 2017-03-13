package com.blaizmiko.popcornapp.ui.movies.details;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class MovieDetailsPresenter extends BaseMvpPresenter<MovieDetailsView>{

    @Inject
    PealApi pealApi;

    public MovieDetailsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMovie(int id) {
        getViewState().startLoad();
        final Subscription detailMovieSubscription = pealApi.getMovie(id, Constants.TheMovieDbApi.IncludeImageLanguage, Constants.TheMovieDbApi.MovieDetailsAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    getViewState().setMovie(movie);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(detailMovieSubscription);
    }
}
