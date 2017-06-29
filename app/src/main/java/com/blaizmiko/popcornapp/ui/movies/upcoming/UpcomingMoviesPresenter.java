package com.blaizmiko.popcornapp.ui.movies.upcoming;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class UpcomingMoviesPresenter extends BaseMvpPresenter<UpcomingMoviesView> {
    @Inject
    DataManager dataManager;

    private int currentPage = Constants.MovieDbApi.FirstPage;

    public UpcomingMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadUpcomingMoviesList() {
        getViewState().startLoad();

        Subscription nowMoviesSubscription = dataManager.getUpcomingMovies(currentPage)
                .subscribe(detailedMovieDBModels -> {
                    getViewState().showUpcomingMoviesList(detailedMovieDBModels);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
