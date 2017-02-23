package com.blaizmiko.popcornapp.presentation.presenters.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.presentation.presenters.base.BaseMvpPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.LoadProgressView;

@InjectViewState
public class LoadProgressPresenter extends BaseMvpPresenter<LoadProgressView>{

    private int mPull = 0;

    public void loadStarted() {
        mPull++;
    }

    public void loadFinished() {
        mPull--;
        if (mPull == 0) {
            getViewState().finishLoad();
        }
    }
}
