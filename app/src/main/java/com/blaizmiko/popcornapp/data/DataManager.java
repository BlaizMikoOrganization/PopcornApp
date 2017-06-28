package com.blaizmiko.popcornapp.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.db.DataConsumer;
import com.blaizmiko.popcornapp.data.db.Database;
import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataManager {
    @Inject
    MovieDbApi movieDbApi;
    @Inject
    Context applicationContext;
    @Inject
    Database database;

    public DataManager() {
        BaseApplication.getComponent().inject(this);
    }

    private boolean hasInternetConnection() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void getNowPlayingMovies(final DataConsumer dataConsumer, final int page) {
        if (hasInternetConnection()) {
            getNowPlayingMoviesFromAPI(dataConsumer, page);
            return;
        }
        database.getNowPlayingMovies(dataConsumer, page);
    }

    private void getNowPlayingMoviesFromAPI(DataConsumer dataConsumer, final int page){
        movieDbApi.getNowPlayingMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .flatMap(baseMovieListResponse -> Observable.from(baseMovieListResponse.getMovies()))
            .filter(movie -> movie != null)
            .map(movie -> {
                movie.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + movie.getBackdropPath());
                return movie;
            })
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( detailedMovieDBModels -> {
                database.putNowPlayingMovies(detailedMovieDBModels, page);
                dataConsumer.consumeMoviesList(detailedMovieDBModels);});
    }
}
