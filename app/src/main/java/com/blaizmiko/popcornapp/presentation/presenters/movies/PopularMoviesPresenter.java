package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.models.movies.BriefMovie;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.popularMovies.PopularMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.movies.TileAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class PopularMoviesPresenter extends BaseMvpPresenter<PopularMoviesView> {
    @Inject
    PealApi mPealApi;

    public PopularMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPopularMoviesList() {

        final Subscription popularMoviesSubscription = mPealApi.getPopularMovies(Constants.Api.ApiKey, Constants.Api.Language, Constants.Api.FirstPage, Constants.Api.NowMovieDefaultRegion)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularMovies -> {
                    getViewState().setPopularMoviesList(createPopularMoviesCells(popularMovies.getMovies()));
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(popularMoviesSubscription);
    }

    private List<TileAdapter.Item> createPopularMoviesCells(final List<BriefMovie> movies) {
        final ArrayList<TileAdapter.Item> popularMoviesCells = new ArrayList<>(movies.size());
        final int convertToFiveScore = 2;
        final int round = 10;

        for (int i = 0; i < movies.size(); i++) {
            final String imagePath =  Constants.Api.BasePopularMovieImageUrl+movies.get(i).getPosterPath();
            final String title = movies.get(i).getTitle();
            final float avrVote = (float)(Math.floor(movies.get(i).getVoteAverage()/convertToFiveScore * round)/round);
            popularMoviesCells.add(new TileAdapter.Item(imagePath, title, avrVote));
        }

        return popularMoviesCells;
    }
}
