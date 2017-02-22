package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.models.movies.BriefMovie;
import com.blaizmiko.popcornapp.models.movies.NowPlayingMovies;
import com.blaizmiko.popcornapp.presentation.presenters.Loader;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@InjectViewState
public class NowMoviesPresenter extends BaseMvpPresenter<NowMoviesView> implements Loader {
    @Inject
    PealApi mPealApi;

    int mCurrentPage = Constants.Api.FirstPage;
    private boolean mIsLoading = false;

    public NowMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Override
    public void load() {
        if(mIsLoading) return;
        mIsLoading = true;
        loadNowMoviesList();
    }

    public void loadNowMoviesList() {
        getViewState().showProgress();

        final Subscription nowMoviesSubscription = mPealApi
                .getNowPlayingMovies(Constants.Api.ApiKey, Constants.Api.Language, mCurrentPage++, Constants.Api.NowMovieDefaultRegion)
                .flatMap(nowPlayingMovies -> Observable.from(nowPlayingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getBackdropPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    mIsLoading = false;
                    getViewState().setNowMoviesList(moviesList);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
