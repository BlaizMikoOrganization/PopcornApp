package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.MvpView;

public interface LoadProgressView extends MvpView {
    void hideProgress();
    void showProgress();
}
