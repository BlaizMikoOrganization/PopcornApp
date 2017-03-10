package com.blaizmiko.popcornapp.ui.actors.details;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.ui.all.presenters.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ActorDetailsPresenter extends BaseMvpPresenter<ActorDetailsView> {

    @Inject
    PealApi mPealApi;

    ActorDetailsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    void loadActorDetails(final int actorId) {
        getViewState().showProgress();

        final Subscription actorDetailsSubscription = mPealApi.getActorDetails(actorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(actorModel -> {
                    getViewState().setActorDetails(actorModel);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(actorDetailsSubscription);
    }
}
