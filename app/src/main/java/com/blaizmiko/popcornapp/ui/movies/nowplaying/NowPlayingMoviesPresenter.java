package com.blaizmiko.popcornapp.ui.movies.nowplaying;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class NowPlayingMoviesPresenter extends BaseMvpPresenter<NowPlayingMoviesView> {
    @Inject
    MovieDbApi movieDbApi;
    private int currentPage = Constants.MovieDbApi.FirstPage;


    @Inject
    Database database;

    public NowPlayingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowMoviesList() {
        getViewState().startLoad();
        final Subscription nowMoviesSubscription = movieDbApi
                .getNowPlayingMovies(currentPage, Constants.MovieDbApi.NowMovieDefaultRegion)
                .flatMap(baseMovieListResponse -> Observable.from(baseMovieListResponse.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(detailedMovieDBModel -> {
                    detailedMovieDBModel.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + detailedMovieDBModel.getBackdropPath());
                    return detailedMovieDBModel;
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    database.putNowPlayingMovies(moviesList);
                    getViewState().showNowMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    Log.d("Perrror", ""+error.getMessage());
                    error.printStackTrace();
                    database.getNowPlayingMovies();
                    error.printStackTrace();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
