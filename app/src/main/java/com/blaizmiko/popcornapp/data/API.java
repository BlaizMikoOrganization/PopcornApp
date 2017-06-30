package com.blaizmiko.popcornapp.data;

import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class API {
    @Inject
    Database database;
    @Inject
    MovieDbApi movieDbApi;

    public API() {
        BaseApplication.getComponent().inject(this);
    }

    public Observable<List<DetailedMovieDBModel>> getNowPlayingMoviesChart(final int page) {
        return movieDbApi.getNowPlayingMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .compose(transformToListAddBackdrop(page, DataManager.NOW_PLAYING_RESPONSE_ID));
    }

    public Observable<List<DetailedMovieDBModel>> getPopularMoviesChart(final int page) {
        return movieDbApi.getPopularMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .compose(transformToListAddPoster(page, DataManager.POPULAR_RESPONSE_ID));
    }

    public Observable<List<DetailedMovieDBModel>> getTopMoviesChart(final int page) {
        return movieDbApi.getTopRatedMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .compose(transformToListAddPoster(page, DataManager.TOP_RESPONSE_ID));
    }

    public Observable<List<DetailedMovieDBModel>> getUpcomingMovuesChart(final int page) {
        return movieDbApi.getUpcomingMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .compose(transformToListAddPoster(page, DataManager.UPCOMING_RESPONSE_ID));
    }

    <T> Observable.Transformer<MoviesResponseDBModel, List<DetailedMovieDBModel>> transformToListAddBackdrop(final int page, final int responseID) {
        return observable -> observable
            .flatMap(moviesResponseDBModel -> Observable.from(moviesResponseDBModel.getMovies()))
            .filter(briefMovie -> briefMovie != null)
            .map(detailedMovieDBModel -> {
                detailedMovieDBModel.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + detailedMovieDBModel.getBackdropPath());
                return detailedMovieDBModel;
            })
            .toList()
            .map(detailedMovieDBModels -> {
                database.putMovieResponse(detailedMovieDBModels, page, responseID);
                return detailedMovieDBModels;
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    <T> Observable.Transformer<MoviesResponseDBModel, List<DetailedMovieDBModel>> transformToListAddPoster(final int page, final int responseID) {
        return observable -> observable
            .flatMap(moviesResponseDBModel -> Observable.from(moviesResponseDBModel.getMovies()))
            .filter(briefMovie -> briefMovie != null)
            .map(detailedMovieDBModel -> {
                detailedMovieDBModel.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + detailedMovieDBModel.getPosterPath());
                return detailedMovieDBModel;
            })
            .toList()
            .map(detailedMovieDBModels -> {
                database.putMovieResponse(detailedMovieDBModels, page, responseID);
                return detailedMovieDBModels;
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
