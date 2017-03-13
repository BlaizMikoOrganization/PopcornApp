package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class UpcomingMoviesPresenter extends BaseMvpPresenter<UpcomingMoviesView> {

    @Inject
    MovieDbApi movieDbApi;

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
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getId(), briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setUpcomingMoviesList(moviesList);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(upcomingMoviesSubscription);
    }
}
