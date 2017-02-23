package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.UpcomingMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class UpcomingMoviesPresenter extends BaseMvpPresenter<UpcomingMoviesView> {
    @Inject
    PealApi mPealApi;

    int mCurrentPage = Constants.Api.FirstPage;

    public UpcomingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadUpcomingMoviesList() {
        getViewState().startLoad();

        final Subscription upcomingMoviesSubscription = mPealApi
                .getUpcomingMovies(Constants.Api.ApiKey, Constants.Api.Language, mCurrentPage, Constants.Api.NowMovieDefaultRegion)
                .flatMap(upcomingMovies -> Observable.from(upcomingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setUpcomingMoviesList(moviesList);
                    mCurrentPage++;
                });

        unSubscribeOnDestroy(upcomingMoviesSubscription);
    }
}
