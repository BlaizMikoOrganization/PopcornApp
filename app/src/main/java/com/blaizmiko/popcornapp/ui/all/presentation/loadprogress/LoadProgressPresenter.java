package com.blaizmiko.popcornapp.ui.all.presentation.loadprogress;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

@InjectViewState
public class LoadProgressPresenter extends BaseMvpPresenter<LoadProgressView> {

    private int progressTasksCount;

    public void showProgress() {
        progressTasksCount++;
        getViewState().showProgress();
    }

    public void hideProgress() {
        progressTasksCount--;
        if (progressTasksCount == 0) {
            getViewState().hideProgress();
        }
    }
}
