package com.blaizmiko.popcornapp.ui.all.presentation.genretags;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface GenresTagsView extends MvpView{
    void setGenreTags(List<String> genreNames);
    void showProgress();
    void hideProgress();
    void showError();
    void finishLoad();
}
