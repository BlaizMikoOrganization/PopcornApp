package com.blaizmiko.popcornapp.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class DataManager {
    @Inject
    API api;
    @Inject
    Context applicationContext;
    @Inject
    Database database;

    public static final int NOW_PLAYING_RESPONSE_ID = 1;
    public static final int POPULAR_RESPONSE_ID = 2;
    public static final int TOP_RESPONSE_ID = 3;
    public static final int UPCOMING_RESPONSE_ID = 4;


    public DataManager() {
        BaseApplication.getComponent().inject(this);
    }

    private boolean hasInternetConnection() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public Observable<List<DetailedMovieDBModel>> getNowPlayingMovies(final int page) {
        return hasInternetConnection() ? api.getNowPlayingMoviesChart(page) : database.getMoviesResponse(NOW_PLAYING_RESPONSE_ID, page);
    }

    public Observable<List<DetailedMovieDBModel>> getPopularMovies(final int page) {
        return hasInternetConnection() ? api.getPopularMoviesChart(page) : database.getMoviesResponse(POPULAR_RESPONSE_ID, page);
    }

    public Observable<List<DetailedMovieDBModel>> getTopMovies(final int page) {
        return hasInternetConnection() ? api.getTopMoviesChart(page) : database.getMoviesResponse(TOP_RESPONSE_ID, page);
    }

    public Observable<List<DetailedMovieDBModel>> getUpcomingMovies(final int page) {
        return hasInternetConnection() ? api.getUpcomingMoviesChart(page) : database.getMoviesResponse(UPCOMING_RESPONSE_ID, page);
    }

    public Observable<DetailedMovieDBModel> getMovie(final long movieId) {
        return hasInternetConnection() ? api.getMovie(movieId) : database.getMovie(movieId);
    }
}
