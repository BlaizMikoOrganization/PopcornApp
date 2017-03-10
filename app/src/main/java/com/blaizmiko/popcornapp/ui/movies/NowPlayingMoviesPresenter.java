package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class NowPlayingMoviesPresenter extends BaseMvpPresenter<NowPlayingMoviesView> {

    @Inject
    PealApi pealApi;
    private int currentPage = Constants.TheMovieDbApi.FirstPage;

    public NowPlayingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowMoviesList() {
        getViewState().startLoad();
        final Subscription nowMoviesSubscription = pealApi
                .getNowPlayingMovies(currentPage, Constants.TheMovieDbApi.NowMovieDefaultRegion)
                .flatMap(nowPlayingMovies -> Observable.from(nowPlayingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getId(), briefMovie.getBackdropPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setNowMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
