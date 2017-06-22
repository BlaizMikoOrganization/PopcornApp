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

    public void loadNowMoviesList(final Database.DBUpdateNowPlayingMovies view) {
        getViewState().startLoad();
        final Subscription nowMoviesSubscription = movieDbApi
                .getNowPlayingMovies(currentPage, Constants.MovieDbApi.NowMovieDefaultRegion)
                .doOnNext(nowMovies -> database.saveMovieResponse(nowMovies))
                .flatMap(baseMovieListResponse -> Observable.from(baseMovieListResponse.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getId(), briefMovie.getBackdropPath(), briefMovie.getTitle(), briefMovie.getVoteAverage(), briefMovie.getBackdropPath(), briefMovie.getPosterPath()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    //database.subscribeToRestoreDetailedMovie(view);
                    getViewState().showNowMoviesList(moviesList);
                    currentPage++;
                    List<DetailedMovieDBModel> movies = database.getNowPlayingMovies();
                    for (DetailedMovieDBModel movie : movies) {
                        Log.d("movie pish", ""+movie);
                    }
                }, error -> {
                    Log.d("presenter_error", ""+error.getMessage());
                    error.printStackTrace();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
