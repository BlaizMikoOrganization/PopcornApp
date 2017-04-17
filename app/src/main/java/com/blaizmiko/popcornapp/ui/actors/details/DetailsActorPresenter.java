package com.blaizmiko.popcornapp.ui.actors.details;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class DetailsActorPresenter extends BaseMvpPresenter<DetailsActorView>{
    @Inject
    MovieDbApi movieDbApi;

    DetailsActorPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadActorInfo(final int actorId) {
        getViewState().startLoad();
        final Subscription creditsMovieSubscription = movieDbApi.getActorInfo(actorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(actor -> {
                    getViewState().showActor(actor);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(creditsMovieSubscription);
    }
}
