package com.blaizmiko.popcornapp.ui.actors;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class PopularActorsPresenter extends BaseMvpPresenter<PopularActorsView> {

    @Inject
    MovieDbApi movieDbApi;
    private int currentPage = 1;
    private int totalPages;

    public PopularActorsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    void loadActorsList() {
        getViewState().showProgress();

        final Subscription actorsSubscription = movieDbApi.getPopularActors(currentPage)
                .doOnNext(popularActors -> {
                    currentPage = popularActors.getPage();
                    totalPages = popularActors.getTotalPages();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularActors -> {
                    getViewState().showActorsList(popularActors);
                }, error -> {
                    getViewState().hideProgress();
                    getViewState().showError();
                }, () -> getViewState().hideProgress());

        unSubscribeOnDestroy(actorsSubscription);
    }
}
