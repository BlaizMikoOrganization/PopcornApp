package com.blaizmiko.popcornapp.ui.movies.reviews;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.db.models.images.ImageDBModel;

import java.util.List;

public interface ReviewView extends MvpView{
    void finishLoad();
    void startLoad();
    void showError();

    void showPosters(final List<ImageDBModel> images);
}
