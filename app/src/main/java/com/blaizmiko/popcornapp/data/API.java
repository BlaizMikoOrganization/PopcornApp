package com.blaizmiko.popcornapp.data;

import android.util.Log;

import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.FormatUtil;
import com.blaizmiko.popcornapp.data.db.models.cast.Cast;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ReviewDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ReviewResponse;

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


    //------------------------------ Movie Responses ----------------------------------------------
    //---------------------------------------------------------------------------------------------

    public Observable<List<DetailedMovieDBModel>> getPopularMoviesChart(final int page) {
        return movieDbApi.getPopularMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .compose(transformToListAddPoster(page, DataManager.POPULAR_RESPONSE_ID));
    }

    public Observable<List<DetailedMovieDBModel>> getTopMoviesChart(final int page) {
        return movieDbApi.getTopRatedMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .compose(transformToListAddPoster(page, DataManager.TOP_RESPONSE_ID));
    }

    public Observable<List<DetailedMovieDBModel>> getUpcomingMoviesChart(final int page) {
        return movieDbApi.getUpcomingMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
            .compose(transformToListAddPoster(page, DataManager.UPCOMING_RESPONSE_ID));
    }

    public API() {
        BaseApplication.getComponent().inject(this);
    }

    public Observable<List<DetailedMovieDBModel>> getNowPlayingMoviesChart(final int page) {
        return movieDbApi.getNowPlayingMovies(page, Constants.MovieDbApi.NowMovieDefaultRegion)
                .compose(transformToListAddBackdrop(page, DataManager.NOW_PLAYING_RESPONSE_ID));
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


    //------------------------------ Movie --------------------------------------------------------
    //---------------------------------------------------------------------------------------------

    public Observable<DetailedMovieDBModel> getMovie(final long id) {
        return movieDbApi.getMovieInfo(id, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.InfoDetailsMovieAppendToResponse)
            .map(movie -> {
                movie = formatMovie(movie);
                database.putMovie(movie);
                return movie;
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    private DetailedMovieDBModel formatMovie(final DetailedMovieDBModel movie) {
        final String formattedReleaseDate = FormatUtil.parseDateToMaterialFormat(movie.getReleaseDate(), FormatUtil.ResultMaterialDateType.FULL);
        movie.setReleaseDate(formattedReleaseDate);
        final String formattedRuntime = FormatUtil.parseTimeToMaterialFormat(movie.getRuntime());
        movie.setRuntime(formattedRuntime);
        final String formattedBudget = FormatUtil.parseMoneyToMaterialFormat(movie.getBudget());
        movie.setBudget(formattedBudget);
        final String formattedRevenue = FormatUtil.parseMoneyToMaterialFormat(movie.getRevenue());
        movie.setRevenue(formattedRevenue);
        return movie;
    }

    //------------------------------ Movie Cast ---------------------------------------------------
    //---------------------------------------------------------------------------------------------

    public Observable<List<Cast>> getCast(final long movieId) {
        return movieDbApi.getMovieCredits(movieId)
            .map(creditsResponse -> creditsResponse.getCast())
            .map(casts ->{
                 database.putCasts(casts, movieId);
                 return casts;
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    //------------------------------ Movie Reviews ------------------------------------------------
    //---------------------------------------------------------------------------------------------

    public Observable<List<ReviewDBModel>> getReviews(final long movieId) {
        return movieDbApi.getMovieReview(movieId, Constants.MovieDbApi.FirstPage)
                .map(ReviewResponse::getReviews)
                .map(reviews -> {
                    database.putReviews(reviews, movieId);
                    return reviews;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
