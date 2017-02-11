package com.blaizmiko.popcornapp.presentation.presenters.actors;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.actors.ActorsView;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ActorsPresenter extends BaseMvpPresenter<ActorsView> {

    @Inject
    PealApi mPealApi;

    private int currentPage = 1;
    private int totalPages;

    public ActorsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadActorsList() {

        getViewState().showProgress();

        final Subscription actorsSubscription = mPealApi.getPopularActors(Constants.Api.ApiKey, Constants.Api.Language, currentPage)
                .doOnNext(popularActors -> {
                    currentPage = popularActors.getPage();
                    totalPages = popularActors.getTotalPages();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularActors -> {
                    getViewState().setActorsList(popularActors);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();;
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(actorsSubscription);
    }
}
