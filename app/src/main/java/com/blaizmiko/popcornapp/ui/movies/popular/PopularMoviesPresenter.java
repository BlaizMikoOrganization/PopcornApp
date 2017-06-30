package com.blaizmiko.popcornapp.ui.movies.popular;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class PopularMoviesPresenter extends BaseMvpPresenter<PopularMoviesView> {
    @Inject
    DataManager dataManager;
        private int currentPage = Constants.MovieDbApi.FirstPage;

    public PopularMoviesPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPopularMoviesList() {
        getViewState().startLoad();

        Subscription nowMoviesSubscription = dataManager.getPopularMovies(currentPage)
                .subscribe(detailedMovieDBModels -> {
                    getViewState().showPopularMoviesList(detailedMovieDBModels);
                    currentPage++;
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(nowMoviesSubscription);
    }
}
