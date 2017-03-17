package com.blaizmiko.popcornapp.ui.tvshows;

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
public class UpcomingTvShowsPresenter extends BaseMvpPresenter<UpcomingTvShowsView> {
    @Inject
    MovieDbApi pealApi;
    private int currentPage = Constants.MovieDbApi.FirstPage;

    UpcomingTvShowsPresenter() {
        BaseApplication.getComponent().inject(this);
    }


    public void loadUpcomingTvShowsList() {
        getViewState().startLoad();

        final Subscription upcomingTvShowsSubscription = pealApi.getUpcomingTvShows(currentPage)
                .flatMap(upcomingTvShows -> Observable.from(upcomingTvShows.getTvShows()))
                .filter(tvShow -> tvShow != null)
                .map(tvShow -> new TileAdapter.Item(tvShow.getId(), tvShow.getPosterPath(), tvShow.getName(), tvShow.getVoteAverage(), tvShow.getBackdropPath(), tvShow.getPosterPath()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowsList -> {
                    getViewState().setUpcomingTvShowsList(tvShowsList);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(upcomingTvShowsSubscription);
    }
}
