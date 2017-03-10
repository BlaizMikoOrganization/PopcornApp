package com.blaizmiko.popcornapp.ui.tvshows;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TopTvShowsPresenter extends BaseMvpPresenter<TopTvShowsView>{
    @Inject
    PealApi pealApi;
    private int currentPage = Constants.TheMovieDbApi.FirstPage;

    public TopTvShowsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadTopTvShowsList() {
        getViewState().startLoad();

        final Subscription topTvShowsSubscription = pealApi.getTopTvShows(currentPage)
                .flatMap(popularTvShows -> Observable.from(popularTvShows.getTvShows()))
                .filter(tvShow -> tvShow != null)
                .map(tvShow -> new TileAdapter.Item(tvShow.getId(), tvShow.getPosterPath(), tvShow.getName(), tvShow.getVoteAverage()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowsList -> {
                    getViewState().setTopTvShowsList(tvShowsList);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(topTvShowsSubscription);

    }
}
