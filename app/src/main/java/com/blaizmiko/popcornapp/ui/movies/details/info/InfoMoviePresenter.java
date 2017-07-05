package com.blaizmiko.popcornapp.ui.movies.details.info;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.data.Database;
import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDetailedMovieDBConsumer;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
import javax.inject.Inject;
import rx.Subscription;

@InjectViewState
public class InfoMoviePresenter extends BaseMvpPresenter<InfoMovieView> implements IDetailedMovieDBConsumer{
    @Inject
    MovieDbApi movieDbApi;
    @Inject
    Database database;

    @Inject
    DataManager dataManager;

    InfoMoviePresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMovieInfo(final long movieId) {
        getViewState().startLoad();
        final Subscription movieInfoSubscription = dataManager.getMovie(movieId)
                .subscribe(movie -> getViewState().updateMovieExtras(movie),
                error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(movieInfoSubscription);
    }

    @Override
    public void transferData(DetailedMovieDBModel detailedMovieDBModel) {
        getViewState().updateMovieExtras(detailedMovieDBModel);
    }
}