package com.blaizmiko.popcornapp.ui.actors;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.ui.all.presenters.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class PopularActorsPresenter extends BaseMvpPresenter<PopularActorsView> {

    @Inject
    PealApi mPealApi;

    private int currentPage = 1;
    private int totalPages;

    PopularActorsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    void loadActorsList() {
        getViewState().showProgress();

        final Subscription actorsSubscription = mPealApi.getPopularActors(currentPage)
                .doOnNext(popularActors -> {
                    currentPage = popularActors.getPage();
                    totalPages = popularActors.getTotalPages();
                })
                .flatMap(popularActorsModel -> Observable.from(popularActorsModel.getPopularActors()))
                .filter(actor -> actor != null && !actor.isAdult())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularActors -> {
                    getViewState().setActorsList(popularActors);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(actorsSubscription);
    }
}
