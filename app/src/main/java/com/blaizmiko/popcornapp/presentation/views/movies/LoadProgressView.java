package com.blaizmiko.popcornapp.presentation.views.movies;

import com.arellomobile.mvp.MvpView;

public interface LoadProgressView extends MvpView{
    void finishLoad();
    void startLoad();
}
