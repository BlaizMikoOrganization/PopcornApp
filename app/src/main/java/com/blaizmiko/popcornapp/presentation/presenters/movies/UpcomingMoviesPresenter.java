package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.Loader;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.UpcomingMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class UpcomingMoviesPresenter extends BaseMvpPresenter<UpcomingMoviesView> implements Loader{
    @Inject
    PealApi mPealApi;

    int mCurrentPage = Constants.Api.FirstPage;
    private boolean mIsLoading = false;

    public UpcomingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Override
    public void load() {
        if(mIsLoading) return;
        mIsLoading = true;
        loadUpcomingMoviesList();
    }

    public void loadUpcomingMoviesList() {
        getViewState().showProgress();

        final Subscription upcomingMoviesSubscription = mPealApi
                .getUpcomingMovies(Constants.Api.ApiKey, Constants.Api.Language, mCurrentPage++, Constants.Api.NowMovieDefaultRegion)
                .flatMap(upcomingMovies -> Observable.from(upcomingMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    mIsLoading = false;
                    getViewState().setUpcomingMoviesList(moviesList);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(upcomingMoviesSubscription);
    }
}
