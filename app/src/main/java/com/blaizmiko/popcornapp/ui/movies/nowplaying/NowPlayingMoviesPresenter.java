package com.blaizmiko.popcornapp.ui.movies.nowplaying;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.data.db.DataConsumer;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class NowPlayingMoviesPresenter extends BaseMvpPresenter<NowPlayingMoviesView> implements DataConsumer {
    @Inject
    MovieDbApi movieDbApi;
    private int currentPage = Constants.MovieDbApi.FirstPage;
    @Inject
    Database database;
    @Inject
    DataManager dataManager;

    public NowPlayingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowMoviesList() {

        dataManager.getNowPlayingMovies(this, currentPage);

/*        final Subscription nowMoviesSubscription = dataManager.getNowPlayingMovies(currentPage)
                .flatMap(baseMovieListResponse -> Observable.from(baseMovieListResponse.getMovies()))
                .filter(movie -> movie != null)
                .map(movie -> {
                    movie.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + movie.getBackdropPath());
                    return movie;
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().showNowMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    error.printStackTrace();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(nowMoviesSubscription);*/
    }

    @Override
    public void consumeMoviesList(final List<DetailedMovieDBModel> detailedMovieDBModelList) {
        Log.d("consuming", "this");
        getViewState().showNowMoviesList(detailedMovieDBModelList);
    }
}
