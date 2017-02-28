package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.MovieDetailsView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class MovieDetailsPresenter extends BaseMvpPresenter<MovieDetailsView>{

    @Inject
    PealApi mPealApi;

    public MovieDetailsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMovie(int id) {
        getViewState().startLoad();
        final Subscription detailMovieSubscription = mPealApi.getMovie(id, Constants.Api.ApiKey, Constants.Api.Language)
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
