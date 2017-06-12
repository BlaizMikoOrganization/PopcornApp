package com.blaizmiko.popcornapp.ui.movies.nowplaying;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
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
                .flatMap(nowPlayingMovies ->  Observable.from(nowPlayingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getId(), briefMovie.getBackdropPath(), briefMovie.getTitle(), briefMovie.getVoteAverage(), briefMovie.getBackdropPath(), briefMovie.getPosterPath()))
                .toList()
                .doOnNext(items -> database.putDetailedMovies(DetailedMovieDBModel.fromTileAdapterItem(items)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    database.subscribeToRestoreDetailedMovie(view);
                    getViewState().showNowMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
