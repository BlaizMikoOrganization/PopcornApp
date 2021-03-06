package com.blaizmiko.popcornapp.ui.movies.top;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class TopMoviesPresenter extends BaseMvpPresenter<TopMoviesView> {
    @Inject
    DataManager dataManager;
    private int currentPage = Constants.MovieDbApi.FirstPage;

    public TopMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadTopRatedMoviesList() {
        getViewState().startLoad();

        Subscription nowMoviesSubscription = dataManager.getTopMovies(currentPage)
                .subscribe(detailedMovieDBModels -> {
                    getViewState().showTopMoviesList(detailedMovieDBModels);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
