package com.blaizmiko.popcornapp.ui.movies.popular;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDBConsumer;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class PopularMoviesPresenter extends BaseMvpPresenter<PopularMoviesView> implements IDBConsumer {
    @Inject
    MovieDbApi movieDbApi;

    @Inject
    Database database;

    private int currentPage = Constants.MovieDbApi.FirstPage;

    public PopularMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPopularMoviesList() {
        getViewState().startLoad();

        final Subscription popularMoviesSubscription = movieDbApi.getPopularMovies(currentPage, Constants.MovieDbApi.NowMovieDefaultRegion)
                .flatMap(popularMovies -> Observable.from(popularMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(detailedMovieDBModel -> {
                    detailedMovieDBModel.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + detailedMovieDBModel.getPosterPath());
                    return detailedMovieDBModel;
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    database.putPopularMovies(moviesList, currentPage);
                    getViewState().showPopularMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    database.getPopularMovies(this, currentPage);
                    getViewState().finishLoad();
                    getViewState().showError();
                    currentPage++;
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(popularMoviesSubscription);
    }

    @Override
    public void transferData(final MoviesResponseDBModel movieResponse) {
        getViewState().showPopularMoviesList(movieResponse.getMovies());
    }
}
