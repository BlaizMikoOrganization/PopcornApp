package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.PopularMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class PopularMoviesPresenter extends BaseMvpPresenter<PopularMoviesView> {
    @Inject
    PealApi mPealApi;

    int mCurrentPage = Constants.Api.FirstPage;

    public PopularMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPopularMoviesList() {
        getViewState().startLoad();

        final Subscription popularMoviesSubscription = mPealApi.getPopularMovies(Constants.Api.ApiKey, Constants.Api.Language, mCurrentPage, Constants.Api.NowMovieDefaultRegion)
                .flatMap(popularMovies -> Observable.from(popularMovies.getMovies()))
                .filter(briefMovie -> briefMovie != null)
                .map(briefMovie -> new TileAdapter.Item(briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    getViewState().setPopularMoviesList(moviesList);
                    mCurrentPage++;
                });

        unSubscribeOnDestroy(popularMoviesSubscription);
    }
}
