package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.popularMovies.PopularMoviesView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class PopularMoviesPresenter extends BaseMvpPresenter<PopularMoviesView> {
    @Inject
    PealApi mPealApi;

    public PopularMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPopularMoviesList() {

        final Subscription popularMoviesSubscription = mPealApi.getPopularMovies(Constants.Api.ApiKey, Constants.Api.Language, Constants.Api.FirstPage, Constants.Api.NowMovieDefaultRegion)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularMovies -> {
                    getViewState().setPopularMoviesList(popularMovies);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(popularMoviesSubscription);
    }
}
