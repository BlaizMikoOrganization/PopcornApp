package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.LoadProgressView;

@InjectViewState
public class LoadProgressPresenter extends BaseMvpPresenter<LoadProgressView> {

    private int mProgressTasksCount;

    public void showProgress() {
        mProgressTasksCount++;
        getViewState().showProgress();
    }

    public void hideProgress() {
        mProgressTasksCount--;
        if (mProgressTasksCount == 0) {
            getViewState().hideProgress();
        }
    }
}
