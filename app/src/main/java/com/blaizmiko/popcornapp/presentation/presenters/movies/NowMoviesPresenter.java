package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.models.movies.BriefMovie;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class NowMoviesPresenter extends BaseMvpPresenter<NowMoviesView> {

    @Inject
    PealApi mPealApi;

    public NowMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowMoviesList() {
        getViewState().showProgress();

        final Subscription nowMoviesSubscription = mPealApi.getNowPlayingMovies(Constants.Api.ApiKey, Constants.Api.Language, Constants.Api.FirstPage, Constants.Api.NowMovieDefaultRegion)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nowPlayingMovies -> {
                    getViewState().setNowMoviesList(createNowMovieCells(nowPlayingMovies.getMovies()));
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                });

        unSubscribeOnDestroy(nowMoviesSubscription);
    }

    private List<TileAdapter.Item> createNowMovieCells(final List<BriefMovie> movies) {
        final ArrayList<TileAdapter.Item> nowMoviesCells = new ArrayList<>(movies.size());

        for (int i = 0; i < movies.size(); i++) {
            nowMoviesCells.add(new TileAdapter.Item(
                    movies.get(i).getBackdropPath(),
                    movies.get(i).getTitle(),
                    movies.get(i).getVoteAverage()));
        }

        return nowMoviesCells;
    }
}
