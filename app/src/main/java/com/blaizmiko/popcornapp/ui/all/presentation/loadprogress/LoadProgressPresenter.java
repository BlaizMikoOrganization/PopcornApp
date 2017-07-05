package com.blaizmiko.popcornapp.ui.all.presentation.loadprogress;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

@InjectViewState
public class LoadProgressPresenter extends BaseMvpPresenter<LoadProgressView> {

    private int progressTasksCount;

    public void showProgress() {
        progressTasksCount++;
        Log.d("loadprogress", "plus. n = " +progressTasksCount);
        getViewState().showProgress();
    }

    public void hideProgress() {
        progressTasksCount--;
        Log.d("loadprogress", "minus. n = " +progressTasksCount);
        if (progressTasksCount == 0) {
            getViewState().hideProgress();
        }
    }
}
