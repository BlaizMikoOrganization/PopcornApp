package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.Loader;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.TopMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TopMoviesPresenter extends BaseMvpPresenter<TopMoviesView> implements Loader {
    @Inject
    PealApi mPealApi;

    int mCurrentPage = Constants.Api.FirstPage;
    private boolean mIsLoading = false;

    public TopMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Override
    public void load() {
        if(mIsLoading) return;
        mIsLoading = true;
        loadTopRatedMoviesList();
    }

    public void loadTopRatedMoviesList() {
        getViewState().showProgress();

        final Subscription topRatedMoviesSubscription = mPealApi
                .getTopRatedMovies(Constants.Api.ApiKey, Constants.Api.Language, mCurrentPage++, Constants.Api.NowMovieDefaultRegion)
                .flatMap(topRatedMovies -> Observable.from(topRatedMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    mIsLoading = false;
                    getViewState().setTopMoviesList(moviesList);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());
        unSubscribeOnDestroy(topRatedMoviesSubscription);
    }
}
