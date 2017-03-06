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

    private int mCurrentPage = Constants.TheMovieDbApi.FirstPage;

    public NowMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowMoviesList() {
        getViewState().startLoad();
        final Subscription nowMoviesSubscription = mPealApi
                .getNowPlayingMovies(Constants.TheMovieDbApi.ApiKey, Constants.TheMovieDbApi.Language, mCurrentPage, Constants.TheMovieDbApi.NowMovieDefaultRegion)
                .flatMap(nowPlayingMovies -> Observable.from(nowPlayingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getId(), briefMovie.getBackdropPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setNowMoviesList(moviesList);
                    mCurrentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
