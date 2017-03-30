package com.blaizmiko.popcornapp.ui.tvshows.nowplaying;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.Random;

import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class NowPlayingTvShowsPresenter extends BaseMvpPresenter<NowPlayingTvShowsView> {
    @Inject
    MovieDbApi pealApi;
    private int currentPage = Constants.MovieDbApi.FirstPage;

    public NowPlayingTvShowsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowPlayingTvShowsList() {
        getViewState().startLoad();

        final Subscription nowPlayingTvShowsSubscription = pealApi.getNowPlayingTvShows(currentPage)
                .flatMap(nowPlayingTvShowsList -> Observable.from(nowPlayingTvShowsList.getTvShows()))
                .filter(tvShow -> tvShow != null)
                .map(tvShow -> new TileAdapter.Item(tvShow.getId(), tvShow.getBackdropPath(), tvShow.getName(), tvShow.getVoteAverage(), tvShow.getBackdropPath(), tvShow.getPosterPath()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowsList -> {
                    getViewState().setNowPlayingTvShowsList(tvShowsList);
                    currentPage++;
                }, error -> {
                    getViewState().showError();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowPlayingTvShowsSubscription);
    }

}
