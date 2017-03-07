package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.TopMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TopMoviesPresenter extends BaseMvpPresenter<TopMoviesView> {
    @Inject
    PealApi mPealApi;

    private int mCurrentPage = Constants.Api.FirstPage;

    public TopMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadTopRatedMoviesList() {
        getViewState().startLoad();

        final Subscription topRatedMoviesSubscription = mPealApi
                .getTopRatedMovies(Constants.Api.ApiKey, Constants.Api.Language, mCurrentPage, Constants.Api.NowMovieDefaultRegion)
                .flatMap(topRatedMovies -> Observable.from(topRatedMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setTopMoviesList(moviesList);
                    mCurrentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(topRatedMoviesSubscription);
    }
}