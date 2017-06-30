package com.blaizmiko.popcornapp.ui.movies.nowplaying;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class NowPlayingMoviesPresenter extends BaseMvpPresenter<NowPlayingMoviesView> {
    @Inject
    DataManager dataManager;

    private int currentPage = Constants.MovieDbApi.FirstPage;

    public NowPlayingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadNowMoviesList() {
        getViewState().startLoad();

        Subscription nowMoviesSubscription = dataManager.getNowPlayingMovies(currentPage)
            .subscribe(detailedMovieDBModels -> {
                getViewState().showNowMoviesList(detailedMovieDBModels);
                currentPage++;
            }, error -> {
                getViewState().finishLoad();
                getViewState().showError();
            }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
