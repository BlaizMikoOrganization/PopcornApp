package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class NowMoviesPresenter extends BaseMvpPresenter<NowMoviesView> {
    @Inject
    PealApi mPealApi;

    int mCurrentPage = Constants.Api.FirstPage;

    public NowMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowMoviesList() {
        getViewState().showProgress();

        final Subscription nowMoviesSubscription = mPealApi
                .getNowPlayingMovies(Constants.Api.ApiKey, Constants.Api.Language, mCurrentPage, Constants.Api.NowMovieDefaultRegion)
                .flatMap(nowPlayingMovies -> Observable.from(nowPlayingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getBackdropPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setNowMoviesList(moviesList);
                    mCurrentPage++;
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
