package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.ui.all.presenters.BaseMvpPresenter;
import com.blaizmiko.popcornapp.ui.movies.UpcomingMoviesView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class UpcomingMoviesPresenter extends BaseMvpPresenter<UpcomingMoviesView> {

    @Inject
    PealApi mPealApi;

    private int mCurrentPage = Constants.TheMovieDbApi.FirstPage;

    public UpcomingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadUpcomingMoviesList() {
        getViewState().startLoad();

        final Subscription upcomingMoviesSubscription = mPealApi
                .getUpcomingMovies(mCurrentPage, Constants.TheMovieDbApi.NowMovieDefaultRegion)
                .flatMap(upcomingMovies -> Observable.from(upcomingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getId(), briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setUpcomingMoviesList(moviesList);
                    mCurrentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(upcomingMoviesSubscription);
    }
}
