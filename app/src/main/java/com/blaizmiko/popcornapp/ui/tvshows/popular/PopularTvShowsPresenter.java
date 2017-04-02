package com.blaizmiko.popcornapp.ui.tvshows.popular;

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
public class PopularTvShowsPresenter extends BaseMvpPresenter<PopularTvShowsView> {
    @Inject
    MovieDbApi pealApi;
    private int currentPage = Constants.MovieDbApi.FirstPage;

    public PopularTvShowsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPopularTvShowsList() {
        getViewState().startLoad();

        final Subscription popularTvShowsSubscription = pealApi.getPopularTvShows(currentPage)
                .flatMap(popularTvShows -> Observable.from(popularTvShows.getTvShows()))
                .filter(tvShow -> tvShow != null)
                .map(tvShow -> new TileAdapter.Item(tvShow.getId(), tvShow.getPosterPath(), tvShow.getName(), tvShow.getVoteAverage(), tvShow.getBackdropPath(), tvShow.getPosterPath()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowsList -> {
                    getViewState().setPopularTvShowsList(tvShowsList);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(popularTvShowsSubscription);
    }
}