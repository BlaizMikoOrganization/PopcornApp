package com.blaizmiko.popcornapp.ui.movies.upcoming;

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
public class UpcomingMoviesPresenter extends BaseMvpPresenter<UpcomingMoviesView> implements IDBConsumer{
    @Inject
    MovieDbApi movieDbApi;
    @Inject
    Database database;

    private int currentPage = Constants.MovieDbApi.FirstPage;

    public UpcomingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadUpcomingMoviesList() {
        getViewState().startLoad();

        final Subscription upcomingMoviesSubscription = movieDbApi
                .getUpcomingMovies(currentPage, Constants.MovieDbApi.NowMovieDefaultRegion)
                .flatMap(upcomingMovies -> Observable.from(upcomingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(detailedMovieDBModel -> {
                    detailedMovieDBModel.setImagePath(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + detailedMovieDBModel.getPosterPath());
                    return detailedMovieDBModel;
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    database.putUpcomingMovies(moviesList, currentPage);
                    getViewState().showUpcomingMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    database.getUpcomingMovies(this, currentPage);
                    getViewState().finishLoad();
                    getViewState().showError();
                    currentPage++;
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(upcomingMoviesSubscription);
    }

    @Override
    public void transferData(final MoviesResponseDBModel movieResponse) {
        getViewState().showUpcomingMoviesList(movieResponse.getMovies());
    }
}
