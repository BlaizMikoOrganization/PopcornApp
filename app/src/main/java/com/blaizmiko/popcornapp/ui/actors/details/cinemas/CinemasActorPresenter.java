package com.blaizmiko.popcornapp.ui.actors.details.cinemas;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.models.actors.cinemascredits.ActorCinemaCreditsResponse;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class CinemasActorPresenter extends BaseMvpPresenter<CinemasActorView>{

    @Inject
    MovieDbApi movieDbApi;

    CinemasActorPresenter () {
        BaseApplication.getComponent().inject(this);
    }

    public void loadActorMovies(final int actorId) {
        getViewState().startLoad();
        unSubscribeOnDestroy(loadActorCinemas(movieDbApi.getActorMovieCredits(actorId)));
    }

    public void loadActorTvShows(final int actorId) {
        getViewState().startLoad();
        unSubscribeOnDestroy(loadActorCinemas(movieDbApi.getActorTvShowCredits(actorId)));
    }

    private Subscription loadActorCinemas(final Observable<ActorCinemaCreditsResponse> cinemaObservable) {
        final Subscription actorCinemasSubscription = cinemaObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(actorCinemaCreditsResponse -> {
                    getViewState().showCastCinemas(actorCinemaCreditsResponse.getCast());
                    final Subscription crewSubscription = Observable.from(actorCinemaCreditsResponse.getCrew())
                            .groupBy(actorCinemaCrewModel -> actorCinemaCrewModel.getJob())
                            .flatMap(Observable::toList)
                            .subscribe(groups -> {
                                getViewState().showCrewCinemas(groups);
                            }, error -> {
                                getViewState().showError();
                            });
                    unSubscribeOnDestroy(crewSubscription);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        return actorCinemasSubscription;
    }
}
