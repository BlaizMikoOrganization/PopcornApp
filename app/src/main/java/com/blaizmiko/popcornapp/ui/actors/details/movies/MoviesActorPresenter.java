package com.blaizmiko.popcornapp.ui.actors.details.movies;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.models.actors.moviecredits.ActorMovieCrewModel;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class MoviesActorPresenter extends BaseMvpPresenter<MoviesActorView>{
    @Inject
    MovieDbApi movieDbApi;

    MoviesActorPresenter () {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMoviesActor(final int actorId) {
        getViewState().startLoad();

        final Subscription taggedImagesSubscription = movieDbApi.getActorMovieCredits(actorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(actorMovieCreditsResponse -> {

                    getViewState().showActorMoviesCast(actorMovieCreditsResponse.getCast());
                    Subscription crewSubscription = Observable.from(actorMovieCreditsResponse.getCrew())
                            .groupBy(actorMovieCrewModel -> actorMovieCrewModel.getJob())
                            .flatMap(Observable::toList)
                            .subscribe(groups -> {
                                getViewState().showActorMoviesCrew(groups);

                            }, error -> {
                                Log.d("error", error.getMessage());
                            });
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(taggedImagesSubscription);
    }

}
