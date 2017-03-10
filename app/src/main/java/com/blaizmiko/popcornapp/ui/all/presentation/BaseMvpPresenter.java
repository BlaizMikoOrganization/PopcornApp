package com.blaizmiko.popcornapp.ui.all.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseMvpPresenter<View extends MvpView> extends MvpPresenter<View> {

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected void unSubscribeOnDestroy(@NonNull final Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.clear();
    }
}