package com.blaizmiko.popcornapp.ui.movies.top;

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
public class TopMoviesPresenter extends BaseMvpPresenter<TopMoviesView> implements IDBConsumer{
    @Inject
    MovieDbApi movieDbApi;
    private int currentPage = Constants.MovieDbApi.FirstPage;

    @Inject
    Database database;

    public TopMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadTopRatedMoviesList() {
        getViewState().startLoad();

        final Subscription topRatedMoviesSubscription = movieDbApi
                .getTopRatedMovies(currentPage, Constants.MovieDbApi.NowMovieDefaultRegion)
                .flatMap(topRatedMovies -> Observable.from(topRatedMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(detailedMovieDBModel -> {
                    detailedMovieDBModel.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + detailedMovieDBModel.getPosterPath());
                    return detailedMovieDBModel;
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    database.putTopMovies(moviesList, currentPage);
                    getViewState().showTopMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    database.getTopMovies(this, currentPage);
                    getViewState().finishLoad();
                    getViewState().showError();
                    currentPage++;
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(topRatedMoviesSubscription);
    }

    @Override
    public void transferData(final MoviesResponseDBModel movieResponse) {
        getViewState().showTopMoviesList(movieResponse.getMovies());
    }
}
