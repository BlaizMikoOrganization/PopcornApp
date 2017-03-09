package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.ui.all.presenters.BaseMvpPresenter;
import com.blaizmiko.popcornapp.ui.movies.LoadProgressView;

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
